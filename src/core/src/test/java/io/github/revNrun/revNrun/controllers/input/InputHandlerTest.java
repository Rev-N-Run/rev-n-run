package io.github.revNrun.revNrun.controllers.input;

import com.badlogic.gdx.Input;
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
}
