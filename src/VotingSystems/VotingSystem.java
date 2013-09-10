package VotingSystems;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 
 * @author Warren Godone-Maresca
 * 
 * <p>
 * <b>Voting System</b>
 * <p>
 * <b>Purpose:</b> To provide generic methods for any voting system.
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
 * <b>Precondition</b>	The array of ballots and the candidates names must be set.
 */
public abstract class VotingSystem {

	/**
	 * Holds the ballots of each voter.
	 */
	protected List<Ballot> voterBallots;

	/**
	 * Holds the candidates' votes with their name as the key.
	 */
	protected Map<String, Integer> candVotes;

	/**
	 * Default constructor which doesn't do anything.
	 */
	public VotingSystem(){}

	/**
	 * Constructor which receives an array of ballots and initializes <code>ballots
	 *</code>, <code>candidates</code>, and the size of <code>candidatesVotes</code>.
	 */
	public VotingSystem(Ballot[] ballots){
		initBallots(ballots);
		initCandidates(ballots);
		setVotes();
	}
	
	protected void initBallots(Ballot[] ballots){
		voterBallots = new ArrayList<Ballot>(); //Sets the ballots list.

		for(int i = 0; i < ballots.length; i++){	//For each ballot,
			if(ballots[i] != null){					//if it is not null,
				//add it to the ballots array.
				voterBallots.add(new Ballot(ballots[i].toArray()));
			}
		}
	}


	/**
	 * Sets up the number of candidate and their names from the first ballot in
	 * the array. The index of each candidate will be taken from that ballot.
	 */
	protected void initCandidates(Ballot[] ballots){
		candVotes = new HashMap<String, Integer>();

		for(Ballot ballot : ballots){
			for(String cand : ballot.toArray()){
				if(!candVotes.containsKey(cand))
					candVotes.put(cand, 0);
			}

		}
	}

	/**
	 * Computes and returns the name of the winning candidate.
	 * 
	 * @return If a winner exists, the name of the winning candidate; otherwise,
	 * the string "Tie".
	 */
	public String computeWinner() {
		String winner = getWinner();		//Get the winner,

		if(isTied(winner) || winner.length() == 0){	//If the winner is tied
			return "Tie";					//return "Tie"
		} else {
			return winner;//Otherwise return the winner.
		}
	}

	/**
	 * Sets the votes according to the rules of the particular voting system.
	 */
	protected abstract void setVotes();

	protected String getWinner(){
		String winner = "";
		int maxVotes = 0;

		for(Map.Entry<String, Integer> entry : candVotes.entrySet()){
			if(entry.getValue() > maxVotes || maxVotes == 0){
				maxVotes = entry.getValue();
				winner = entry.getKey();
			}
		}

		return winner;
	}

	/**
	 * Checks if that candidate is tied with any candidate (has an equal number 
	 * of votes). 
	 * 
	 * @return <code>true</code> if the candidate is with another candidate, 
	 * otherwise <code>false</code>.
	 */
	protected boolean isTied(String candidate){
		boolean isTie = false;
		int candsVotes = candVotes.get(candidate);

		for(Map.Entry<String, Integer> entry : candVotes.entrySet()){

			if(entry.getValue() == candsVotes && 
					!candidate.equals(entry.getKey())){
				isTie = true; //Then that candidate is tied.
			}
		}

		return isTie;
	}

	/**
	 * Returns a summary of the election.
	 * @return The summary. TODO be more specific
	 */
	public String results(){
		String results = "";

		String[] candidates = getSortedCandidateList(); //Sorts the candidates.

		for(String candidate : candidates){
			results += String.format("%-18s %d", //each candidate's results.
					candidate + ":", candVotes.get(candidate)) + "\n";
		}

		return results;
	}

	/**
	 * Returns an array of the candidates sorted by number of votes received.
	 */
	protected String[] getSortedCandidateList(){
		String[] candidates = new String[candVotes.size()];

		int i = 0;

		for(Map.Entry<String, Integer> entry : candVotes.entrySet())
			candidates[i++] = entry.getKey();

		insertionSort(candidates);

		return candidates;
	}

	private void insertionSort(String[] a){
		for(int i = 1; i < a.length; i++){
			String val = a[i];
			int index = i;

			while(index > 0 && candVotes.get(val) > candVotes.get(a[index - 1])){
				a[index] = a[index - 1];
				index--;
			}

			a[index] = val;
		}
	}


} //end VotingSystem
