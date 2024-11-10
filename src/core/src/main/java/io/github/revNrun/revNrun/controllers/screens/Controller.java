package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.ScreenAdapter;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.revNrun.revNrun.RevNRun;
import io.github.revNrun.revNrun.core.GameState;
import io.github.revNrun.revNrun.views.View;

public abstract class Controller extends ScreenAdapter {
    protected GameState state;
    protected RevNRun game;
    protected View view;

    public Controller(RevNRun revNRun, SpriteBatch batch) {
        this.game = revNRun;
    }

    public abstract void execute();

    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
