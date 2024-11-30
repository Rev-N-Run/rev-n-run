package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.controllers.game.car.CarController;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.CreateCar;
import io.github.revNrun.revNrun.view.GameView;

public class GameScreenController extends ScreenController {
    private CarController controller;

    public GameScreenController(Main game, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(game);
        view = new GameView(viewport, camera, batch);
        controller = new CarController(CreateCar.createCar(), new InputHandler(new LibGDXInputHelper()));
    }

    @Override
    public void render(float delta) {
        controller.execute(delta);
    }
}
