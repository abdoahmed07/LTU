package carwash;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulation.EventQueue;
import simulation.Simulator;

/* To run and compile
javac carwash/*.java simulation/*.java random/*.java
java carwash.Main
 */

public class Main {
    public static void main(String[] args) {
        int fastStations = 2;
        int slowStations = 2;
        int maxQueue = 5;

        double closeTime = 100.0;
        double stopTime  = 120.0;

        double lambda = 2.0;
        int seed = 1234;
        // RandomStream.setSeed(seed);

        double fastMin = 2.8, fastMax = 4.6;
        double slowMin = 3.5, slowMax = 6.7;



        // Random streams
        ExponentialRandomStream arrival = new ExponentialRandomStream(lambda, seed);
        UniformRandomStream fastWash = new UniformRandomStream(fastMin, fastMax, seed);
        UniformRandomStream slowWash = new UniformRandomStream(slowMin, slowMax, seed);

        EventQueue q = new EventQueue();
        CarWashState state = new CarWashState(fastStations, slowStations, maxQueue);

        EventFactory factory = new EventFactory(arrival, fastWash, slowWash, closeTime, stopTime);
        factory.init(q, state);

        System.out.println("Fast machines: " + fastStations);
        System.out.println("Slow machines: " + slowStations);
        System.out.println("Fast distribution: (" + fastMin + ", " + fastMax + ")");
        System.out.println("Slow distribution: (" + slowMin + ", " + slowMax + ")");
        System.out.println("Exponential distribution with lambda = " + lambda);
        System.out.println("seed = " + seed);
        System.out.println("Max Queue size: " + maxQueue);

        state.printHeader();
        state.printRow(0.0, "Start", "");

        Simulator sim = new Simulator(q, state);
        sim.run();
    }
}