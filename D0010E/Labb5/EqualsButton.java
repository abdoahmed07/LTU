/**
 * The EqualsButton class represents the "=" button in a calculator application. It extends the CalculatorButton class and defines the behavior of the button when it is pressed.
 * When the button is pressed, it checks the current state of the calculator and performs the appropriate action based on the state. If the state is Input2, it applies the selected binary operator to the left operand and the current value displayed, updates the display with the result, and changes the state to HasResult. If the state is Input1, OpReady, or HasResult, it does nothing. If any exception occurs during the process (such as a NumberFormatException or an ArithmeticException), it resets the calculator to its initial state.
 */
public class EqualsButton extends CalculatorButton {

    public EqualsButton(Situation situation) {
        super("=", situation);
    }

    @Override
    protected void transition() {
        try {
            // Parses the current value displayed on the calculator as an integer. This is necessary to perform the calculation when the equals button is pressed.
            int currentValue = Integer.parseInt(situation.display.getText()); 

            switch (situation.state) {
                case Input2:
                    int result = situation.binaryOperator.apply(situation.leftOperand, currentValue);
                    situation.display.setText("" + result);
                    situation.leftOperand = result;
                    situation.state = State.HasResult;
                    break;

                case Input1:
                case OpReady:
                case HasResult:
                    // do nothing
                    break;
            }
        } catch (Exception ex) {
            situation.display.setText("0");
            situation.leftOperand = 0;
            situation.binaryOperator = null;
            situation.state = State.Input1;
        }
    }
}