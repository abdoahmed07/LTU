package carwash;

import simulation.State;

import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Maintains the state of the entire car wash simulation.
 * Tracks available machines, queued cars, and statistics about arrivals,
 * services, and rejections.
 */
class CarWashState extends State {
    // Number of available fast washing machines
    private int freeFast;
    // Number of available slow washing machines
    private int freeSlow;

    // Maximum number of cars allowed in the waiting queue
    private final int maxQueue;
    // Queue of cars waiting for a washing machine
    private final Queue<Car> queue = new ArrayDeque<>();

    // Simulation view for displaying the state (if needed)
    private SimulationView view;

    // Total number of cars that have arrived
    private int totalArrived;
    // Total number of cars that have been served
    private int totalServed;
    // Total number of cars that were rejected (queue was full)
    private int totalRejected;

    // Time of the last event (used to calculate idle and queue times)
    private double lastEventTime = 0.0;
    // Total accumulated time all machines were idle
    private double totalIdleTime = 0.0;
    // Total accumulated time all cars spent waiting in queue
    private double totalQueueTime = 0.0;

    // Flag indicating if the car wash has stopped accepting new arrivals
    private boolean closed;

    /**
     * Creates a new car wash state with the specified number of machines and queue
     * capacity.
     * 
     * @param fastStations Number of fast washing machines
     * @param slowStations Number of slow washing machines
     * @param maxQueue     Maximum queue size
     */
    CarWashState(int fastStations, int slowStations, int maxQueue) {
        this.freeFast = fastStations;
        this.freeSlow = slowStations;
        this.maxQueue = maxQueue;
    }

    /**
     * Checks if the car wash has stopped accepting new arrivals.
     * 
     * @return true if closed, false otherwise
     */
    boolean isClosed() {
        return closed;
    }

    /**
     * Marks the car wash as closed to new arrivals.
     */
    void closeArrivals() {
        this.closed = true;
    }

    // Fast machine getters and setters
    /** Gets the number of available fast machines */
    int getFreeFast() {
        return freeFast;
    }

    /** Gets the number of available slow machines */
    int getFreeSlow() {
        return freeSlow;
    }

    /** Occupies one fast machine */
    void takeFast() {
        freeFast--;
    }

    /** Releases one fast machine */
    void releaseFast() {
        freeFast++;
    }

    // Slow machine getters and setters
    /** Occupies one slow machine */
    void takeSlow() {
        freeSlow--;
    }

    /** Releases one slow machine */
    void releaseSlow() {
        freeSlow++;
    }

    /**
     * Gets the queue of waiting cars.
     * 
     * @return The queue
     */
    Queue<Car> getQueue() {
        return queue;
    }

    /**
     * Sets the simulation view for this state.
     * 
     * @param view The simulation view to set
     */
    void setView(SimulationView view) {
        this.view = view;
    }

    /**
     * Gets the simulation view associated with this state.
     * 
     * @return The simulation view
     */
    SimulationView getView() {
        return view;
    }

    /**
     * Gets the maximum queue size.
     * 
     * @return The maximum queue size
     */
    int getMaxQueue() {
        return maxQueue;
    }

    // Statistics updaters
    /** Increments the count of arrived cars */
    void incArrived() {
        totalArrived++;
    }

    /** Increments the count of served cars */
    void incServed() {
        totalServed++;
    }

    /** Increments the count of rejected cars */
    void incRejected() {
        totalRejected++;
    }

    // Statistics getters
    /** Gets the total number of cars that arrived */
    int getTotalArrived() {
        return totalArrived;
    }

    /** Gets the total number of cars that were served */
    int getTotalServed() {
        return totalServed;
    }

    /** Gets the total number of cars that were rejected */
    int getTotalRejected() {
        return totalRejected;
    }

    /** Gets the total accumulated idle time for all machines */
    double getTotalIdleTime() {
        return totalIdleTime;
    }

    /** Gets the total accumulated waiting time for all cars */
    double getTotalQueueTime() {
        return totalQueueTime;
    }

    /**
     * Prints one row of event information during the simulation.
     * Updates idle and queue times before printing.
     * 
     * @param time  The current simulation time
     * @param event The event type (e.g., "Arrive", "Leave")
     * @param id    The ID of the car involved (or empty string if not applicable)
     */
    void printRow(double time, String event, String id) {
        view.printRow(
            time, event, id,
            getFreeFast(), getFreeSlow(),
            getTotalIdleTime(), getTotalQueueTime(),
            getQueue().size(), getTotalRejected()
        );
    }

    /**
     * Updates the accumulated idle and queue times based on elapsed time.
     * 
     * @param now The current simulation time
     */
    void updateTimes(double now) {
        // Calculate time elapsed since last event
        double dt = now - lastEventTime;
        if (dt < 0)
            dt = 0;

        // Add idle time for all idle machines
        int idleMachines = getFreeFast() + getFreeSlow();
        totalIdleTime += dt * idleMachines;

        // Add queue time for all waiting cars
        int waitingCars = getQueue().size();
        totalQueueTime += dt * waitingCars;

        // Update last event time
        lastEventTime = now;
    }

    /**
     * Prints the final summary statistics of the simulation.
     */
    void printSummary() {

        // Calculate summary statistics
        double idle = getTotalIdleTime();
        double queueT = getTotalQueueTime();

        // Calculate mean queue time per car that entered the system
        int entered = getTotalArrived() - getTotalRejected();
        double meanQ = (entered == 0) ? 0.0 : (queueT / entered);

        // Print the summary using the view
        view.printSummary(idle, queueT, meanQ, getTotalRejected());
    }

    /**
     * Returns a string representation of the current state.
     * 
     * @return A formatted string with all state information
     */
    String statistics() {
        return ""
                + "time=" + getTime() + "\n"
                + "arrived=" + totalArrived + "\n"
                + "served=" + totalServed + "\n"
                + "rejected=" + totalRejected + "\n"
                + "queueSize=" + queue.size() + "\n"
                + "freeFast=" + freeFast + "\n"
                + "freeSlow=" + freeSlow + "\n";
    }
}