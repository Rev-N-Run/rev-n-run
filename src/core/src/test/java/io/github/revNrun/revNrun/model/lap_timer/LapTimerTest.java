package io.github.revNrun.revNrun.model.lap_timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LapTimerTest {
    @Test
    void startLapTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
        String lapStart = "00:00.000";
        assertFalse(lapTimer.isRunning());
        assertEquals(lapStart, lapTimer.getCurrentLapTime());

        lapTimer.start();

        assertTrue(lapTimer.isRunning());
        assertEquals(lapStart, lapTimer.getCurrentLapTime());

        Thread.sleep(1000);

        assertTrue(lapTimer.getCurrentLapTime().compareTo(lapStart) > 0);
        assertNotEquals(lapStart, lapTimer.getCurrentLapTime());
    }

    @Test
    void resetTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
        lapTimer.start();
        Thread.sleep(1000);

        lapTimer.reset();

        assertFalse(lapTimer.isRunning());
        assertEquals("00:00.000", lapTimer.getCurrentLapTime());

        Thread.sleep(1000);

        assertEquals("00:00.000", lapTimer.getCurrentLapTime());
    }
}
