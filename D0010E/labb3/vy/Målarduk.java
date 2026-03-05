package labb3.vy;

import java.awt.Graphics;

import javax.swing.JPanel;

import static labb3.GlobalaKonstanter.*;
import labb3.modell.Gång;
import labb3.modell.Nivå;
import labb3.modell.Rum;
import labb3.modell.Väderstreck;
import labb3.verktyg.Punkt;
import java.awt.BasicStroke;
import java.awt.Graphics2D;

/**
 * this class is responsible for drawing the game on the window. 
 * It extends JPanel, which is a component that can be added to a JFrame to create a window.
 * It has a reference to the level (Nivå) that it will draw, and it overrides the paintComponent method to do the actual drawing.
 * It has helper methods to draw the rooms, the passages, and the marker for where the player is.
 * The paintComponent method is called automatically by the Swing library whenever the window needs to be redrawn (for example, when the player moves to another room).
 */
public class Målarduk extends JPanel {

	private final Nivå enNivå;

	public Målarduk(Nivå enNivå) {
		this.enNivå = enNivå;

		setBackground(MARKFÄRG);
        setFocusable(true);

	}

    /**
     * This method is called when the window needs to be redrawn (for example, when the player moves to another room).
     * We call the methods to draw the rooms, the passages, and the marker for where the player is.
     * @param g
     */
    @Override
	protected void paintComponent(Graphics g) {

        // This line clears the window and prepares it for drawing, 
        // using the background color we set in the constructor.
		super.paintComponent(g);

        // We loop through all the rooms in the level and call the method to draw each room.    
		for (Rum r : enNivå.getRum()) ritaRum(g, r);
    	
        // We loop through all the rooms again and call the method to draw the passages from each room.
    	for (Rum r : enNivå.getRum()) ritaGångarFrånRum(g, r);
    	
        // Call the method to draw the marker for where the player is.
		ritaMarkörFörVarAnvändarenÄr(g);

	}

    /**
     * This method draws a room on the window using the Graphics object and the parameters of the room.
     * Draws a filled rectangle for the floor and an outline for the walls.
     * @param g
     * @param ettRum
     */
    private void ritaRum(Graphics g, Rum ettRum) {
        Graphics2D g2 = (Graphics2D) g;
		int x = ettRum.getX();
        int y = ettRum.getY();
        int w = ettRum.getWall_width();
        int h = ettRum.getWall_height();

        g2.setColor(ettRum.getFloor_color());
        g2.fillRect(x, y, w, h);

        g2.setColor(VÄGGFÄRG);
        g2.setStroke(new BasicStroke(VÄGGTJOCKLEK));
        g2.drawRect(x, y, w, h);
	}

    /**
     * This method draws the passages from a room on the window using the Graphics object and the parameters of the room and the passages.
     * It loops through all the directions and checks if there is a passage in that direction. 
     * If there is, it calls the method to draw that passage.
     * @param g
     * @param ettRum
     */
	private void ritaGångarFrånRum(Graphics g, Rum ettRum) {
		for (Väderstreck r : Väderstreck.values()) 
            if (ettRum.finnsUtgångÅt(r)) {
                Gång gång = ettRum.gångenÅt(r);
                if (gång.getFrån() == ettRum) ritaGång(g, gång);
            }
    }

    /**
     * this Gets the base point for a passage from a room in a certain direction.
     * The base point is the point where the passage starts from the room.
     * @param ettRum
     * @param enRiktning
     * @return
     */
	private Punkt baspunkt(Rum ettRum, Väderstreck enRiktning) {
		int x = ettRum.getX();
        int y = ettRum.getY();
        int w = ettRum.getWall_width();
        int h = ettRum.getWall_height();
        
        int midX = x + w / 2;
        int midY = y + h / 2;
        
        switch (enRiktning) {
            case NORR:
                return new Punkt(midX, y);
            case SÖDER:
                return new Punkt(midX, y + h);
            case ÖSTER:
                return new Punkt(x + w, midY);
            case VÄSTER:
                return new Punkt(x, midY);
            default:
                throw new IllegalArgumentException("Unknown direction: " + enRiktning);
        }
	}

    /**
     * this Gets the pivot point for a passage from a room in a certain direction.
     * The pivot point is the point where the passage changes direction from the room to the next room.
     * @param ettRum
     * @param enRiktning
     * @return
     */
	private Punkt pivotpunkt(Rum ettRum, Väderstreck enRiktning) {
		Punkt bas = baspunkt(ettRum, enRiktning);
        int offset = 7;

        switch (enRiktning) {
            case NORR:
                return new Punkt(bas.x(), bas.y() - offset);
            case SÖDER:
                return new Punkt(bas.x(), bas.y() + offset);
            case ÖSTER:
                return new Punkt(bas.x() + offset, bas.y());
            case VÄSTER:
                return new Punkt(bas.x() - offset, bas.y());
            default:
                throw new IllegalArgumentException("Unknown direction: " + enRiktning);
        }
	}

    /**
     * this draws the actwal corridor between two rooms on the window using the Graphics object and the parameters of the passage.
     * It draws a line from the base point of the first room to the pivot point,
     * then a line from the pivot point to the pivot point of the second room,
     * and finally a line from the pivot point of the second room to the base point of the second room.
     * @param g
     * @param enGång
     */
	private void ritaGång(Graphics g, Gång enGång) {

        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(GÅNGFÄRG);
        g2.setStroke(new BasicStroke(VÄGGTJOCKLEK, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));

	    Rum från = enGång.getFrån();
        Rum till = enGång.getTill();

        Väderstreck ut = enGång.getRiktningUtUrFrån();
        Väderstreck in = enGång.getRiktningInITill();

        Punkt basFrån = baspunkt(från, ut);
        Punkt pivotFrån = pivotpunkt(från, ut);

        Punkt pivotTill = pivotpunkt(till, in);
        Punkt basTill = baspunkt(till, in);

	    g2.drawLine(basFrån.x(), basFrån.y(), pivotFrån.x(), pivotFrån.y());
	    g2.drawLine(pivotFrån.x(), pivotFrån.y(), pivotTill.x(), pivotTill.y());
	    g2.drawLine(pivotTill.x(), pivotTill.y(), basTill.x(), basTill.y());
	}

    /**
     * this draws a marker for where the player is on the window using the Graphics object and the parameters of the current room.
     * It draws a filled circle in the center of the current room.
     * @param g
     */
	private void ritaMarkörFörVarAnvändarenÄr(Graphics g) {
		Rum r = enNivå.getNuvarandeRum();

        int centerX = r.getX() + r.getWall_width() / 2;
        int centerY = r.getY() + r.getWall_height() / 2;

        int size = ANVÄNDARRADIE;

        g.setColor(ANVÄNDARFÄRG);
        g.fillOval(centerX - size , centerY - size , size * 2, size * 2);
	}
}

//Done