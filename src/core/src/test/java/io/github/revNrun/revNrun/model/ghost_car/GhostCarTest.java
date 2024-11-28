package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostCarTest {
    @Test
    void recordTest() {
        GhostCar ghostCar = new GhostCar();
        ghostCar.recordState(new Vector2(1, 1), 1, 1);

        assertEquals(1, ghostCar.getPositionX(0));
        assertEquals(1, ghostCar.getPositionY(0));
        assertEquals(1, ghostCar.getAngle(0));
        assertEquals(1, ghostCar.getTimestamp(0));

        // Check out of index access
        // < 0
        assertEquals(ghostCar.getPositionX(0), ghostCar.getPositionX(-1));
        assertEquals(ghostCar.getPositionY(0), ghostCar.getPositionY(-1));
        assertEquals(ghostCar.getAngle(0), ghostCar.getAngle(-1));
        assertEquals(ghostCar.getTimestamp(0), ghostCar.getTimestamp(-1));

        ghostCar.recordState(new Vector2(2, 2), 2, 2);

        // >= states.size()
        assertEquals(ghostCar.getPositionX(1), ghostCar.getPositionX(2));
        assertEquals(ghostCar.getPositionY(1), ghostCar.getPositionY(2));
        assertEquals(ghostCar.getAngle(1), ghostCar.getAngle(2));
        assertEquals(ghostCar.getTimestamp(1), ghostCar.getTimestamp(2));
    }
}
