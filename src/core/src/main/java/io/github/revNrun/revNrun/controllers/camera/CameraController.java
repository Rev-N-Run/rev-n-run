package io.github.revNrun.revNrun.controllers.camera;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.ViewUtils;

public class CameraController {
    private OrthographicCamera camera;
    private Viewport viewport;

    public CameraController() {
        camera = ViewUtils.getCamera();
        viewport = ViewUtils.getViewport();
    }

    public void calculateCameraPosition(Vector2 position, float carWidth, float carHeight) {
        float cameraX = position.getX() + carWidth / 2;
        float cameraY = position.getY() + carHeight / 2;

        float vpWidth = viewport.getWorldWidth();
        float vpHeight = viewport.getWorldHeight();

        if (cameraX < vpWidth / 2) {
            cameraX = vpWidth / 2;
        } else if (cameraX > ViewUtils.WORLD_WIDTH - vpWidth / 2) {
            cameraX = ViewUtils.WORLD_WIDTH - vpWidth / 2;
        }
        if (cameraY < vpHeight / 2) {
            cameraY = vpHeight / 2;
        } else if (cameraY > ViewUtils.WORLD_HEIGHT - vpHeight / 2) {
            cameraY = ViewUtils.WORLD_HEIGHT - vpHeight / 2;
        }

        camera.position.set(cameraX, cameraY, 0);
    }

    public void update() {
        camera.update();
        viewport.apply();
    }
}
