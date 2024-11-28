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
    }
}
