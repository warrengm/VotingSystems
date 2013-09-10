package VotingSystems;

import java.util.Map;

/**
 * 
 * @author Warren Godone-Maresca
 * 
 * <p>
 * <b>The Borda Count</b>
 * <p>
 * <b>Purpose:</b> To compute the winning candidate using the plurality voting 
 * 			method from a set of ranked ballots.
 * <p>
 * <b>Input:</b>	The ballots as an array of the <code>Ballot</code> object 
 * 			using the non-default constructor. The ballots as well as the 
 * 			candidates' names must set in a different class. However the 
 * 			information about the candidates does not need to be explicitly 
 *			inputted, the information will be taken from the array ballots.
 * <p>
 * <b>Output:</b>	The name of the winning candidate if one exists, else the 
 * 			string "Tie". The output is given with 
 * 			<code>{@link #computeWinner()}</code>.
 * <p>
 * <b>Algorithm:</b>	In an election with <i>n</i> candidates/alternatives, 
 * 			all candidates will	be awarded <i>n</i> - 1 points each time that 
 * 			they are ranked first on a	ballot, <i>n</i> - 2 points every time 
 * 			they are ranked second, and so forth. The candidate with the most 
 * 			points wins.
 * <p>
 * <b>Precondition</b>	The array of ballots and the candidates names must be set.
 * <p>
 * <b>Relation with other classes</b> Inherits the ballots array  and the several
 * 				methods from VotingSystem.
 */
public class Borda extends VotingSystem{
	
	public Borda(){}

	public Borda(Ballot[] ballots){
		super(ballots);
	}

	protected void setVotes(){
		for(Ballot ballot : voterBallots){
			for(String candidate : ballot.toArray()){
				int score = candVotes.get(candidate) + 
						candVotes.size() - ballot.getPosition(candidate);
				
				candVotes.put(candidate, score);
			}
		}
	}
}//end Borda