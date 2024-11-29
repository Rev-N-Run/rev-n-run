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

        Checkpoints checkpoints = new Checkpoints(track, 4);

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
    void lapStatus() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));

        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);
    }

    @Test
    void lapStatusGoingBackwards() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1,1),
            new Vector2(3,3),
            new Vector2(5,5)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));

        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);
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
    void hasPassedCheckPoints_shouldReturnGoodWhenNotFinishedTheLap() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3, 3)));

        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
    }

    @Test
    void hasPassedCheckPoints_shouldReturnIncompleteWhenLapFinishedButNotMinPercentOfCheckpointsPassed() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5),
            new Vector2(7, 7),
            new Vector2(9, 9),
            new Vector2(0, 9),
            new Vector2(0, 5),
            new Vector2(1, 1)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 5)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(6, 6)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(7, 7)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(9, 9)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertFalse(checkpoints.isInsideCircuit(new Vector2(12, 12)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0, 9)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0, 5)));
        assertEquals(checkpoints.lapStatus(), LapStatus.INCOMPLETE);

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

        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,1)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0.5f,0.5f)));

        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);

        checkpoints.resetProgress();

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,5)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));

        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);

        checkpoints.resetProgress();

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1,1)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2,2)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2.9f,2.9f)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,2.9f)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0,0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.COMPLETE);
    }
}
