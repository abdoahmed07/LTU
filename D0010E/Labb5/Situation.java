// JLabel is a component in Java Swing that can display a short string or an image icon.
// It is used in this code to display the current value on the calculator's screen.
import javax.swing.JLabel;

/**
 * Situation class represents the current state of a calculator application. It holds the following information:
 * - state: The current state of the calculator, represented by the State enum (Input1, OpReady, Input2, HasResult).
 * - leftOperand: The value of the left operand that the user has entered or that is currently being processed.
 * - display: A JLabel component that shows the current value on the calculator's screen.
 * - binaryOperator: The currently selected binary operator (e.g., +, -, *, /) that will be applied to the left operand and the next operand.
 */
public class Situation {
    public State state;
    public int leftOperand;
    public JLabel display;
    public BinOpButton binaryOperator;

    public Situation(JLabel display) {
        // Storing a reference to the display JLabel
        this.display = display;
        // Sets the state to Input1, meaning waiting for the first operand
        this.state = State.Input1;
        this.leftOperand = 0; // Initializes the left operand to 0
        this.binaryOperator = null; // Initializes the binary operator to null, meaning no operator is selected
        this.display.setText("0"); // Sets the initial text of the display to "0"
    }
}