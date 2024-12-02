package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;

public abstract class View {
    protected Viewport viewport;
    protected Camera camera;
    protected SpriteBatch spriteBatch;

    public View() {
        this.viewport = ViewUtils.getViewport();
        this.camera = ViewUtils.getCamera();
        this.spriteBatch = ViewUtils.getSpriteBatch();
    }

    public void resize(int width, int height) {
        viewport.update(width, height, true);
        viewport.apply();
    }
}
