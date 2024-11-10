package io.github.revNrun.revNrun.controllers.input;

import com.badlogic.gdx.Input;

public class InputHandler {
    private InputHelper inputHelper;

    public InputHandler(InputHelper inputHelper) {
        this.inputHelper = inputHelper;
    }

    public InputHandler() {
        this.inputHelper = new LibGDXInputHelper();
    }

    public boolean isSpacePressed() {
        return inputHelper.isKeyPressed(Input.Keys.SPACE);
    }
}
