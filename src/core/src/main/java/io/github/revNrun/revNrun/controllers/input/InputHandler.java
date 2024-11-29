package io.github.revNrun.revNrun.controllers.input;

import com.badlogic.gdx.Input;

public class InputHandler {
    private InputHelper inputHelper;

    public InputHandler(InputHelper inputHelper) {
        this.inputHelper = inputHelper;
    }

    public boolean isSpacePressed() {
        return inputHelper.isKeyPressed(Input.Keys.SPACE);
    }

    public boolean isUpPressed() {
        return false;
    }

    public boolean isDownPressed() {
        return false;
    }

    public boolean isLeftPressed() {
        return false;
    }

    public boolean isRightPressed() {
        return false;
    }
}
