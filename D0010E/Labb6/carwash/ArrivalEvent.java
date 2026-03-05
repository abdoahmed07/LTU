package carwash;

import simulation.Event;
import simulation.EventQueue;
import simulation.State;

public class ArrivalEvent extends Event {
    private final EventFactory factory;

    public ArrivalEvent(double time, EventFactory factory) {
        super(time);
        this.factory = factory;
    }
    @Override
    public void execute(State s, EventQueue q) {
        CarWashState state = (CarWashState) s;
    
        if (state.isClosed()) {
            return;
        }
    
        state.incArrived();
        Car car = factory.newCar(getTime());
    
        if (state.getFreeFast() > 0) {
            state.takeFast();
            factory.scheduleDeparture(getTime(), car, true);
        } 
        else if (state.getFreeSlow() > 0) {
            state.takeSlow();
            factory.scheduleDeparture(getTime(), car, false);
        } 
        else if (state.getQueue().size() < state.getMaxQueue()) {
            state.getQueue().add(car);
        } 
        else {
            state.incRejected();
        }
    
        state.printRow(getTime(), "Arrive", String.valueOf(car.getId()));
    
        factory.scheduleNextArrival(getTime());
    }
}