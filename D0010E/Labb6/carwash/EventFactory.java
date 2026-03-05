package carwash;

import random.ExponentialRandomStream;
import random.UniformRandomStream;
import simulation.EventQueue;

public class EventFactory {
    private final ExponentialRandomStream arrivalStream;
    private final UniformRandomStream fastWashStream;
    private final UniformRandomStream slowWashStream;

    private EventQueue queue;
    private CarWashState state;

    private int nextCarId = 1;

    private final double closeTime;
    private final double stopTime;

    public EventFactory(
            ExponentialRandomStream arrivalStream,
            UniformRandomStream fastWashStream,
            UniformRandomStream slowWashStream,
            double closeTime,
            double stopTime
    ) {
        this.arrivalStream = arrivalStream;
        this.fastWashStream = fastWashStream;
        this.slowWashStream = slowWashStream;
        this.closeTime = closeTime;
        this.stopTime = stopTime;
    }

    public void init(EventQueue queue, CarWashState state) {
        this.queue = queue;
        this.state = state;

        scheduleNextArrival(0.0);
        queue.add(new CloseEvent(closeTime));
        queue.add(new StopEvent(stopTime));
    }

    public void scheduleNextArrival(double currentTime) {
        double dt = arrivalStream.next();
        double nextTime = currentTime + dt;

        if (nextTime < closeTime)
            queue.add(new ArrivalEvent(nextTime, this));

    }

    public double scheduleDeparture(double currentTime, Car car, boolean fast) {
        double washTime = fast ? fastWashStream.next() : slowWashStream.next();
        queue.add(new DepartureEvent(currentTime + washTime, car, fast, washTime, this));
        return washTime;
    }

    public Car newCar(double arrivalTime) {
        return new Car(nextCarId++, arrivalTime);
    }
}