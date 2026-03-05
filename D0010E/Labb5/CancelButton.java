/**
 * CancelButton is a class that represents the "C" button in a calculator application. It extends the CalculatorButton class and implements the transition method to define how the calculator's state should change when the cancel button is pressed.
 * When the cancel button is pressed, the transition method resets the display to "0", clears the left operand and binary operator, and changes the state of the calculator to Input1, allowing the user to start a new calculation.
 */
public class CancelButton extends CalculatorButton {

    public CancelButton(Situation situation) {
        super("C", situation);
    }

    @Override
    protected void transition() {
        situation.display.setText("0");
        situation.leftOperand = 0;
        situation.binaryOperator = null;
        situation.state = State.Input1;
    }
}