package labb3.modell;


/**
 * represents a passage between two rooms in a certain direction
 * 
 * each passage has a direction from the first room and a direction to the second room
 */
public class Gång {

	private final Rum från;
	private final Väderstreck riktningUtUrFrån;
	private final Rum till;
	private final Väderstreck riktningInITill;

	/**
	 * creates a new passage between two rooms in a certain direction
	 * 
	 * @param från	the room the passage starts from
	 * @param riktningUtUrFrån	the direction of the passage from the first room
	 * @param till	the room the passage leads to
	 * @param riktningInITill	the direction of the passage to the second room
	 */
	public Gång(Rum från, Väderstreck riktningUtUrFrån, 
				Rum till, Väderstreck riktningInITill) {

		this.från = från;
		this.riktningUtUrFrån = riktningUtUrFrån;
		this.till = till;
		this.riktningInITill = riktningInITill;
	}

	/**
	 * these four methods are getters for the passage's parameters
	 * @return the values of the passage's parameters
	 */
	public Rum getFrån() {
		return från;
	}
	public Väderstreck getRiktningUtUrFrån() {
		return riktningUtUrFrån;
	}
	public Rum getTill() {
		return till;
	}
	public Väderstreck getRiktningInITill() {
		return riktningInITill;
	}
}

//Done