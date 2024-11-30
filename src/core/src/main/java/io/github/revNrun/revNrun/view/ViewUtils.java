package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.OrthographicCamera;

public class ViewUtils {
    private static OrthographicCamera camera = null;

    public static OrthographicCamera getCamera() {
        if(camera == null) {
            camera = new OrthographicCamera();
        }
        return camera;
    }
}
