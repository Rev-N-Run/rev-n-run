package io.github.revNrun.revNrun.model.lap_timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LapTimerTest {
    @Test
    void startLapTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
        String lapStart = "00:00.000";
        lapTimer.start();

        assertTrue(lapTimer.isRunning());
        assertEquals(lapStart, lapTimer.getCurrentLapTime());

        Thread.sleep(1000);

        assertTrue(lapTimer.getCurrentLapTime().compareTo(lapStart) > 0);
        assertNotEquals(lapStart, lapTimer.getCurrentLapTime());
    }
}
