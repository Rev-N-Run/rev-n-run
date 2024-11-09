package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.Input;

public class InputHandler {
    private InputHelper inputHelper;

    public InputHandler(InputHelper inputHelper) {
        this.inputHelper = inputHelper;
    }

    public boolean isSpacePressed() {
        return inputHelper.isKeyPressed(Input.Keys.SPACE);
    }
}
