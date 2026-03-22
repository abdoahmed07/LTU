package carwash;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulation.EventQueue;
import simulation.Simulator;

/**
 * Main entry point for the car wash simulation.
 * This program simulates a car wash facility with two types of machines (fast
 * and slow).
 * Cars arrive randomly, wait in a queue if necessary, and leave after being
 * washed.
 * 
 * To run and compile:
 * javac carwash/*.java simulation/*.java random/*.java
 * java carwash.Main
 */
public class Main {
    /**
     * Runs the car wash simulation with predefined parameters.
     */
    public static void main(String[] args) {
        // Configuration: number of washing machines
        int fastStations = 2; // Number of fast machines
        int slowStations = 2; // Number of slow machines
        int maxQueue = 5; // Maximum queue size

        // Configuration: simulation timing
        double closeTime = 15.0; // When to stop accepting new arrivals
        double stopTime = 15.0; // When to end the simulation

        // Configuration: random distributions
        double lambda = 2.0; // Rate parameter for exponential arrivals
        int seed = 1234; // Seed for random number generation

        // Configuration: wash time ranges (minutes)
        double fastMin = 2.8, fastMax = 4.6; // Fast machine wash time range
        double slowMin = 3.5, slowMax = 6.7; // Slow machine wash time range

        // Create random streams for generating random numbers
        ExponentialRandomStream arrival = new ExponentialRandomStream(lambda, seed);
        UniformRandomStream fastWash = new UniformRandomStream(fastMin, fastMax, seed);
        UniformRandomStream slowWash = new UniformRandomStream(slowMin, slowMax, seed);

        // Create the simulation components
        EventQueue q = new EventQueue(); // Event queue
        CarWashState state = new CarWashState(fastStations, slowStations, maxQueue); // State

        // Create the simulation view and link it to the state
        SimulationView view = new SimulationView(); 
        state.setView(view); // Set the view in the state for updates

        // Create factory and initialize with first events
        EventFactory factory = new EventFactory(arrival, fastWash, slowWash, closeTime, stopTime);
        factory.init(q, state);

        // Print configuration information
        System.out.println("Fast machines: " + fastStations);
        System.out.println("Slow machines: " + slowStations);
        System.out.println("Fast distribution: (" + fastMin + ", " + fastMax + ")");
        System.out.println("Slow distribution: (" + slowMin + ", " + slowMax + ")");
        System.out.println("Exponential distribution with lambda = " + lambda);
        System.out.println("seed = " + seed);
        System.out.println("Max Queue size: " + maxQueue);

        // Print the header and start row
        view.printHeader();
        view.printRow(0.0, "Start", "",
            state.getFreeFast(),
            state.getFreeSlow(),
            state.getTotalIdleTime(),
            state.getTotalQueueTime(),
            state.getQueue().size(),
            state.getTotalRejected());

        // Run the simulation
        Simulator sim = new Simulator(q, state);
        sim.run();
    }
}