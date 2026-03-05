/**
 * DigitButton is a class that represents a button for digits (0-9) in a calculator application. It extends the CalculatorButton class and implements the transition method to define how the calculator's state should change when a digit button is pressed.
 * When a digit button is pressed, the transition method checks the current state of the calculator and updates the display accordingly. If the state is Input1, it appends the digit to the current display value or replaces it if the current value is "0". If the state is OpReady, it sets the display to the digit and changes the state to Input2. If the state is Input2, it appends the digit to the current display value or replaces it if the current value is "0". If the state is HasResult, it resets the left operand and binary operator, sets the display to the digit, and changes the state to Input1.
 */
public class DigitButton extends CalculatorButton {

    private final int digit;

    public DigitButton(int digit, Situation situation) {
        super("" + digit, situation);
        this.digit = digit;
    }

    @Override
    protected void transition() {
        String current = situation.display.getText();

        switch (situation.state) {
            case Input1:
                if (current.equals("0")) situation.display.setText("" + digit);
                else situation.display.setText(current + digit);
                break;

            case OpReady:
                situation.display.setText("" + digit);
                situation.state = State.Input2;
                break;

            case Input2:
                if (current.equals("0")) situation.display.setText("" + digit);
                else situation.display.setText(current + digit);
                break;

            case HasResult:
                situation.leftOperand = 0;
                situation.binaryOperator = null;
                situation.display.setText("" + digit);
                situation.state = State.Input1;
                break;
        }
    }
}