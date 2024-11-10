package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.revNrun.revNrun.RevNRun;
import io.github.revNrun.revNrun.core.GameState;
import io.github.revNrun.revNrun.views.MainMenuView;
import io.github.revNrun.revNrun.views.View;

public class MainMenuController extends Controller {
    public MainMenuController(RevNRun revNRun, SpriteBatch batch) {
        super(revNRun, batch);
        view = new MainMenuView(batch);
    }

    @Override
    public void execute() {

    }
}
