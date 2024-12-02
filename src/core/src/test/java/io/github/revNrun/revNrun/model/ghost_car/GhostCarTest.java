package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.model.lap_timer.LapTimer;
import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostCarTest {
    GhostCar ghostCar;
    @BeforeEach
    void setUp() {
        ghostCar = new GhostCar();
    }

    @Test
    void recordTest() {
        ghostCar.recordState(new Vector2(1, 1), 1, 1);
        ghostCar.recordState(new Vector2(2, 2), 2, 2);

        assertEquals(1, ghostCar.getPositionX());
        assertEquals(1, ghostCar.getPositionY());
        assertEquals(1, ghostCar.getAngle());
        assertEquals(1, ghostCar.getTimestamp());

        ghostCar.nextFrame();

        assertEquals(2, ghostCar.getPositionX());
        assertEquals(2, ghostCar.getPositionY());
        assertEquals(2, ghostCar.getAngle());
        assertEquals(2, ghostCar.getTimestamp());

        ghostCar.nextFrame();

        assertEquals(2, ghostCar.getPositionX());
        assertEquals(2, ghostCar.getPositionY());
        assertEquals(2, ghostCar.getAngle());
        assertEquals(2, ghostCar.getTimestamp());
    }

    @Test
    void reset() {
        ghostCar.reset();
        assertEquals(0, ghostCar.getCurrentStateIndex());
        assertEquals(0, ghostCar.getPositionX());
        assertEquals(0, ghostCar.getPositionY());
        assertEquals(0, ghostCar.getAngle());
        assertEquals(0, ghostCar.getTimestamp());
    }

    @Test
    void testGoToFirstFrame() {
        ghostCar.recordState(new Vector2(1, 1), 1, 1);
        ghostCar.recordState(new Vector2(2, 2), 2, 2);
        ghostCar.nextFrame();
        ghostCar.goToFirstFrame();

        assertEquals(1, ghostCar.getPositionX());
        assertEquals(1, ghostCar.getPositionY());
        assertEquals(1, ghostCar.getAngle());
        assertEquals(1, ghostCar.getTimestamp());
    }


    @Test
    void testConstructorCopy() {
        ghostCar.recordState(new Vector2(1, 1), 1, 1);
        ghostCar.recordState(new Vector2(2, 2), 2, 2);

        GhostCar copy = new GhostCar(ghostCar);

        assertEquals(copy.getPositionX(), ghostCar.getPositionX());
        assertEquals(copy.getPositionY(), ghostCar.getPositionY());
        assertEquals(copy.getAngle(), ghostCar.getAngle());
        assertEquals(copy.getTimestamp(), ghostCar.getTimestamp());

        ghostCar.nextFrame();
        copy.nextFrame();

        assertEquals(copy.getPositionX(), ghostCar.getPositionX());
        assertEquals(copy.getPositionY(), ghostCar.getPositionY());
        assertEquals(copy.getAngle(), ghostCar.getAngle());
        assertEquals(copy.getTimestamp(), ghostCar.getTimestamp());

        copy.recordState(new Vector2(3, 3), 3, 3);

        ghostCar.nextFrame();
        copy.nextFrame();

        assertNotEquals(copy.getPositionX(), ghostCar.getPositionX());
        assertNotEquals(copy.getPositionY(), ghostCar.getPositionY());
        assertNotEquals(copy.getAngle(), ghostCar.getAngle());
        assertNotEquals(copy.getTimestamp(), ghostCar.getTimestamp());
    }

    @Test
    void restart() {
        ghostCar.restart();
        assertEquals(0, ghostCar.getCurrentStateIndex());
    }

    @Test
    void timer() {
        LapTimer timer = new LapTimer();
        ghostCar.setTimer(timer);
        assertEquals(timer, ghostCar.getLapTimer());
    }

    @Test
    void isEmpty() {
        ghostCar.nextFrame();
    }
}
