package io.github.revNrun.revNrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.controllers.screen.GameScreenController;
import io.github.revNrun.revNrun.controllers.screen.ScreenController;

public class Main extends Game {
    private ScreenController screenController;
    private SpriteBatch batch;
    private Viewport viewport;
    private Camera camera;

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(30, 40, camera);
        screenController = new GameScreenController(this, batch, viewport, camera);
        setScreen(screenController);
    }
}
