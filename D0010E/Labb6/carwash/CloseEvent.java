package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

/**
 * Represents the event when the car wash stops accepting new arrivals.
 * This event marks the closing time - no new cars will be accepted after this.
 */
class CloseEvent extends Event {
    /**
     * Creates a close event at a specific time.
     * 
     * @param time The time when the car wash should stop accepting arrivals
     */
    CloseEvent(double time) {
        super(time);
    }

    /**
     * Executes the close event by marking the car wash as closed.
     * After this, no new cars will be accepted into the system.
     * 
     * @param s The current state of the car wash
     * @param q The event queue
     */
    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
        state.updateTimes(getTime());

        // Mark the car wash as closed to new arrivals
        state.closeArrivals();
    }
}