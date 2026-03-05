package carwash;

import simulation.State;

import java.util.Locale;
import java.util.ArrayDeque;
import java.util.Queue;

public class CarWashState extends State {
    private int freeFast;
    private int freeSlow;

    private final int maxQueue;
    private final Queue<Car> queue = new ArrayDeque<>();

    private int totalArrived;
    private int totalServed;
    private int totalRejected;

    private double lastEventTime = 0.0;
    private double totalIdleTime = 0.0;
    private double totalQueueTime = 0.0;

    private boolean closed; // after CloseEvent

    public CarWashState(int fastStations, int slowStations, int maxQueue) {
        this.freeFast = fastStations;
        this.freeSlow = slowStations;
        this.maxQueue = maxQueue;
    }

    public boolean isClosed() {
        return closed;
    }

    public void closeArrivals() {
        this.closed = true;
    }

    public int getFreeFast() { return freeFast; }
    public int getFreeSlow() { return freeSlow; }

    public void takeFast() { freeFast--; }
    public void releaseFast() { freeFast++; }

    public void takeSlow() { freeSlow--; }
    public void releaseSlow() { freeSlow++; }

    public Queue<Car> getQueue() { return queue; }
    public int getMaxQueue() { return maxQueue; }

    public void incArrived() { totalArrived++; }
    public void incServed() { totalServed++; }
    public void incRejected() { totalRejected++; }

    public int getTotalArrived() { return totalArrived; }
    public int getTotalServed() { return totalServed; }
    public int getTotalRejected() { return totalRejected; }
    public double getTotalIdleTime() { return totalIdleTime; }
    public double getTotalQueueTime() { return totalQueueTime; }

    public void printHeader() {
        Locale.setDefault(Locale.GERMAN);        
        System.out.println("----------------------------------------");
        System.out.println("    Time\tEvent\tId\tFast\tSlow\tIdleTime\tQueueTime\tQueueSize\tRejected");
    }

    public void printRow(double time, String event, String id) {
        updateTimes(time);
        System.out.printf("    %.2f\t%s\t%s\t%d\t%d\t%.2f\t\t%.2f\t\t%d\t\t%d%n",
            time, event, id,
            getFreeFast(), getFreeSlow(),
            getTotalIdleTime(), getTotalQueueTime(),
            getQueue().size(), getTotalRejected());
    }

    public void updateTimes(double now) {
        double dt = now - lastEventTime;
        if (dt < 0) dt = 0;

        int idleMachines = getFreeFast() + getFreeSlow();
        totalIdleTime += dt * idleMachines;

        int waitingCars = getQueue().size();
        totalQueueTime += dt * waitingCars;

        lastEventTime = now;
    }


    public String fmt(double x) {
        return String.format(Locale.GERMAN, "%.2f", x);
    }

    public void printSummary() {
        System.out.println("----------------------------------------");

        double idle = getTotalIdleTime();
        double queueT = getTotalQueueTime();

        int served = getTotalServed();
        double meanQ = (served == 0) ? 0.0 : (queueT / served);

        System.out.println("Total idle machine time: " + fmt(idle));
        System.out.println("Total queueing time: " + fmt(queueT));
        System.out.println("Mean queueing time: " + fmt(meanQ));
        System.out.println("Rejected cars: " + getTotalRejected());
    }

    public String statistics() {
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