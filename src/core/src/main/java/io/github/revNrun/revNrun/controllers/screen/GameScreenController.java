package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Game;
import io.github.revNrun.revNrun.controllers.car.CarController;
import io.github.revNrun.revNrun.view.GameView;

public class GameScreenController extends ScreenController {
    private CarController carController;

    public GameScreenController(Game game, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(game);
        view = new GameView(viewport, camera, batch);
        carController = new CarController();
    }
}
