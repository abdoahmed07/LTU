/**
 * State enum represents the different states of a calculator or similar application.
 * The states include:
 * - Input1: The user is entering the first operand.
 * - OpReady: The user has entered the first operand and selected a binary operator, waiting for the second operand.
 * - Input2: The user is entering the second operand after selecting a binary operator.
 * - HasResult: The calculator has computed a result and is displaying it, waiting for the next user action.
 */
public enum State {
    Input1, OpReady, Input2, HasResult
}