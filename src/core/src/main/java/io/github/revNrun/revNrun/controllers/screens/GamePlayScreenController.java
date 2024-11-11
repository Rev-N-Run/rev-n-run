package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.views.GamePlayView;

public class GamePlayScreenController extends ScreenController {
    public GamePlayScreenController(Game revNRun, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(revNRun);
        view = new GamePlayView(batch, viewport, camera);
    }

    @Override
    public void render(float delta) {

    }
}
