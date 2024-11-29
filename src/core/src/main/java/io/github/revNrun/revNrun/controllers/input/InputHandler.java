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
        return inputHelper.isKeyPressed(Input.Keys.UP) ||  inputHelper.isKeyPressed(Input.Keys.W);
    }

    public boolean isDownPressed() {
        return inputHelper.isKeyPressed(Input.Keys.DOWN) ||  inputHelper.isKeyPressed(Input.Keys.S);
    }

    public boolean isLeftPressed() {
        return inputHelper.isKeyPressed(Input.Keys.LEFT) ||  inputHelper.isKeyPressed(Input.Keys.A);
    }

    public boolean isRightPressed() {
        return inputHelper.isKeyPressed(Input.Keys.RIGHT) ||  inputHelper.isKeyPressed(Input.Keys.D);
    }
}
