package io.github.revNrun.revNrun.model.checkpoints;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CheckpointsTest {

    @Test
    void getStartPoint() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(2,2),
            new Vector2(3,3)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 1);

        assertEquals(track.get(0), checkpoints.getStartPoint());
    }

    @Test
    void isInsideCircuit() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4, 4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(6, 6)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0, 0)));

        assertFalse(checkpoints.isInsideCircuit(new Vector2(-1.1f, -1.1f)));
        assertFalse(checkpoints.isInsideCircuit(new Vector2(4, 1)));
        assertFalse(checkpoints.isInsideCircuit(new Vector2(0, -1)));
        assertFalse(checkpoints.isInsideCircuit(new Vector2(7, 7)));
    }

    @Test
    void hasPassedCheckPoints() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));

        assertTrue(checkpoints.hasPassedCheckPoints());
    }

    @Test
    void hasPassedCheckPointsGoingBackwards() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));

        assertTrue(checkpoints.hasPassedCheckPoints());
    }

    @Test
    void isInsideCircuit_shouldHandleEdgeCases() {
        assertThrows(IllegalArgumentException.class, () -> new Checkpoints(new ArrayList<>(), 2));
        assertThrows(IllegalArgumentException.class, () -> new Checkpoints(null, 2));
        assertThrows(IllegalArgumentException.class, () -> new Checkpoints(new ArrayList<>(Arrays.asList(
            new Vector2(1,2),
            new Vector2(2,3)
        )), 0));
        assertThrows(IllegalArgumentException.class, () -> new Checkpoints(new ArrayList<>(Arrays.asList(
            new Vector2(1,2),
            new Vector2(2,3)
        )), -1));
    }

    @Test
    void hasPassedCheckPoints_shouldReturnFalseWhenNotAllCheckpointsPassed() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3, 3)));

        assertFalse(checkpoints.hasPassedCheckPoints(),
            "Not all checkpoints should have been passed");
    }

    @Test
    void hasPassedCheckPoints_shouldReturnFalseWhenNotAllCheckpointsPassed2() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 5)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(6, 6)));

        assertFalse(checkpoints.hasPassedCheckPoints(),
            "Not all checkpoints should have been passed");
    }

    @Test
    void multipleLaps() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5),
            new Vector2(5,3),
            new Vector2(5,0),
            new Vector2(0,0),
            new Vector2(1,1)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 2);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));

        assertTrue(checkpoints.hasPassedCheckPoints());

        checkpoints.resetProgress();

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,2.9f)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));

        assertTrue(checkpoints.hasPassedCheckPoints());

        checkpoints.resetProgress();

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2.9f,2.9f)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,2.9f)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));

        assertFalse(checkpoints.hasPassedCheckPoints());
    }
}
