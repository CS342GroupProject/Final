package hw4_group_1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.ListIterator;

public class Computer extends Player {
	private static int secretLength = 4;
	private static ArrayList<String> solutions;
	private static Computer instance;

	private Computer() {
	}

	public static Computer getInstance() {
		if (instance == null) {
			instance = new Computer();
			instance.init();
		}
		return instance;
	}

	// initialization code (in case multiple games are played in one session)
	public void init() {
		solutions = Computer.generateSolutions();
	}

	// get 4 random non-repeating digits from 0..9 as a string
	public static String generateSecret() {
		ArrayList<String> numbers = new ArrayList<String>();
		String secret = "";

		for (int i = 0; i < 10; i++)
			numbers.add(0, String.format("%d", i));

		Collections.shuffle(numbers);
		numbers = new ArrayList<String>(numbers.subList(0, secretLength));

		for (String c : numbers)
			secret += c;

		return secret;
	}

	// construct the initial solution set
	public static ArrayList<String> generateSolutions() {
		ArrayList<String> numbers = new ArrayList<String>();
		for (int i = 0; i < 10; i++)
			numbers.add(0, String.format("%d", i));
		solutions = getPermutations(secretLength, numbers);
		Collections.shuffle(solutions);
		return solutions;
	}

	// gather all k-permutations of a string (represented as a list of
	// single-character strings) in a list of strings
	public static ArrayList<String> getPermutations(int k, ArrayList<String> numbers) {
		ArrayList<String> suffixes = new ArrayList<String>();
		if (k > 0) {
			for (String s : getPermutations(k - 1, numbers))
				for (String c : numbers)
					if (!s.contains(c))
						suffixes.add(0, s + c);
		} else
			suffixes.add("");
		return suffixes;
	}

	// evaluate the first guess from solutions against the opponent secret
	// eliminate all solutions that do not evaluate the same against our guess
	// return the guess
	@Override
	public Outcome playTurn(String opponentSecret){
		String newGuess = solutions.get(0);
		Result newResult = evaluate(newGuess, opponentSecret);
		ListIterator<String> iter = solutions.listIterator();
		while (iter.hasNext()) {
			if (!evaluate(iter.next(), newGuess).equals(newResult))
				iter.remove();
		}
		Outcome outcome = new Outcome(WhoseTurn.COMPUTER, newGuess, newResult);
		return outcome;
	}
}
