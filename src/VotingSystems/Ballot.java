package VotingSystems;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Warren Godone-Maresca
 * 
 * <b>B A L L O T</b>
 *
 * <p>
 * <b>Purpose:</b> To hold a ranking of candidates/alternatives as an array of 
 * 					strings where the indices also serve as the ranking.
 * <p>
 * <b>Input:</b>	The names of the candidates in order of ranking as an array.
 * <p>
 * <b>Output:</b>	None.
 */

public class Ballot {
	/**
	 * Holds what will be the actual ballot. Each string will be the name of each
	 * candidate and the index will be the ranking of that candidate.
	 */
	private List<String> theBallot;

	/**
	 * Default constructor which instantiates the ballot.
	 */
	public Ballot(){
		theBallot = new ArrayList<String>();
	}

	/**
	 * Setups a ballot with the candidates names in order of ranking.
	 * 
	 * @param candidates	The candidates to be voted on in order preference. 
	 * 						Lower index values in the array correspond with 
	 * 						higher preferences.
	 */
	public Ballot(String[] candidates){
		this();

		setBallot(candidates);
	}

	/**
	 * Setups a ballot with the candidates names in order of ranking.
	 * 
	 * @param candidates	The candidates to be voted on in order preference. 
	 * 						Lower index values in the array correspond with 
	 * 						higher preferences.
	 */
	public Ballot(ArrayList<String> candidates){
		this();

		setBallot(candidates);
	}

	public Ballot(String candidates){
		this();

		setBallot(candidates);
	}

	/**
	 * Sets the ballot with the candidates names in order of ranking. 
	 * <p>
	 * <b>Note:</b> This method initializes the ballot. If the ballot is 
	 * initialized, Candidates must be added with either 
	 * <code>addCandidate(String)</code> or <code>addCandidates(String[]).
	 * 
	 * @param ballot	The candidates to be voted on in order preference. 
	 * 					Lower index values in the array corresponds with
	 * 					higher (more preferred) preferences (candidates).
	 */
	public void setBallot(String[] ballot){
		theBallot.removeAll(theBallot);

		for(String candidate : ballot){
			if(!theBallot.contains(candidate))
				theBallot.add(candidate); //Sets the ballot to the array passed
		}									//in the parameter.
	}

	public void setBallot(ArrayList<String> candidates){
		setBallot(candidates.toArray(new String[candidates.size()]));
	}

	/**
	 * Sets the ballot with the candidates names from a single string in order 
	 * of listing. Candidates must be separated by a comma (,), semicolon (;),
	 * forward slash (/), and/or greater than (>). However, within the same line,
	 * candidates must be separated by the same character. Trailing and leading 
	 * whitespace will be removed from candidates' names.
	 * 
	 * <p>
	 * <b>Note:</b> This method initializes the ballot. If the ballot is 
	 * initialized, Candidates must be added with either 
	 * <code>addCandidate(String)</code> or <code>addCandidates(String[]).
	 * 
	 * @param ballot	The candidates to be voted on in order preference. 
	 * 					Lower index values in the array corresponds with
	 * 					higher (more preferred) preferences (candidates).
	 */
	public void setBallot(String ballot){
		theBallot.removeAll(theBallot);

		int index = -1; //The index for searching the candidates string.

		char[] separators = {',', ';', '>', '/'};

		do {
			int newIndex = -1;

			for(char c : separators)
				newIndex = Math.max(ballot.indexOf(c, index + 1), newIndex);

			String candidate;
			
			if(newIndex != -1)
				candidate = ballot.substring(index + 1, newIndex).trim();
			else
				candidate = ballot.substring(index + 1).trim();
			
			if(!theBallot.contains(candidate))
				theBallot.add(candidate);

			index = newIndex;

		} while(index != -1);
	}

	/**
	 * Returns the name of the candidate at the specified index.
	 * 
	 * @param index	The index of the candidate whose name is to be returned. 
	 * <b>Note:</b> this method will return the candidate with index <i>n</i>, 
	 * not the ranking/position <i>n</i>. To get the candidate with that ranking,
	 * not index, use <i>n</i> + 1 as the argument.
	 * 
	 * @return	The name of the candidate whose index is <code>index</code> on
	 * 			the ballot, or an empty string if <code>index</code> is out of 
	 * 			bounds.
	 */
	public String getCandidate(int index){
		try {
			return theBallot.get(index); //Returns the candidate with specified index.
		} catch(IndexOutOfBoundsException e){
			return "";
		}
	}

	/**
	 * Returns the length of the ballot which is also the number of candidates.
	 * 
	 * @return The length of the ballot / number of candidates.
	 */
	public int getBallotLength(){
		return theBallot.size();
	}

	/**
	 * Given a candidate's name, they will be removed from the ballot and then
	 * array will be resized so that there aren't any null elements. Note, if the
	 * index in the array of the candidate to be eliminated is <i>i</i>, then the
	 * index of all candidates with  index greater than <i>i</i> will be 
	 * decremented.
	 * <p>
	 * However, if no candidate's name equals the name passed in the argument, 
	 * then no changes will be made. 
	 * 
	 * @param candidate The name of the candidate to be eliminated.
	 */
	public void eliminateCandidate(String candidate){
		if(theBallot.contains(candidate))
			theBallot.remove(getIndex(candidate));
	}

	/**
	 * Given a candidate's index, they will be removed from the ballot and then
	 * array will be resized so that there aren't any null elements. Note, if 
	 * the index in the array of the candidate to be eliminated is <i>i</i>, then
	 * the index of all candidates with index greater than <i>i</i> will be 
	 * decremented. <b>Note:</b> this method will remove the candidate with index
	 * <i>i</i>, not the ranking <i>i</i>. To remove the candidate with that 
	 * ranking, not index, use <i>i</i> + 1 in the argument.
	 * 
	 * @param index The index of the candidate to be eliminated.
	 */
	public void eliminateCandidate(int index){
		theBallot.remove(index);
	}


	/**
	 * Returns the index of the candidate whose name equals the string passed
	 * in the argument.
	 * 
	 * @param candidate	The name of candidate whose name is to be given.
	 * @return	The candidates position (NOT position which equals index + 1) 
	 * if the string matches a candidates name, else -1.
	 */
	public int getIndex(String candidate){
		return theBallot.indexOf(candidate);
	}

	/**
	 * Returns the position of the candidate whose name equals the string passed
	 * in the argument.
	 * 
	 * @param candidate	The name of candidate whose name is to be given.
	 * @return	The candidates position (NOT index which equals position - 1) 
	 * if the string matches a candidates name, else -1.
	 */
	public int getPosition(String candidate){
		return getIndex(candidate) + 1;
	}

	/**
	 * Returns the ballot as a list of strings.
	 * 
	 * @return The ballot as a lit of strings.
	 */
	public List<String> toList(){
		return theBallot;
	}

	/**
	 * Returns the ballot as an array of strings.
	 * 
	 * @return The ballot as an array of strings.
	 */
	public String[] toArray(){
		return theBallot.toArray(new String[theBallot.size()]);
	}
} //end Ballot
