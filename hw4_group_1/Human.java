package hw4_group_1;

import java.util.ArrayList;

public class Human extends Player {
	private static Human instance;
	protected static ArrayList<String> previousGuesses;
	protected static ArrayList<Result> previousResults;
	/*
	 * Class constructor
	 * Assign actualNumber to the value that human set for the computer to guess.
	 */
	private Human() {
		previousResults = new ArrayList<Result>(10);
		previousGuesses = new ArrayList<String>(10);
	}
	
	/* 
	 * Creates and returns instance if it doesn't already exist, and returns instance otherwise.
	 */
	public static Human getInstance() {
		if (instance == null) {
			instance = new Human();
		}
		return instance;
	}
	
	// getters for the arraylists of previous guesses and results
	public ArrayList<String> getPreviousGuesses() {
		return previousGuesses;
	}
	
	public ArrayList<Result> getPreviousResults() {
		return previousResults;
	}
	
	/* 
	 * Adds to our array list of pair values, representing previous results
	 */
	public static void addToResults(Result p) {
		previousResults.add(p);
	}
	
	/* 
	 * Adds to our array list of string values, representing previous guesses
	 */
	public static void addToGuesses(String s) {
		previousGuesses.add(s);
	}

	/*
	 * Delegate the printing of instructions and retrieving input to Client
	 * Evaluate the guess we got from the client and return outcome info
	 */
	@Override
	public Outcome playTurn(String opponentSecret) {
		Human.printHumanLog();
		String guess = Client.getGuess();
		Result result = evaluate(guess, opponentSecret);
		addToGuesses(guess);
		addToResults(result);
		Outcome outcome = new Outcome(WhoseTurn.HUMAN, guess, result);
		return outcome;
	}
	
	public static void printHumanLog(){
		int n = previousGuesses.size();
		if(n == 0) return;
		for(int i = 0; i < n; i++){
			String guess = previousGuesses.get(i);
			Result result = previousResults.get(i);
			System.out.println("Your previous attempts:");
			System.out.printf("Guess: %s\n\t%d in correct place\n\t%d in incorrect place", guess, result.getCorrectPlaces(), result.getWrongPlaces());
			//System.out.println("");
		}
	}
}
