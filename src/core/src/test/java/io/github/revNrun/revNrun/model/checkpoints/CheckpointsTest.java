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

        Checkpoints checkpoints = new Checkpoints(track, 0);

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
}
