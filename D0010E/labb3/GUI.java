package labb3;

import java.util.Observable;
import java.util.Observer;

import javax.swing.JFrame;

import java.awt.Dimension;

import labb3.kontroll.Tangentbordslyssnare;
import labb3.modell.Nivå;
import labb3.vy.Målarduk;

/**
 * This class represents the graphical user interface of the game.
 * It creates a window and contains a Målarduk where the level is drawn.
 * It also listens for changes in the level so that it can repaint the window when the player moves to another room.
 * JFrame is a class from the Swing library that represents a window that can contain other components.
 * Observer is an interface from the Java library that allows this class to listen for changes in the Nivå (which is an Observable) 
 * and react to those changes by repainting the window.
 */
public class GUI extends JFrame implements Observer {

	// The place where the level will be drawn. 
	private Målarduk målarduk;

	/**
	 * this constructor creates a new GUI with the given level. It sets up the window and the drawing area.
	 * It also adds this GUI as an observer to the level so that it can repaint the window when the level changes (when the player moves to another room).
	 * @param enNivå the level that this GUI will display
	 */
	public GUI(Nivå enNivå) {

		// Tells the program to exit when the window is closed.
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// WE add this GUI as an observer to the level so that we can repaint the window when the level changes.
		enNivå.addObserver(this);

		// Creates the Målarduk and gives it the level to draw.
		// GUI doesnt draw anything itself, it just contains the Målarduk which does the drawing.
		målarduk = new Målarduk(enNivå);

		// Sets the preferred size of the Målarduk and 
		// adds a key listener to it so that we can move around in the level with the keyboard.
		målarduk.setPreferredSize(new Dimension(800, 600));
		målarduk.addKeyListener(new Tangentbordslyssnare(enNivå));

		// puts the drawing area on the window so its visable 
		// and packs the window so that it fits the preferred size of the drawing area.
		setContentPane(målarduk);
		pack();
		setLocationRelativeTo(null); // optional, centers window
		setVisible(true); // makes the window visible
		målarduk.requestFocusInWindow(); // Gives the keyboard focus to the drawing area so that it can receive key events.
	}


	/**
	 * this method is called when the level changes (when the player moves to another room).
	 * @param o the Observable that has changed (the level)
	 * @param arg an optional argument that can be passed by the Observable (not used in this case)
	 */
	@Override
	public void update(Observable o, Object arg) {
		this.målarduk.repaint();
	}
}

//Done