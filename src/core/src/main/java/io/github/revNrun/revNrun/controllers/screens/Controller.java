package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.ScreenAdapter;
import io.github.revNrun.revNrun.views.View;

public abstract class Controller extends ScreenAdapter {
    protected Game game;
    protected View view;

    public Controller(Game revNRun) {
        this.game = revNRun;
    }

    public abstract void execute();

    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
