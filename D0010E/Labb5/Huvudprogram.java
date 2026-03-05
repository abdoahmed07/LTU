import javax.swing.JLabel;

/* To Run
javac *.java
java Huvudprogram
*/

/**
 * Huvudprogram class contains the main method which serves as the entry point for the calculator application.
 * It initializes the display JLabel, creates a Situation object to hold the state of the calculator
 * and then creates a GUI object to display the user interface.
 */
public class Huvudprogram {
    public static void main(String[] args) {
        JLabel display = new JLabel("0");
        Situation situation = new Situation(display);
        new GUI(situation);
    }
}