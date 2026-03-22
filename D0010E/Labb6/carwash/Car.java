package carwash;

/**
 * Represents a car that arrives at the car wash.
 * Stores the car's unique ID and the time it arrived.
 */
class Car {
    // Unique identifier for this car
    private final int id;
    // The time when this car arrived at the car wash
    private final double arrivalTime;

    /**
     * Creates a new car with a unique ID and arrival time.
     * 
     * @param id          Unique identifier for this car
     * @param arrivalTime The time this car arrived
     */
    Car(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    /**
     * Gets the car's unique ID.
     * 
     * @return The car's ID
     */
    int getId() {
        return id;
    }

    /**
     * Gets the time this car arrived.
     * 
     * @return The arrival time
     */
    double getArrivalTime() {
        return arrivalTime;
    }
}