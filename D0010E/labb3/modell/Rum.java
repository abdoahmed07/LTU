package labb3.modell;

import java.awt.Color;

import labb3.GlobalaKonstanter;

public class Rum {

	/**
	 * Represents a room in the game
	 * 
	 * x- and y-coordinates represent the position of the upper left corner of the room
	 * wall height and width represent the size of the room
	 * floor color represents the color of the floor of the room
	 * 
	 * each room has an array of passages that lead to other rooms in the four directions (North, East, South, West)
	 * the index of the passage in the array corresponds to the direction of the passage (Norr = 0, Öster = 1, Söder = 2, Väster = 3)
	 * if there is no passage in a certain direction, the value at that index is null
	 */
	private int x;
	private int y;
	private int höjd;
	private int bredd;
	private Color golvfärg;

	private Gång[] gångar = new Gång[GlobalaKonstanter.ANTAL_VÄDERSTRECK];

	/**
	 * creates a new room with the given parameters
	 * @param golvfärg
	 * @param bredd
	 * @param höjd
	 * @param övX
	 * @param övY
	 */
	public Rum(Color golvfärg, int bredd, int höjd, int övX, int övY) {
		this.golvfärg = golvfärg;
		this.höjd = höjd;
		this.bredd = bredd;
		this.x = övX;
		this.y = övY;

	}

	/**
	 * these five methods are getters for the room's parameters
	 * @return the values of the room's parameters
	 */
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public int getWall_height() {
		return höjd;
	}
	public int getWall_width() {
		return bredd;
	}
	public Color getFloor_color() {
		return golvfärg;
	}

	/**
	 * this method checks if there is a passage in the given direction
	 * @param väderstreck is the direction to check for a passage
	 * @return true if there is a passage in the given direction, false otherwise
	 */
	public boolean finnsUtgångÅt(Väderstreck väderstreck) {
		return gångar[väderstreck.index()] != null;
	}

	/**
	 * retuens the passage in the given direction if it exists, otherwise throws an exception
	 * @param väderstreck is the direction to get the passage from
	 * @return the passage in the given direction if it exists
	 * @throws IllegalArgumentException if there is no passage in the given direction
	 */
	public Gång gångenÅt(Väderstreck väderstreck) {
		if (gångar[väderstreck.index()] == null) 
			throw new IllegalArgumentException("There is no passage in that direction");
		return gångar[väderstreck.index()];
	}


	/**
	 * this method connects two rooms with a passage in the given directions
	 * @param från is the room the passage starts from
	 * @param riktningUtUrFrån is the direction of the passage from the first room
	 * @param till is the room the passage leads to
	 * @param riktningInITill is the direction of the passage to the second room
	 * @throws IllegalArgumentException if the rooms are the same, if there is already a passage in the given direction from either room, or if any of the parameters are null
	 * if the passage is successfully created, it is added to the array of passages for both rooms
	 */
	public static void kopplaIhop(Rum från, Väderstreck riktningUtUrFrån,
			Rum till, Väderstreck riktningInITill) {
		if (från == null || till == null || riktningUtUrFrån == null || riktningInITill == null)
			throw new IllegalArgumentException("The rooms and directions cannot be null");
		if(från == till)
			throw new IllegalArgumentException("The rooms cannot be the same");
		if (från.finnsUtgångÅt(riktningUtUrFrån))
			throw new IllegalArgumentException("There is already a passage in that direction from the first room");
		if (till.finnsUtgångÅt(riktningInITill))
			throw new IllegalArgumentException("There is already a passage in that direction from the second room");
		Gång gångFrån = new Gång(från, riktningUtUrFrån, till, riktningInITill);
		Gång gångTill = new Gång(till, riktningInITill, från, riktningUtUrFrån);
		från.gångar[riktningUtUrFrån.index()] = gångFrån;
		till.gångar[riktningInITill.index()] = gångTill;
	}
}

//Done