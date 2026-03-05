package simulation;

public class Simulator {
    private final EventQueue eventQueue;
    private final State state;

    public Simulator(EventQueue eventQueue, State state) {
        this.eventQueue = eventQueue;
        this.state = state;
    }

    public void run() {
        while (!state.isStopped() && !eventQueue.isEmpty()) {
            Event e = eventQueue.next();
            state.setTime(e.getTime());
            e.execute(state, eventQueue);
        }
    }
}