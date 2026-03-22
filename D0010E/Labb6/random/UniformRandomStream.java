
package random;

import java.util.Random;

/**
 * Generates random numbers following a uniform distribution.
 * All values between the lower and upper bounds are equally likely.
 */
public class UniformRandomStream {

	// Random number generator
	private Random rand;
	// Lower bound of the distribution
	private double lower;
	// Width (upper - lower) of the distribution
	private double width;

	/**
	 * Creates a uniform random stream with a specific seed for reproducibility.
	 * 
	 * @param lower The lower bound (inclusive)
	 * @param upper The upper bound (inclusive)
	 * @param seed  The seed for the random number generator
	 */
	public UniformRandomStream(double lower, double upper, long seed) {
		rand = new Random(seed);
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * Creates a uniform random stream without a fixed seed.
	 * 
	 * @param lower The lower bound (inclusive)
	 * @param upper The upper bound (inclusive)
	 */
	public UniformRandomStream(double lower, double upper) {
		rand = new Random();
		this.lower = lower;
		this.width = upper - lower;
	}

	/**
	 * Generates the next random number following uniform distribution.
	 * 
	 * @return A random value between lower and upper bounds
	 */
	public double next() {
		return lower + rand.nextDouble() * width;
	}
}
