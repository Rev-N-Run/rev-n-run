package io.github.revNrun.revNrun.controllers.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.views.MainMenuView;

public class MainMenuScreenController extends ScreenController {
    public MainMenuScreenController(Game revNRun, SpriteBatch batch, Viewport viewport, Camera camera) {
        super(revNRun);
        view = new MainMenuView(batch, viewport, camera);
    }

    @Override
    public void render(float delta) {

    }
}
