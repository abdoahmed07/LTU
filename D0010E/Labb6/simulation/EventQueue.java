package simulation;

import java.util.PriorityQueue;

/**
 * A queue that stores events in chronological order.
 * Events are always retrieved in order of their occurrence time.
 */
public class EventQueue {
    // Priority queue that orders events by time
    private final PriorityQueue<Event> pq = new PriorityQueue<>();

    /**
     * Adds an event to the queue.
     * The event will be stored in chronological order.
     * 
     * @param e The event to add
     */
    public void add(Event e) {
        pq.add(e);
    }

    /**
     * Gets and removes the next event from the queue.
     * 
     * @return The event that occurs next in time
     */
    protected Event next() {
        return pq.poll();
    }

    /**
     * Checks if the queue is empty.
     * 
     * @return true if no events are scheduled, false otherwise
     */
    protected boolean isEmpty() {
        return pq.isEmpty();
    }
}