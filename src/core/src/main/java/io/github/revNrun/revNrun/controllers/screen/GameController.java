package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Game;
import io.github.revNrun.revNrun.view.GameView;

public class GameController extends ScreenController {
    public GameController(Game game, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(game);
        view = new GameView(viewport, camera, batch);
    }
}
