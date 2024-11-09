package io.github.revNrun.revNrun.controllers.screens;

import io.github.revNrun.revNrun.Game;
import io.github.revNrun.revNrun.core.GameState;
import io.github.revNrun.revNrun.views.View;

public abstract class Controller {
    protected State state;
    protected Game game;
    protected View view;

    public Controller(Game game, View view, GameState state) {
        this.game = game;
        this.view = view;
        this.state = state;
    }
}
