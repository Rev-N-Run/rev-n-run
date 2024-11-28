package io.github.revNrun.revNrun.model.lap_timer;

public class LapTimer {
    private long startTime;
    private long currentLapTime;
    private boolean isRunning = false;

    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }
}
