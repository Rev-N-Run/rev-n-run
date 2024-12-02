package io.github.revNrun.revNrun.controllers.game;

import io.github.revNrun.revNrun.controllers.screen.GameStatus;
import io.github.revNrun.revNrun.view.countdown.ICountdownView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyFloat;
import static org.mockito.Mockito.*;

class CountdownControllerTest {
    ICountdownView mockView;
    CountdownController controller;
    @BeforeEach
    void setUp() {
        mockView = mock(ICountdownView.class);
        controller = new CountdownController(mockView);
    }

    @Test
    void count() {
        GameStatus status;

        // Test first number (3)
        status = controller.count(0.5f); // Within first second
        assertEquals(GameStatus.STOP, status);
        verify(mockView).setScale(anyFloat());
        verify(mockView).setNumberAlpha(anyFloat());
        verify(mockView).draw(3);

        // Test second number (2)
        status = controller.count(0.7f); // Move to second
        assertEquals(GameStatus.STOP, status);
        verify(mockView).draw(2);

        // Test third number (1)
        status = controller.count(0.8f); // Move to third
        assertEquals(GameStatus.STOP, status);
        verify(mockView).draw(1);

        // Test "RUN" display
        status = controller.count(1.0f); // Move to RUN
        assertEquals(GameStatus.START, status);
        verify(mockView).setShowRun(true);
        verify(mockView).setTitleAlpha(0);
        verify(mockView).setRunAlpha(1f);

        // Test fade out
        status = controller.count(1.0f); // Move to fade out
        assertEquals(GameStatus.START, status);
        verify(mockView, atLeastOnce()).setRunAlpha(anyFloat());

        // Test countdown finish
        status = controller.count(1.0f); // Complete countdown
        assertEquals(GameStatus.ONGOING, status);
    }

    @Test
    void testHandleNumberTransitionEarlyPhase() {
        // Test early phase of number transition (zoom effect)
        controller.count(0.2f); // Within zoomTime

        verify(mockView).setScale(anyFloat());
        verify(mockView).setNumberAlpha(1f);
        verify(mockView).draw(3);
    }

    @Test
    void testHandleNumberTransitionLatePhase() {
        // Test late phase of number transition (fade effect)
        controller.count(0.8f); // After zoomTime

        verify(mockView).setNumberAlpha(anyFloat());
        verify(mockView).setScale(1.5f);
        verify(mockView).draw(3);
    }
}
