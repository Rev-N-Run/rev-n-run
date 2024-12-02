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
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(7, 7)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(9, 9)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertFalse(checkpoints.isInsideCircuit(new Vector2(11, 12)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
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

    @Test
    void goingBackToRecoverSkippedCheckpointsIsNotPermitted() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5),
            new Vector2(5, 3),
            new Vector2(5, 0),
            new Vector2(0, 0),
            new Vector2(1, 1)
        ));

        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2, 2)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3, 3)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4, 4)));
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 5)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertFalse(checkpoints.isInsideCircuit(new Vector2(8, 8)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertFalse(checkpoints.isInsideCircuit(new Vector2(9, 9)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 3)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(5, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(4, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(3, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(2, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 0)));
        assertEquals(checkpoints.lapStatus(), LapStatus.WRONG);
        assertTrue(checkpoints.isInsideCircuit(new Vector2(0, 0)));

        assertEquals(checkpoints.lapStatus(), LapStatus.INCOMPLETE);
    }

    @Test
    public void testInitCarOutsideTrackHasToReturnFatal() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5),
            new Vector2(5, 3),
            new Vector2(5, 0),
            new Vector2(0, 0),
            new Vector2(1, 1)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertFalse(checkpoints.isInsideCircuit(new Vector2(-10, -10)));
        assertEquals(checkpoints.lapStatus(), LapStatus.FATAL);
    }

    @Test
    public void testInitCarInsideTrackHasToReturnGood() {
        List<Vector2> track = new ArrayList<>(Arrays.asList(
            new Vector2(1, 1),
            new Vector2(3, 3),
            new Vector2(5, 5),
            new Vector2(5, 3),
            new Vector2(5, 0),
            new Vector2(0, 0),
            new Vector2(1, 1)
        ));
        Checkpoints checkpoints = new Checkpoints(track, 4);

        assertTrue(checkpoints.isInsideCircuit(new Vector2(1, 1)));
        assertEquals(checkpoints.lapStatus(), LapStatus.GOOD);
    }

    // LOOP TESTING

    // Simulate a list of Vector2 control points
    private List<Vector2> createControlPoints(int size) {
        List<Vector2> points = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            points.add(new Vector2(i * 10, i * 10));  // Simple increasing points for the test
        }
        return points;
    }

    @Test
    void testLoopWithTwoPoints() {
        List<Vector2> points = createControlPoints(2);
        Checkpoints checkpoints = new Checkpoints(points);

        Vector2 pointInside = new Vector2(5, 5);  // Inside the circuit
        Vector2 pointOutside = new Vector2(50, 50);  // Outside the circuit

        // This test focuses on testing the loop with only two points
        assertTrue(checkpoints.isInsideCircuit(pointInside));
        assertFalse(checkpoints.isInsideCircuit(pointOutside));
    }

    @Test
    void testLoopWithMultiplePoints() {
        List<Vector2> points = createControlPoints(5);  // Multiple points for the loop
        Checkpoints checkpoints = new Checkpoints(points);

        Vector2 pointInside = new Vector2(15, 15);  // Inside the circuit
        Vector2 pointOutside = new Vector2(500, 500);  // Outside the circuit

        // This test checks the loop over 5 control points
        assertTrue(checkpoints.isInsideCircuit(pointInside));
        assertFalse(checkpoints.isInsideCircuit(pointOutside));
    }

    @Test
    void testLoopWithEdgeCases() {
        List<Vector2> points = createControlPoints(10);  // More points for stress testing the loop
        Checkpoints checkpoints = new Checkpoints(points);

        Vector2 pointEdge = new Vector2(30, 30);  // On the boundary of the circuit
        Vector2 pointFar = new Vector2(200, 200);  // Far away from the circuit

        // Testing the loop with larger list and edge conditions
        assertTrue(checkpoints.isInsideCircuit(pointEdge));
        assertFalse(checkpoints.isInsideCircuit(pointFar));
    }

    @Test
    void testLoopWithZeroPoints() {
        List<Vector2> points = createControlPoints(0);  // Zero points should throw an error
        assertThrows(IllegalArgumentException.class, () -> new Checkpoints(points));
        }

    @Test
    void testisInsideTrackNullPoint() {
        List<Vector2> points = createControlPoints(10);
        Checkpoints checkpoints = new Checkpoints(points);
        assertThrows(IllegalArgumentException.class, () -> checkpoints.isInsideCircuit(null));
    }
}
