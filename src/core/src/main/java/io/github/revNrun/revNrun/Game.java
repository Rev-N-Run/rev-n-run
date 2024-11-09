package io.github.revNrun.revNrun;

import com.badlogic.gdx.ApplicationAdapter;
import io.github.revNrun.revNrun.controllers.screens.Controller;

public class Game extends ApplicationAdapter {
    private Controller currentController;

    public Game(){
        System.out.println("This is the game's Main class");
    }

    @Override
    public void render() {
        System.out.println("hola");
    }
}
