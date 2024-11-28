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

    public String getCurrentLapTime() {
        if (!isRunning) {
            return "00:00.000";
        }
        currentLapTime = System.nanoTime() - startTime;
        return formatTime(currentLapTime);
    }

    public void reset() {
        startTime = System.nanoTime();
        isRunning = false;
    }

    private String formatTime(long nanoTime) {
        long millis = nanoTime / 1_000_000;
        long minutes = (millis / 1000) / 60;
        long seconds = (millis / 1000) % 60;
        long milliseconds = millis % 1000;

        return String.format("%02d:%02d.%03d", minutes, seconds, milliseconds);
    }
}
