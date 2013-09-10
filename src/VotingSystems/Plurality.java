package VotingSystems;
/**
 * 
 * @author Warren Godone-Maresca
 * 
 * <p>
 *					<b>Plurality Voting</b>
 * <p>
 * <b>Purpose:</b> To compute the winning candidate using the plurality voting 
 * 			method from a set of ranked ballots.
 * <p>
 * <b>Input:</b>	The ballots as an array of the <code>Ballot</code> object 
 * 			using the non-default constructor. The ballots as well as the 
 * 			candidates' names must set in a different class. However the 
 * 			information about the candidates does not need to be explicitly 
 * 			inputted, the information will be taken from the array ballots.
 * <p>
 * <b>Output:</b>	The name of the winning candidate if one exists, else the 
 * 			string "Tie". The output is given with 
 * 			<code>{@link #computeWinner()}</code>.
 * <p>
 * <b>Algorithm:</b>	The candidate with the most first place votes wins.
 */
public class Plurality extends VotingSystem{
	/**
	 * Default constructor which doesn't do anything.
	 */
	public Plurality(){}
	
	/**
	 * Will execute the constructor of <code>VotingSystem</code> which 
	 * instantiates the ballots and candidates.
	 * 
	 * @param ballots The ballots to be counted for determining the winner.
	 */
	public Plurality(Ballot[] ballots){
		super(ballots);
	}

	
	/**
	 * Awards one vote to each candidate every time they are ranked first on a 
	 * ballot.
	 */
	protected void setVotes(){
		for(Ballot ballot : voterBallots){
			candVotes.put(ballot.getCandidate(0), 
					candVotes.get(ballot.getCandidate(0)) + 1);
		}
	}
} //end Plurality
