package io.github.revNrun.revNrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.controllers.screens.ScreenController;
import io.github.revNrun.revNrun.controllers.screens.MainMenuScreenController;

public class RevNRun extends Game {
    private ScreenController currentController;
    private SpriteBatch batch;
    private Viewport viewport;
    private Camera camera;

    public RevNRun(){
        System.out.println("This is the game's Main class");
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        camera = new OrthographicCamera();
        viewport = new FitViewport(30, 40, camera);
        currentController = new MainMenuScreenController(this, batch, viewport, camera);
        setScreen(currentController);
    }

    @Override
    public void render() {
        super.render();
        //System.out.println("hola");
    }

    @Override
    public void resize(int width, int height) {
        currentController.resize(width, height);
    }
}
