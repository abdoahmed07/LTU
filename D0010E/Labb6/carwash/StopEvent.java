package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

public class StopEvent extends Event {
    public StopEvent(double time) {
        super(time);
    }

    @Override
    public void execute(State state, EventQueue queue) {
        ((CarWashState) state).printRow(getTime(), "Stop", "");
        state.stop();
        ((CarWashState) state).printSummary();
    }
}