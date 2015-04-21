package hw4_group_1;

public class Result {
	private int correctPlace;
	private int wrongPlace;

	// constructor
	public Result() {
		this.correctPlace = this.wrongPlace = 0;
	}// end of constructor

	// getter for correctPlaces
	public int getCorrectPlaces() {
		return this.correctPlace;
	}// end of getCorrectPlaces()

	// getter for wrongPlaces
	public int getWrongPlaces() {
		return this.wrongPlace;
	}// end of getWrongPlaces()

	// setter for correctPlace, which simply increments the count
	public void incrementCorrecPlaces() {
		this.correctPlace++;
	}// end of incrementCorrectPlaces()

	// setter for wrongPlaces, which also simply increments the count
	public void incrementWrongPlaces() {
		this.wrongPlace++;
	}// end of incrementWrongPlaces()

	// equality check for objects of Result type
	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Result))
			return false;
		return correctPlace == ((Result) obj).correctPlace
				&& wrongPlace == ((Result) obj).wrongPlace;
	}
}
