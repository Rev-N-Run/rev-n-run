package io.github.revNrun.revNrun.controllers.game.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.TrackView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


class TrackControllerTest {
    private TrackController trackController;
    private TrackView trackView;  // Mocked view (TrackView)

    @BeforeEach
    void setUp() {
        trackView = mock(TrackView.class);  // Mock the TrackView class with mockito
        trackController = new TrackController();  // Instantiate the controller
        trackController.setTrackView(trackView);
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

        verify(trackView).drawTrack(mockLeftBorder, mockRightBorder);
    }

    @Test
    void testDrawLifeByCheckPoints() {
        int mockLifeByCheckPoints = 10;
        trackController.setLifeByCheckPoints(mockLifeByCheckPoints);

        trackController.draw();

        verify(trackView).drawLifeByCheckPoints(mockLifeByCheckPoints);
    }

    @Test
    void testCarInTrack() {
        trackController.setCarInTrack(true);
        assertTrue(trackController.isCarInTrack());
    }
}
