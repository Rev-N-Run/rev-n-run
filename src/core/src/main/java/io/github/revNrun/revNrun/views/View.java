package io.github.revNrun.revNrun.views;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class View {
    protected Viewport viewport;
    protected Camera camera;
    protected SpriteBatch batch;

    public View(SpriteBatch batch, Viewport viewport, Camera camera) {
        this.camera = camera;
        this.viewport = viewport;
        this.batch = batch;
    }

    public abstract void render();

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }
}
