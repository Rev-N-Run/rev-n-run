package io.github.revNrun.revNrun.controllers.input;

import com.badlogic.gdx.Gdx;

public class LibGDXInputHelper implements InputHelper {
    @Override
    public boolean isKeyPressed(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }
}
