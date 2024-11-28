package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;
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
}
