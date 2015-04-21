package hw4_group_1;

import hw4_group_1.Player.WhoseTurn;

public class Outcome {
	
	public WhoseTurn player;
	public Result result;
	public String guess;
	public int turnCount = 0;
	public boolean win = false;
	
	public Outcome(WhoseTurn player, String guess, Result result){
		this.player = player;
		this.guess = guess;
		this.result = result;
	}	
}
