package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class ViewUtils {
    private static OrthographicCamera camera = null;
    private static FitViewport viewport = null;
    private static SpriteBatch spriteBatch = null;

    public static OrthographicCamera getCamera() {
        if (camera == null) {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, ViewUtils.WORLD_WIDTH, ViewUtils.WORLD_HEIGHT);
        }
        return camera;
    }

    public static FitViewport getViewport() {
        if (viewport == null) {
            viewport = new FitViewport(WORLD_WIDTH, WORLD_HEIGHT, camera);
        }
        return viewport;
    }

    public static SpriteBatch getSpriteBatch() {
        if (spriteBatch == null) {
            spriteBatch = new SpriteBatch();
        }
        return spriteBatch;
    }

    public static float WORLD_WIDTH = 1000.0f;
    public static float WORLD_HEIGHT = 1000.0f;
}
