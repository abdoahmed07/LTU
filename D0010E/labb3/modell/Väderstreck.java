package labb3.modell;

/**
 * represents the 4 directions (North, East, South, West) as an enum
 * 
 * each direction has an index that can be used to calculate the next position of the player
 * Norr = 0, Öster = 1, Söder = 2, Väster = 3
 */
public enum Väderstreck {
	NORR(0), ÖSTER(1), SÖDER(2), VÄSTER(3);
	
	private final int index;

	/**
	 * creates a new direction with the given index
	 * @param index the index of the direction
	 */
	Väderstreck(int index) {
		this.index = index;
	}
	/**
	 * @return the index of the direction
	 */
	public int index() {
		return this.index;
	}
}

//Done