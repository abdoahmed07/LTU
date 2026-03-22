package simulation;

/**
 * Abstract base class for all events in the simulation.
 * Events are scheduled to occur at specific times and are processed in order.
 */
public abstract class Event implements Comparable<Event> {
    // The time when this event occurs
    private final double time;

    /**
     * Creates an event scheduled to occur at a specific time.
     * 
     * @param time The time when this event should occur
     */
    protected Event(double time) {
        this.time = time;
    }

    /**
     * Gets the time when this event occurs.
     * 
     * @return The event time
     */
    protected double getTime() {
        return time;
    }

    /**
     * Executes the actions associated with this event.
     * Subclasses must implement this method to define what happens when the event
     * occurs.
     * 
     * @param state The current state of the simulation
     * @param queue The event queue to add new events to
     */
    public abstract void execute(State state, EventQueue queue);

    /**
     * Compares two events by their time.
     * This allows events to be sorted in chronological order.
     * 
     * @param other The event to compare with
     * @return Negative if this event is earlier, positive if later, 0 if same time
     */
    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}