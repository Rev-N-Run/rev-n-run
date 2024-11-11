package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import io.github.revNrun.revNrun.views.View;

public abstract class ScreenController extends ScreenAdapter {
    protected Game game;
    protected View view;

    public ScreenController(Game revNRun) {
        this.game = revNRun;
    }

    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
