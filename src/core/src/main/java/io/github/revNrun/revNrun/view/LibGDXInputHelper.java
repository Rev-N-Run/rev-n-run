package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.Gdx;

public class LibGDXInputHelper implements InputHelper {
    @Override
    public boolean isKeyPressed(int keycode) {
        return Gdx.input.isKeyPressed(keycode);
    }
}
