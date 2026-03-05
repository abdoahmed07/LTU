package labb3.modell;

import java.util.ArrayList;
import java.util.Observable;


/**
 * represents a level in the game, which consists of a list of rooms and a current room where the player is.
 * 
 * the level is an Observable, 
 * which means that it can notify its observers (the GUI) when the current room changes (when the player moves to another room).
 */
public class Nivå extends Observable {

	private ArrayList<Rum> rum;
	private Rum nuvarandeRum;

	/**
	 * creates a new level with the given start room and list of rooms
	 * @param startrum the room where the player starts
	 * @param rum the list of rooms in the level
	 * @throws IllegalArgumentException if the start room or the list of rooms is null,
	 * if the start room is not included in the list of rooms,
	 * or if any two rooms in the list overlap.
	 * 
	 * @see Rum for more information about room overlap
	 */
	public Nivå(Rum startrum, ArrayList<Rum> rum) {
		if(startrum == null || rum == null)
			throw new IllegalArgumentException("Start room and rooms cannot be null");
		this.rum = rum;
		this.nuvarandeRum = startrum;
		
		if(!this.rum.contains(startrum))
			throw new IllegalArgumentException("Start room is not included in the rooms");

		for(int i = 0; i< rum.size();i++)
			for(int j = i +1;j < rum.size();j++)
				if (rum.get(i).getX() < rum.get(j).getX() + rum.get(j).getWall_width() 
					&& rum.get(i).getX() + rum.get(i).getWall_width() > rum.get(j).getX() 
					&& rum.get(i).getY() < rum.get(j).getY() + rum.get(j).getWall_height() 
					&& rum.get(i).getY() + rum.get(i).getWall_height() > rum.get(j).getY())
						throw new IllegalArgumentException("Two rooms overlap");
	}


	/**
	 * these two methods are getters for the level's parameters
	 * @return the values of the level's parameters
	 */
	public ArrayList<Rum> getRum() {
		return rum;
	}

	public Rum getNuvarandeRum() {
		return nuvarandeRum;
	}


	/**
	 * this method is called when the player wants to jump to another room in a certain direction.
	 * it checks if there is a passage in that direction from the current room, 
	 * and if there is, it updates the current room to the room that the passage leads to 
	 * and notifies the observers (the GUI) that the level has changed.
	 * @param väderstreck
	 */
	public void hoppaÅt(Väderstreck väderstreck) {
		if(nuvarandeRum.finnsUtgångÅt(väderstreck)){
			nuvarandeRum = nuvarandeRum.gångenÅt(väderstreck).getTill();
			setChanged();
			notifyObservers();
		}

	}
}

//Done