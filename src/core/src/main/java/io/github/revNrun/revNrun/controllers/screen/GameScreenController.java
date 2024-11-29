package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.controllers.game.GameController;
import io.github.revNrun.revNrun.controllers.game.car.CarController;
import io.github.revNrun.revNrun.view.GameView;

public class GameScreenController extends ScreenController {
    private GameController controller;

    public GameScreenController(Main game, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(game);
        view = new GameView(viewport, camera, batch);
        controller = new GameController();
    }

    @Override
    public void render(float delta) {
        //controller.execute(delta);
    }
}
