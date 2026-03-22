package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

/**
 * Represents the event when a car finishes washing and departs from the car
 * wash.
 * When a car departs, the machine is freed and can serve another car from the
 * queue.
 */
class DepartureEvent extends Event {
    // The car that is departing
    private final Car car;
    // True if using fast machine, false if using slow machine
    private final boolean fast;
    // Factory to schedule new events
    private final EventFactory factory;


    /**
     * Creates a departure event for a car finishing its wash.
     * 
     * @param time     The time when the car departs
     * @param car      The car that is departing
     * @param fast     True if the car used a fast machine, false for slow
     * @param washTime The duration of the wash
     * @param factory  The factory to schedule new events
     */
    DepartureEvent(double time, Car car, boolean fast, EventFactory factory) {
        super(time);
        this.car = car;
        this.fast = fast;
        this.factory = factory;
    }

    /**
     * Executes the departure event. Frees the washing machine and processes
     * the next car from the queue if there is one.
     * 
     * @param s The current state of the car wash
     * @param q The event queue to add new events to
     */
    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
        state.updateTimes(getTime());

        // Count this car as served
        state.incServed();

        // Print the departure event
        state.printRow(getTime(), "Leave", String.valueOf(car.getId()));

        // Free up the washing machine that was used
        if (fast) state.releaseFast();
         else state.releaseSlow();

        // If there are cars waiting, process the next one
        if (!state.getQueue().isEmpty()) {
            Car next = state.getQueue().poll();

            // occupy the same machine for the next car
            if (fast) 
                state.takeFast();
            else 
                state.takeSlow();

            // Schedule the next car's departure
            factory.scheduleDeparture(getTime(), next, fast);
        }
    }
}