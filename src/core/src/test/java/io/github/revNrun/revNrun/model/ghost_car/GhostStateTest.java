package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class GhostStateTest {
    @Test
    void testCopyConstructor() {
        GhostState ghost = new GhostState(new Vector2(1, 2), 0, 0);
        GhostState copy = new GhostState(ghost);

        assertTrue(ghost.getPosition() != copy.getPosition());
        assertEquals(ghost.getX(), copy.getX());
        assertEquals(ghost.getY(), copy.getY());
        assertEquals(ghost.getAngle(), copy.getAngle());
        assertEquals(ghost.getTimestamp(), copy.getTimestamp());
    }
}
