package hw4_group_1;

import java.util.Random;


public class GameController {
	public int currentRound;
	public Human human;
	public Computer computer;
	private boolean humanTurn;
	private static GameController instance;
	private static String humanSecret, computerSecret;
	
	/* Class constructor
	 * Randomly decide who goes first
	 * @param humanActualNumber - the number set by the human for the computer to guess
	 */
	private GameController(String humanActualNumber) {
		currentRound = 1;
		Random r = new Random();
		humanTurn = r.nextBoolean();
		human = Human.getInstance();
		computer = Computer.getInstance();
		humanSecret = humanActualNumber;
		computerSecret = Computer.generateSecret();
	}
	
	/* 
	 * Creates and returns instance if it doesn't already exist, and returns instance otherwise.
	 */
	public static GameController getInstance(String humanActualNumber) {
		if (instance == null)
			instance = new GameController(humanActualNumber);
		return instance;
	}
	
	/* Decides whether it is the human's turn, or the computer's
	 * @return - Returns an array in the format: 
	 * 			[currentRound, numberCorrentInCorrectPosition, numberCorrectInWrongPosition]
	 * Note: If either player guesses the correct number, -1 is returned in the first index of the array returned.
	 */
	public Outcome decideTurn() {
		Outcome outcome;
		if (humanTurn) {
			humanTurn = false;
			outcome = human.playTurn(computerSecret);
		}
		else {
			humanTurn = true;
			outcome = computer.playTurn(humanSecret);
		}
		
		outcome.turnCount = currentRound;
		currentRound++;
		if(outcome.result.getCorrectPlaces() == 4)
			outcome.win = true;
		
		return outcome;
	}
}
