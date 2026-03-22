package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

/**
 * Represents an event where a car arrives at the car wash.
 * This event is triggered when a new customer arrives and either gets assigned
 * to a free washing machine or gets added to the queue.
 */
class ArrivalEvent extends Event {
    // Factory used to create new events (like departures and next arrivals)
    private final EventFactory factory;

    /**
     * Creates an arrival event at a specific time.
     * 
     * @param time    The time when the car arrives
     * @param factory The factory used to schedule other events
     */
    ArrivalEvent(double time, EventFactory factory) {
        super(time);
        this.factory = factory;
    }

    /**
     * Executes the arrival event. Checks if a free washing machine is available.
     * If yes, assigns the car to it. If no, adds to queue or rejects if queue is
     * full.
     * 
     * @param s The current state of the car wash
     * @param q The event queue to add new events to
     */
    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
        state.updateTimes(getTime());

        // Don't process arrivals if car wash is closed
        if (state.isClosed()) return;

        // Count the arrival
        state.incArrived();
        Car car = factory.newCar(getTime());

        // Try to assign to a fast washing machine
        if (state.getFreeFast() > 0) {
            state.takeFast();
            factory.scheduleDeparture(getTime(), car, true);
        }
        // Try to assign to a slow washing machine
        else if (state.getFreeSlow() > 0) {
            state.takeSlow();
            factory.scheduleDeparture(getTime(), car, false);
        }
        // If no machines available, add to queue (if there's room)
        else if (state.getQueue().size() < state.getMaxQueue()) 
            state.getQueue().add(car);
        // If queue is full, reject the car
        else 
            state.incRejected();
        

        // Print event information
        state.printRow(getTime(), "Arrive", String.valueOf(car.getId()));

        // Schedule the next car arrival
        factory.scheduleNextArrival(getTime());
    }
}