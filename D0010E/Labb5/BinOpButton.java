/**
 * BinOpButton is a class that represents a button for binary operations (addition, subtraction, multiplication, division) in a calculator application. It extends the CalculatorButton class and implements the transition method to define how the calculator's state should change when a binary operator button is pressed.
 * When a binary operator button is pressed, the transition method checks the current state of the calculator and updates the left operand, binary operator, and state accordingly. If the state is Input1 or HasResult, it sets the left operand to the current value displayed, sets the binary operator to this button, and changes the state to OpReady. If the state is OpReady, it allows the user to change their mind about the operator by updating the binary operator to this button. If the state is Input2, it computes the result of the previous operation, updates the display with the result, sets the left operand to the result, updates the binary operator to this button, and changes the state to OpReady. If any ArithmeticException occurs (such as division by zero), it resets the calculator to its initial state.
 * The apply method takes two integers as input and applies the binary operator represented by this button to
 */
public class BinOpButton extends CalculatorButton {

    private final char op;

    public BinOpButton(char op, Situation situation) {
        super("" + op, situation);
        this.op = op;
    }

    /**
     * Applies the binary operator represented by this button to the given operands.
     * @param a the left operand
     * @param b the right operand
     * @return the result of applying the operator to the operands
     * @throws ArithmeticException if division by zero is attempted
     * @throws IllegalStateException if an unknown operator is encountered
     */
    public int apply(int a, int b) {
        switch (op) {
            case '+': return a + b;
            case '-': return a - b;
            case '*': return a * b;
            case '/':
                if (b == 0) throw new ArithmeticException("Division by zero");
                return a / b; // integer division
            default:
                throw new IllegalStateException("Unknown operator: " + op);
        }
    }

    @Override
    protected void transition() {
        try {
            int currentValue = Integer.parseInt(situation.display.getText());

            switch (situation.state) {
                case Input1:
                case HasResult:
                    situation.leftOperand = currentValue;
                    situation.binaryOperator = this;
                    situation.state = State.OpReady;
                    break;

                case OpReady:
                    // user changed their mind about operator
                    situation.binaryOperator = this;
                    break;

                case Input2:
                    // compute previous op first, then set new op
                    int result = situation.binaryOperator.apply(situation.leftOperand, currentValue);
                    situation.display.setText("" + result);
                    situation.leftOperand = result;
                    situation.binaryOperator = this;
                    situation.state = State.OpReady;
                    break;
            }
        } catch (ArithmeticException ex) {
            // simple reset on error
            situation.display.setText("0");
            situation.leftOperand = 0;
            situation.binaryOperator = null;
            situation.state = State.Input1;
        }
    }
}