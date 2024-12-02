package io.github.revNrun.revNrun;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.controllers.game.track.SingletonTrack;
import io.github.revNrun.revNrun.controllers.screen.GameScreenController;
import io.github.revNrun.revNrun.controllers.screen.ScreenController;
import io.github.revNrun.revNrun.view.ViewUtils;

public class Main extends Game {
    private ScreenController screenController;
    private SpriteBatch batch;
    private Viewport viewport;
    private OrthographicCamera camera;

    @Override
    public void create() {
        // Init the track in order to get the world dimensions for the camera and viewport
        SingletonTrack.getTrack();

        batch = new SpriteBatch();
        camera = ViewUtils.getCamera();
        viewport = ViewUtils.getViewport();
        screenController = new GameScreenController(this);
        setScreen(screenController);
    }

    @Override
    public void render() {
        super.render();
    }
}
