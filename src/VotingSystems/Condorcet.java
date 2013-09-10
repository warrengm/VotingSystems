package VotingSystems;

import java.util.HashMap;
import java.util.Map;

/**
 * 
 * @author Warren Godone-Maresca
 * 
 * <p>
 *
 *<b>The Condorcet Method</b>
 * <p>
 * <b>Purpose:</b> To compute the winning candidate using the Condorcet method 
 * 				from a set of ranked ballots.
 * <p>
 * <b>Input:</b>	The ranked ballots as an array of the <code>Ballot</code> 
 * 			object using the non-default constructor. The ballots as well as the
 * 			candidates' names must set in a different class. However the 
 * 			information about the candidates does not need to be explicitly 
 * 			inputted, the information will be taken from the array ballots.
 * <p>
 * <b>Output:</b>	The name of the winning candidate if one exists, else the 
 * 			string "Tie". The output is given with <code>{@link #computeWinner()}
 * 			</code>.
 * <p>
 * <b>Algorithm:</b> The position of each candidate is compared pairwisely 
 * 					against all other candidates on each ballot. If a candidate 
 * 					is ranked above the other candidate	on a majority of ballots
 * 					in all comparisons, then that candidate wins.
 */
public class Condorcet extends VotingSystem{

	/**
	 * Holds the votes of each candidate against each other candidate
	 */
	private Map<String, Map<String, Integer>> candVotes;
	
	/**
	 * Does nothing
	 */
	public Condorcet(){}

	public Condorcet(Ballot[] ballots){
		super(ballots);
	}

	@Override
	protected void initCandidates(Ballot[] ballots){
		candVotes = new HashMap<String, Map<String, Integer>>();

		for(Ballot ballot : ballots){
			for(String cand : ballot.toArray()){
				if(!candVotes.containsKey(cand))
					candVotes.put(cand, new HashMap<String, Integer>());
			}
		}

		for(Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()){
			for(Map.Entry<String, Map<String, Integer>> inner : candVotes.entrySet()){
				if(!outer.getKey().equals(inner.getKey())){
					outer.getValue().put(inner.getKey(), 0);
				}
			}
		}
	}

	@Override
	protected void setVotes() {
		//for each ballot
		for(Ballot ballot : voterBallots){
			//and for each candidate
			for(Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()){
				//against each candidate
				for(Map.Entry<String, Integer> inner : outer.getValue().entrySet()){
					int outerI = ballot.getIndex(outer.getKey()); //Outer index
					int innerI = ballot.getIndex(inner.getKey());

					if(innerI == -1){ //If the inner candidate is not ranked
						if(outerI != -1){//but the outer one is,
							//increment the votes of outer against inner.
							inner.setValue(inner.getValue() + 1);
						}
					} else if(outerI < innerI){//Lower index --> higher preference
						inner.setValue(inner.getValue() + 1);
					}
				}
			}
		}
	}

	/**
	 * Returns the name of the winner or an empty string if no winner exists.
	 */
	protected String getWinner(){
		String winner = "";

		Map<String, Boolean> isDefeated = new HashMap<String, Boolean>();

		//Compares each candidate against each other candidate.
		for(Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()){
			//against each candidate

			isDefeated.put(outer.getKey(), false);

			for(Map.Entry<String, Integer> inner : outer.getValue().entrySet()){
				if(inner.getValue() < voterBallots.size()/2 + 1){
					isDefeated.put(outer.getKey(), true);
				}
			}
		}

		//Checks for an undefeated candidate.
		for(Map.Entry<String, Boolean> entry : isDefeated.entrySet()){
			if(entry.getValue() == false)
				winner = entry.getKey();
		}

		return winner;
	}

	@Override
	public String results() {
		String table = String.format("%-18s", " ");

		//Top row
		for(Map.Entry<String, Map<String, Integer>> entry : candVotes.entrySet())
			table += String.format("%-18s", entry.getKey());


		for(Map.Entry<String, Map<String, Integer>> outer : candVotes.entrySet()){
			table += String.format("\n%-18s", outer.getKey());

			for(Map.Entry<String, Map<String, Integer>> inner : candVotes.entrySet()){
				if(outer.getKey().equals(inner.getKey())){
					table += String.format("%-18s", "-");
				} else {
					table += String.format("%-18d", outer.getValue().get(inner.getKey()));
				}
			}
		}

		return table;
	}
	
	@Override
	//Overrided to remove implementation
	protected boolean isTied(String candidate) {
		return false;
	}



}//End Condorcet