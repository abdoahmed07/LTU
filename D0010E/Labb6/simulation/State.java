package simulation;

/**
 * Base class for simulation state.
 * Tracks the current simulation time and whether the simulation is running.
 */
public class State {
    // Current simulation time
    private double time;
    // Flag indicating if the simulation should stop
    private boolean stopFlag;

    /**
     * Gets the current simulation time.
     * 
     * @return The current time
     */
    protected double getTime() {
        return time;
    }

    /**
     * Sets the current simulation time.
     * 
     * @param t The new time
     */
    protected void setTime(double t) {
        this.time = t;
    }

    /**
     * Signals the simulation to stop.
     */
    public void stop() {
        this.stopFlag = true;
    }

    /**
     * Checks if the simulation has been stopped.
     * 
     * @return true if simulation is stopped, false otherwise
     */
    protected boolean isStopped() {
        return stopFlag;
    }
}