package simulation;

import java.util.PriorityQueue;

public class EventQueue {
    private final PriorityQueue<Event> pq = new PriorityQueue<>();

    public void add(Event e) {
        pq.add(e);
    }

    public Event next() {
        return pq.poll();
    }

    public boolean isEmpty() {
        return pq.isEmpty();
    }
}