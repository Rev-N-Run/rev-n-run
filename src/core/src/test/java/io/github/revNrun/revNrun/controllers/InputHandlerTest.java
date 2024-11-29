package io.github.revNrun.revNrun.controllers;

import com.badlogic.gdx.Input;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.InputHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InputHandlerTest {
    private InputHandler inputHandler;
    private InputHelper mockInputHelper;

    @BeforeEach
    void setUp() {
        mockInputHelper = mock(InputHelper.class);
        inputHandler = new InputHandler(mockInputHelper);
    }

    @Test
    void isSpacePressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.SPACE)).thenReturn(true);
        assertTrue(inputHandler.isSpacePressed());
    }

    @Test
    void isUpPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        assertTrue(inputHandler.isUpPressed());
    }

    @Test
    void isUpNotPressed() {
        assertFalse(inputHandler.isUpPressed());
    }

    @Test
    void isDownPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);
        assertTrue(inputHandler.isDownPressed());
    }

    @Test
    void isDownNotPressed() {
        assertFalse(inputHandler.isDownPressed());
    }

    @Test
    void isLeftPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        assertTrue(inputHandler.isLeftPressed());
    }

    @Test
    void isLeftNotPressed() {
        assertFalse(inputHandler.isLeftPressed());
    }

    @Test
    void isRightPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        assertTrue(inputHandler.isRightPressed());
    }

    @Test
    void isRightNotPressed() {
        assertFalse(inputHandler.isRightPressed());
    }

    @Test
    void isWPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.W)).thenReturn(true);
        assertTrue(inputHandler.isUpPressed());
    }

    @Test
    void isWNotPressed() {
        assertFalse(inputHandler.isUpPressed());
    }

    @Test
    void isSPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.S)).thenReturn(true);
        assertTrue(inputHandler.isDownPressed());
    }

    @Test
    void isSNotPressed() {
        assertFalse(inputHandler.isDownPressed());
    }

    @Test
    void isDPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.D)).thenReturn(true);
        assertTrue(inputHandler.isRightPressed());
    }

    @Test
    void isDNotPressed() {
        assertFalse(inputHandler.isRightPressed());
    }

    @Test
    void isAPressed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.A)).thenReturn(true);
        assertTrue(inputHandler.isLeftPressed());
    }

    @Test
    void isANotPressed() {
        assertFalse(inputHandler.isLeftPressed());
    }
}
