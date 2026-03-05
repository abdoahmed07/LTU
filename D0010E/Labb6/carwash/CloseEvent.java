package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

public class CloseEvent extends Event {
    public CloseEvent(double time) {
        super(time);
    }

    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
        state.closeArrivals();
    }
}