package hw4_group_1;

import hw4_group_1.Player.WhoseTurn;

import java.util.ArrayList;
import java.util.Scanner;

public class Client {
	
	private static String input;
	private static String guess;
	private static String mode;
	private static GameController game_controller;
	private static Scanner keyboard;
	
	public static void main (String args[]){
		
		Outcome outcome = null;
		keyboard = new Scanner(System.in);
		int correct = 0;
		System.out.println("Welcome to Master the Mind!\n");
		
		while(correct ==  0)
		{
			System.out.println("Enter a four-digit number(no repeats) for the computer to guess: ");
			input = keyboard.nextLine();
			correct  = check(input);
			if (correct == 0) 
				System.out.println("Invalid input!");
		}
		
		game_controller = GameController.getInstance(input);

		while((outcome = game_controller.decideTurn()) != null)
		{
			//Human Guesses 	
			if (outcome.player == WhoseTurn.HUMAN )
			{
				System.out.println("\nYour turn!");
								
				System.out.println("Number of digits that are correct AND in the correct place = " + outcome.result.getCorrectPlaces());
				System.out.println("Number of digits that are correct BUT in the wrong place = " + outcome.result.getWrongPlaces());
				
				if (outcome.win == true) {
					System.out.println("You win!!!!!");
					break;
				}
			}
			
			//Computer guesses
			else if( outcome.player == WhoseTurn.COMPUTER)
			{
				System.out.println("\nComputer's turn...");
								
				System.out.println("Number of digits that are correct AND in the correct place = " + outcome.result.getCorrectPlaces());
				System.out.println("Number of digits that are correct BUT in the wrong place = " + outcome.result.getWrongPlaces());
				
				if (outcome.win == true) {
					System.out.println("Computer won...");
					break;
				}
			}
		}
		keyboard.close();
	}//end of main
	
	static String getGuess(){
		int correct = 0;
		while( correct == 0)
		{
			System.out.println("Enter your four-digit guess:");
			guess = keyboard.nextLine();
			correct  = check(guess);
		}
		return guess;
	}
	
	static String getInput()
	{
		return input;
	}
	
	static String getMode(){
		return mode;
	}
	
	public static int check(String str)
	{
		int len = str.length();
		
		int i = 0;
		int j = 0;
		
		for(i= 0; i < len; i++ )
		{
			for ( j = i + 1 ; j < len; j++)
			{
				if ( str.charAt(i) == str.charAt(j))
				{
					System.out.println("repeated number:" + str.charAt(i) + "\n   TRY AGAIN\n");
					return 0; // input has a repeating number. - bad 
					
				}
			}
		}
		return 1;
	}//end of check()
	
	
	
}//end of client class
