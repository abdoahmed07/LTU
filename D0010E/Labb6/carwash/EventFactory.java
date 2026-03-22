package carwash;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulation.EventQueue;

/**
 * Factory class responsible for creating and scheduling all events in the
 * simulation.
 * Handles arrivals, departures, closing, and stopping the simulation.
 */
class EventFactory {
    // Random stream for car arrival times (exponential distribution)
    private final ExponentialRandomStream arrivalStream;
    // Random stream for fast machine wash times (uniform distribution)
    private final UniformRandomStream fastWashStream;
    // Random stream for slow machine wash times (uniform distribution)
    private final UniformRandomStream slowWashStream;

    // The event queue where all events are stored
    private EventQueue queue;

    // Counter for assigning unique IDs to cars
    private int nextCarId = 0;

    // The time when the car wash stops accepting new arrivals
    private final double closeTime;
    // The time when the entire simulation stops
    private final double stopTime;

    /**
     * Creates a new event factory with the specified random streams and time
     * settings.
     * 
     * @param arrivalStream  Random stream for car arrivals
     * @param fastWashStream Random stream for fast machine wash times
     * @param slowWashStream Random stream for slow machine wash times
     * @param closeTime      Time when the car wash stops accepting arrivals
     * @param stopTime       Time when the simulation stops
     */
    EventFactory(
            ExponentialRandomStream arrivalStream,
            UniformRandomStream fastWashStream,
            UniformRandomStream slowWashStream,
            double closeTime,
            double stopTime) {
        this.arrivalStream = arrivalStream;
        this.fastWashStream = fastWashStream;
        this.slowWashStream = slowWashStream;
        this.closeTime = closeTime;
        this.stopTime = stopTime;
    }

    /**
     * Initializes the factory with the event queue and state.
     * Schedules the initial events: first arrival, closing event, and stop event.
     * 
     * @param queue The event queue
     * @param state The car wash state
     */
    void init(EventQueue queue, CarWashState state) {
        this.queue = queue;

        // Schedule the first arrival
        scheduleNextArrival(0.0);
        // Schedule when the car wash closes to new arrivals
        queue.add(new CloseEvent(closeTime));
        // Schedule when the simulation stops
        queue.add(new StopEvent(stopTime));
    }

    /**
     * Schedules the next car arrival.
     * The time is determined by the arrival random stream.
     * 
     * @param currentTime The current simulation time
     */
    void scheduleNextArrival(double currentTime) {
        // Generate the time until the next arrival
        double dt = arrivalStream.next();
        double nextTime = currentTime + dt;

        // Only schedule if it happens before closing time
        if (nextTime < closeTime)
            queue.add(new ArrivalEvent(nextTime, this));
    }

    /**
     * Schedules a departure event for a car after it finishes washing.
     * 
     * @param currentTime The current simulation time
     * @param car         The car to wash
     * @param fast        True for fast machine, false for slow
     * @return The wash time that was generated
     */
    void scheduleDeparture(double currentTime, Car car, boolean fast) {
        // Generate a random wash time based on the machine type
        double washTime = fast ? fastWashStream.next() : slowWashStream.next();
        // Schedule the departure event
        queue.add(new DepartureEvent(currentTime + washTime, car, fast, this));
    }

    /**
     * Creates a new car with a unique ID.
     * 
     * @param arrivalTime The time this car arrived
     * @return A new Car object
     */
    Car newCar(double arrivalTime) {
        return new Car(nextCarId++, arrivalTime);
    }
}