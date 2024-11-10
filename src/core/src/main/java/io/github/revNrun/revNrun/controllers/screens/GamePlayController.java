package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.revNrun.revNrun.RevNRun;
import io.github.revNrun.revNrun.views.GamePlayView;

public class GamePlayController extends Controller {
    public GamePlayController(RevNRun revNRun, SpriteBatch batch) {
        super(revNRun, batch);
        view = new GamePlayView(batch);
    }

    @Override
    public void execute() {

    }
}
