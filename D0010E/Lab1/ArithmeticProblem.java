package Lab1;
/**
 * An ArithmeticProblem represents a simple arithmetic problem consisting
 * of two integer operands and an operator.  The operator is +, -, *, and /.
 * (This class is only appropriate for creating random, simple arithmetic problems!)
 */
public class ArithmeticProblem {
	
	private final int operand1, operand2;  // The two operands for the problem.
	private final char operator;           // Operator to be applied (+, -, *, or /).
	
	/**
	 * This constructor creates a random ArithmeticProblem using a given operator.  The
	 * problem is chosen to be fairly simple -- a problem that might be appropriate for
	 * a child just learning arithmetic.  For example, the operands are small non-negative
	 * numbers, the answer for a subtraction problem is non-negative, and the answer
	 * for a division problem is always an exact integer.
	 * @param op The operator for the problem.  Must be '+', '-', '*' or '/'.
	 * @throws IllegalArgumentException if op is not one of the legal operators.
	 */
	public ArithmeticProblem(char op) {
		if (op != '+' && op != '-' && op != '*' && op != '/')
			throw new IllegalArgumentException("Operator is not legal.");
		operator = op;
		switch (operator) {
		case '+':
			// Make a random addition problem with an answer <= 100.
			operand1 = (int)(101*Math.random());
			operand2 = (int)( (101-operand1)*Math.random());
			break;
		case '-':
			// Make a random subtraction problem with first operand between 9 and 100
			// and with a non-negative answer.
			operand1 = 9 + (int)(92*Math.random());
			operand2 = (int)((1+operand1)*Math.random());
			break;
		case '*':
			// Make a random multiplication problem with first operand <= 51
			// and second operand <= 12.
			operand1 = (int)(51*Math.random());
			operand2 = (int)(13*Math.random());
			break;
		default:  // op must be '/'
			// Make a random division problem where the denominator evenly divides
			// the numerator and the answer and the denominator are less than 13.
			int answer = (int)(13*Math.random());   // Select the answer first.
			operand2 = 1 + (int)(12*Math.random()); // Denominator must be > 0.
			operand1 = operand2*answer;
			break;
		}
	}
	
	/**
	 * Return a string representing the problem.  The string has a form
	 * such as "What is 19 + 43 ?  ".
	 */
	public String getQuestion() {
		return String.format("What is %d %s %s ?", operand1, operator, operand2);
	}
	
	/**
	 * Returns the correct answer for this problem.
	 */
	public int getAnswer() {
		switch (operator) {
		case '+':
			return operand1 + operand2;
		case '-':
			return operand1 - operand2;
		case '*':
			return operand1 * operand2;
		default: // operator must be '/'
			return operand1 / operand2;
		}
	}
	
}

