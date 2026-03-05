package simulation;

public abstract class Event implements Comparable<Event> {
    private final double time;

    public Event(double time) {
        this.time = time;
    }

    public double getTime() {
        return time;
    }

    public abstract void execute(State state, EventQueue queue);

    @Override
    public int compareTo(Event other) {
        return Double.compare(this.time, other.time);
    }
}