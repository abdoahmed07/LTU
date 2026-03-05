package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

public class DepartureEvent extends Event {
    private final Car car;
    private final boolean fast;
    private final EventFactory factory;
    private final double washTime;

    public DepartureEvent(double time, Car car, boolean fast, double washTime, EventFactory factory) {
        super(time);
        this.car = car;
        this.fast = fast;
        this.washTime = washTime;
        this.factory = factory;
    }

    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
    
        state.incServed();
    
        // print leave event
        state.printRow(getTime(), "Leave", String.valueOf(car.getId()));
    
        // free the machine
        if (fast) state.releaseFast();
        else state.releaseSlow();
    
        // start next car from queue if any
        if (!state.getQueue().isEmpty()) {
            Car next = state.getQueue().poll();
        
            if (fast) state.takeFast();
            else state.takeSlow();
        
            factory.scheduleDeparture(getTime(), next, fast);
        }
    }
}