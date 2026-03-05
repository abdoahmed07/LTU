package labb3.kontroll;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import labb3.modell.Nivå;
import labb3.modell.Väderstreck;

/**
 * this class listens for key presses and tells the level to move the player in the corresponding direction when a key is pressed.
 * It implements the KeyListener interface from the Java library, which allows it to listen for key events (key presses, key releases, and key types).
 * We only use the keyPressed method to react to key presses, but we have to implement the other two methods (keyTyped and keyReleased) because they are part of the KeyListener interface.
 */
public class Tangentbordslyssnare implements KeyListener {
	private Nivå enNivå;

	public Tangentbordslyssnare(Nivå enNivå) {
		this.enNivå = enNivå;
	}

	@Override
	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_W)
			enNivå.hoppaÅt(Väderstreck.NORR);
		else if (key == KeyEvent.VK_D)
			enNivå.hoppaÅt(Väderstreck.ÖSTER);
		else if (key == KeyEvent.VK_S)
			enNivå.hoppaÅt(Väderstreck.SÖDER);
		else if (key == KeyEvent.VK_A)
			enNivå.hoppaÅt(Väderstreck.VÄSTER);
		

	}

	@Override
	public void keyTyped(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyTyped finns i
		// gränssnittet KeyListener.
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// Används inte men måste implementeras eftersom keyReleased finns i
		// gränssnittet KeyListener.
	}
}

//Done
