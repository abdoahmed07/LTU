package simulation;

/**
 * The main simulation engine.
 * Processes events from the event queue one at a time in chronological order.
 */
public class Simulator {
    // The queue of events to process
    private final EventQueue eventQueue;
    // The current state of the simulation
    private final State state;

    /**
     * Creates a new simulator with the given event queue and state.
     * 
     * @param eventQueue The queue of events to process
     * @param state      The simulation state
     */
    public Simulator(EventQueue eventQueue, State state) {
        this.eventQueue = eventQueue;
        this.state = state;
    }

    /**
     * Runs the simulation by processing all events in order.
     * Continues until the state is stopped or no more events exist.
     */
    public void run() {
        // Process events until simulation stops or queue is empty
        while (!state.isStopped() && !eventQueue.isEmpty()) {
            // Get the next event
            Event e = eventQueue.next();
            // Update the simulation time
            state.setTime(e.getTime());
            // Execute the event
            e.execute(state, eventQueue);
        }
    }
}