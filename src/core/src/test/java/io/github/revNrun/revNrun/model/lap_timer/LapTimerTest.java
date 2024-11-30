package io.github.revNrun.revNrun.model.lap_timer;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LapTimerTest {
    private final String lapStart = "00:00.000";

    @Test
    void startLapTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
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
        assertEquals(lapStart, lapTimer.getCurrentLapTime());

        Thread.sleep(1000);

        assertEquals(lapStart, lapTimer.getCurrentLapTime());
    }

    @Test
    void stopTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
        lapTimer.start();
        Thread.sleep(1000);

        lapTimer.stop();
        String lapStop = lapTimer.getCurrentLapTime();

        assertFalse(lapTimer.isRunning());

        Thread.sleep(1000);

        assertEquals(lapStop, lapTimer.getCurrentLapTime());
    }

    @Test
    void compareLapTimeTest() throws InterruptedException {
        LapTimer lapTimer = new LapTimer();
        lapTimer.start();
        Thread.sleep(1000);
        lapTimer.stop();

        assertFalse(lapTimer.isFasterThan(lapStart));
        String testLapTime1 = "00:00.836";
        assertFalse(lapTimer.isFasterThan(testLapTime1));
        assertTrue(lapTimer.isFasterThan("00:34.000"));
        assertFalse(lapTimer.isFasterThan(lapTimer.getLastLapTime()));
    }

    @Test
    void compareLaps() throws InterruptedException {
        LapTimer lapTimer1 = new LapTimer();
        LapTimer lapTimer2 = new LapTimer();

        lapTimer1.start();
        lapTimer2.start();

        Thread.sleep(100);
        lapTimer1.stop();
        lapTimer2.stop();

        assertFalse(lapTimer1.isFasterThan(lapTimer2));

        lapTimer2.start();
        Thread.sleep(200);
        lapTimer2.stop();

        assertTrue(lapTimer1.isFasterThan(lapTimer2));
    }

    @Test
    void testCopyConstructor() {
        LapTimer timer = new LapTimer();
        timer.start();
        LapTimer copy = new LapTimer(timer);

        assertEquals(timer.isRunning(), copy.isRunning());

        timer.stop();

        assertNotEquals(timer.isRunning(), copy.isRunning());
        assertNotEquals(timer.getLastLapTime(), copy.getLastLapTime());
    }
}
