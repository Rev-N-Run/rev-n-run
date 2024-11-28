package io.github.revNrun.revNrun.model.lap_timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LapTimerTest {
    @Test
    void startLapTest() {
        LapTimer lapTimer = new LapTimer();
        lapTimer.start();

        assertTrue(lapTimer.isRunning());
    }
}
