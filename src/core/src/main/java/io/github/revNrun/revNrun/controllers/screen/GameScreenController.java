package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.controllers.car.CarController;
import io.github.revNrun.revNrun.view.GameView;

public class GameScreenController extends ScreenController {
    private CarController carController;

    public GameScreenController(Main game, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(game);
        view = new GameView(viewport, camera, batch);
        carController = new CarController();
    }

    @Override
    public void render(float delta) {
        carController.execute(delta);
    }
}
