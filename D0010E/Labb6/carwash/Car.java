package carwash;

public class Car {
    private final int id;
    private final double arrivalTime;

    public Car(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }
}