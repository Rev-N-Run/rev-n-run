package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.ScreenAdapter;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.view.View;

public abstract class ScreenController extends ScreenAdapter {
    protected Main game;
    protected View view;

    public ScreenController(Main game) {
        this.game = game;
    }

    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
