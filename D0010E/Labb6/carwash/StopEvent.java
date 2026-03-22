package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

/**
 * Represents the event that stops the entire simulation.
 * This is the final event that terminates the simulation and prints the
 * summary.
 */
class StopEvent extends Event {
    /**
     * Creates a stop event at a specific time.
     * 
     * @param time The time when the simulation should stop
     */
    StopEvent(double time) {
        super(time);
    }

    /**
     * Executes the stop event by ending the simulation and printing the final
     * summary.
     * 
     * @param state The current state of the car wash
     * @param queue The event queue
     */
    @Override
    public void execute(State state, EventQueue queue) {
        CarWashState s = (CarWashState) state;
        s.updateTimes(getTime());

        s.printRow(getTime(), "Stop", "");
        state.stop();
        s.printSummary();
    }
}