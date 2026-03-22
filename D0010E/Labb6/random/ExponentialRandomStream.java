package random;

import java.util.Random;

/**
 * Generates random numbers following an exponential distribution.
 * This is commonly used for modeling time intervals between events (like
 * customer arrivals).
 */
public class ExponentialRandomStream {

	// Random number generator
	private Random rand;
	// Rate parameter (lambda) for the exponential distribution
	private double lambda;

	/**
	 * Creates an exponential random stream with a specific seed for
	 * reproducibility.
	 * 
	 * @param lambda The rate parameter for the exponential distribution
	 * @param seed   The seed for the random number generator
	 */
	public ExponentialRandomStream(double lambda, long seed) {
		rand = new Random(seed);
		this.lambda = lambda;
	}

	/**
	 * Creates an exponential random stream without a fixed seed.
	 * 
	 * @param lambda The rate parameter for the exponential distribution
	 */
	public ExponentialRandomStream(double lambda) {
		rand = new Random();
		this.lambda = lambda;
	}

	/**
	 * Generates the next random number following exponential distribution.
	 * 
	 * @return A random value from the exponential distribution
	 */
	public double next() {
		return -Math.log(rand.nextDouble()) / lambda;
	}
}
