package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.FitViewport;

public class ViewUtils {
    private static OrthographicCamera camera = null;
    private static FitViewport viewport = null;
    private static SpriteBatch spriteBatch = null;

    public static OrthographicCamera getCamera() {
        if (camera == null) {
            camera = new OrthographicCamera();
            camera.setToOrtho(false, 200, 200);
        }
        return camera;
    }

    public static FitViewport getViewport() {
        if (viewport == null) {
            viewport = new FitViewport(200, 200, camera);
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
