package simulation;

public class State {
    private double time;
    private boolean stopFlag;

    public double getTime() {
        return time;
    }

    public void setTime(double t) {
        this.time = t;
    }

    public void stop() {
        this.stopFlag = true;
    }

    public boolean isStopped() {
        return stopFlag;
    }
}