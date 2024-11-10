package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Game;
import io.github.revNrun.revNrun.core.GameState;
import io.github.revNrun.revNrun.views.View;

public abstract class Controller {
    protected GameState state;
    protected Game game;
    protected View view;

    public Controller(Game game, View view, GameState state) {
        this.game = game;
        this.view = view;
        this.state = state;
    }

    public void resize(int width, int height) {
        view.resize(width, height);
    }
}
