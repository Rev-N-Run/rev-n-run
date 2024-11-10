package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.views.GamePlayView;

public class GamePlayController extends Controller {
    public GamePlayController(Game revNRun, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(revNRun);
        view = new GamePlayView(batch, viewport, camera);
    }

    @Override
    public void execute() {

    }
}
