import javax.swing.JButton;

/**
 * CalculatorButton is an abstract class that serves as a base for different types of buttons in a calculator application. It extends the JButton class from the Java Swing library and includes a reference to the Situation object, which holds the current state of the calculator.
 * The constructor of the CalculatorButton class takes a string for the button's text and a Situation object, which it uses to initialize the button and set up an action listener that calls the transition method when the button is pressed. The transition method is an abstract method that must be implemented by subclasses to define how the calculator's state should change when the button is pressed.
 */
public abstract class CalculatorButton extends JButton {

    protected final Situation situation;

    public CalculatorButton(String text, Situation situation) {
        // Calls the constructor of the superclass JButton to set the button's text
        super(text);
        this.situation = situation;

        addActionListener(e -> transition());
    }

    protected abstract void transition();
}