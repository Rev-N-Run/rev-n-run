package io.github.revNrun.revNrun;

import com.badlogic.gdx.ApplicationAdapter;
import io.github.revNrun.revNrun.controllers.screens.Controller;
import io.github.revNrun.revNrun.controllers.screens.MainMenuController;
import io.github.revNrun.revNrun.core.GameState;
import io.github.revNrun.revNrun.views.GamePlayView;

public class Game extends ApplicationAdapter {
    private Controller currentController;

    public Game(){
        System.out.println("This is the game's Main class");
    }

    @Override
    public void create() {

    }

    @Override
    public void render() {
        System.out.println("hola");
    }

    public void setCurrentController(Controller newController){
        currentController = newController;
    }

    @Override
    public void resize(int width, int height) {
        currentController.resize(width, height);
    }
}
