package io.github.revNrun.revNrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import io.github.revNrun.revNrun.controllers.screens.Controller;
import io.github.revNrun.revNrun.controllers.screens.MainMenuController;

public class RevNRun extends Game {
    private Controller currentController;
    private SpriteBatch batch;

    public RevNRun(){
        System.out.println("This is the game's Main class");
    }

    @Override
    public void create() {
        batch = new SpriteBatch();
        currentController = new MainMenuController(this, batch);
        setScreen(currentController);
    }

    @Override
    public void render() {
        super.render();
        System.out.println("hola");
    }

    @Override
    public void resize(int width, int height) {
        currentController.resize(width, height);
    }
}
