package io.github.revNrun.revNrun.controllers.game.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.track.ITrackView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TrackControllerTest {
    private TrackController trackController;
    private ITrackView mockTrackView;  // Mocked view (TrackView)

    @BeforeEach
    void setUp() {
        mockTrackView = mock(ITrackView.class);  // Mock the TrackView class with mockito
        trackController = new TrackController(mockTrackView);  // Instantiate the controller
        trackController.setTrackView(mockTrackView);
    }

    @Test
    void testDrawTrack() {
        List<Vector2> mockRightBorder = new ArrayList<>(Arrays.asList(
            new Vector2(0, 0),
            new Vector2(1, 1),
            new Vector2(2, 2),
            new Vector2(3, 3)
        ));
        List<Vector2> mockLeftBorder = new ArrayList<>(Arrays.asList(
            new Vector2(0, 1),
            new Vector2(1, 2),
            new Vector2(2, 3),
            new Vector2(3, 4)
        ));
        trackController.setRightBorder(mockRightBorder);
        trackController.setLeftBorder(mockLeftBorder);

        trackController.draw();

        verify(mockTrackView).drawTrack(mockLeftBorder, mockRightBorder);
    }

    @Test
    void testDrawLifeByCheckPoints() {
        int mockLifeByCheckPoints = 10;
        trackController.setLifeByCheckPoints(mockLifeByCheckPoints);

        trackController.draw();

        verify(mockTrackView).drawLifeByCheckPoints(mockLifeByCheckPoints);
    }

    @Test
    void testCarInTrack() {
        trackController.setCarInTrack(true);
        assertTrue(trackController.isCarInTrack());
    }

    @Test
    void getStartPoint() {
        assertNotNull(trackController.getStartPoint());
    }

    @Test
    void isCarInStart() {
        Vector2 pos = new Vector2(Integer.MIN_VALUE, Integer.MIN_VALUE);
        assertFalse(trackController.isCarInStart(pos));

        pos = trackController.getStartPoint();
        assertTrue(trackController.isCarInStart(pos));
    }

    @Test
    void updateCarInTrack() {
        Vector2 pos = new Vector2(Integer.MIN_VALUE, Integer.MIN_VALUE);
        trackController.updateCarInTrack(pos);
        assertFalse(trackController.isCarInStart(pos));

        pos = trackController.getStartPoint();
        trackController.updateCarInTrack(pos);
        assertTrue(trackController.isCarInStart(pos));
    }
}
