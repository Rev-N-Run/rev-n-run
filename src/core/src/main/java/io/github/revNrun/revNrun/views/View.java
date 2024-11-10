package io.github.revNrun.revNrun.views;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class View {
    protected Viewport viewport;
    public View(Viewport viewport) {
        this.viewport = viewport;
    }

    public abstract void render();

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }
}
