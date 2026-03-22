package carwash;

import java.util.Locale;

class SimulationView {

    void printHeader() {
        System.out.println("----------------------------------------");
        System.out.println("Time\tEvent\tId\tFast\tSlow\tIdleTime\tQueueTime\tQueueSize\tRejected");
    }

    void printRow(double time, String event, String id,
                         int freeFast, int freeSlow,
                         double idleTime, double queueTime,
                         int queueSize, int rejected) {

        System.out.printf(Locale.GERMAN,
                "%.2f\t%s\t%s\t%d\t%d\t%.2f\t\t%.2f\t\t%d\t\t%d%n",
                time, event, id,
                freeFast, freeSlow,
                idleTime, queueTime,
                queueSize, rejected);
    }

    void printSummary(double idle, double queue, double mean, int rejected) {
        System.out.println("----------------------------------------");
        System.out.println("Total idle machine time: " + String.format(Locale.GERMAN, "%.2f", idle));
        System.out.println("Total queueing time: " + String.format(Locale.GERMAN, "%.2f", queue));
        System.out.println("Mean queueing time: " + String.format(Locale.GERMAN, "%.2f", mean));
        System.out.println("Rejected cars: " + rejected);
    }
}