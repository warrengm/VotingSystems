package Main;


import java.util.ArrayList;
import java.util.Scanner;

import VotingSystems.*;

public class Poll {

	private final String COMMAND = "\\go";
	private VotingSystem system;

	public Poll(){
		System.out.println(
				"Enter 1 for instant runoff voting, 2 for the Borda Count, 3 for" +
				"\n the Condorcet Method, and any other int for plurality voting");
		
		Scanner scanner = new Scanner(System.in);
		
		int choice = scanner.nextInt();
		
		//scanner.close();
		
		System.out.println(
				"Enter input now. Then enter " + COMMAND + " to compute results.");
		
		ArrayList<Ballot> b = parseInput();
		switch(choice){
			case 1 : system = new InstantRunoff(b.toArray(new Ballot[b.size()]));
			break;
			case 2: system = new Borda(b.toArray(new Ballot[b.size()]));
			break;
			case 3: system = new Condorcet(b.toArray(new Ballot[b.size()]));
			break;
			default: system = new Plurality(b.toArray(new Ballot[b.size()]));
		}
		
		System.out.println("WINNER: " + system.computeWinner());
		System.out.println(system.results());
	}

	private ArrayList<Ballot> parseInput(){
		ArrayList<Ballot> ballots = new ArrayList<Ballot>();

		Scanner input = new Scanner(System.in);

		String ballot;

		while((ballot = input.nextLine().trim()) != null){
			
			if(ballot.equalsIgnoreCase(COMMAND)){
				input.close();
				return ballots;
			}

			int n = 1; //Number of ballots in the line

			int index = ballot.indexOf(' ');

			if(index != - 1 && index < ballot.length() - 1){
				try{
					n = Integer.parseInt(ballot.substring(0, index));
					ballot = ballot.substring(index);
				} catch(NumberFormatException e){ 
					n = 1;//try removing this
				}	

			}

			for(int i = 0; i < n; i++){
				if(ballot.length() > 0)
					ballots.add(new Ballot(ballot));
			}
		}

		input.close();

		return ballots;

	}

	public static void main(String[] args){
		Poll p = new Poll();
	}

}
