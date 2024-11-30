package io.github.revNrun.revNrun.model.lap_timer;

public class LapTimer {
    private long startTime;
    private long lastLapTime;
    private boolean isRunning = false;

    public LapTimer() {

    }

    public LapTimer(LapTimer that) {
        this.startTime = that.startTime;
        this.lastLapTime = that.lastLapTime;
        this.isRunning = that.isRunning;
    }

    public void start() {
        if (!isRunning) {
            startTime = System.nanoTime();
            isRunning = true;
        }
    }

    public boolean isRunning() {
        return isRunning;
    }

    public String getCurrentLapTime() {
        if (!isRunning) {
            return "00:00.000";
        }
        long currentLapTime = System.nanoTime() - startTime;
        return formatTime(currentLapTime);
    }

    public String getLastLapTime() {
        return formatTime(lastLapTime);
    }

    public void reset() {
        startTime = System.nanoTime();
        isRunning = false;
    }

    public void stop() {
        isRunning = false;
        lastLapTime = System.nanoTime() - startTime;
    }

    public boolean isFasterThan(String lapTime) {
        return getLastLapTime().compareTo(lapTime) < 0;
    }

    public boolean isFasterThan(LapTimer lapTime) {
        return getLastLapTime().compareTo(lapTime.getLastLapTime()) < 0;
    }

    private String formatTime(long nanoTime) {
        long millis = nanoTime / 1_000_000;
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        long milliseconds = millis % 1000;

        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
    }
}
