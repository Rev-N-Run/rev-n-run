package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class TrackView {
    ShapeRenderer shapeRenderer;


    public TrackView() {
        shapeRenderer = new ShapeRenderer();
    }

    public void drawTrack(List<Vector2> vertices) {
        // Update the camera
        OrthographicCamera camera = ViewUtils.getCamera();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Begin rendering the track
        shapeRenderer.begin(ShapeRenderer.ShapeType.Line);
        shapeRenderer.setColor(Color.WHITE);


        // Draw the track as a polygon
        for (int i = 0; i < vertices.size(); i++) {
            Vector2 current = vertices.get(i);
            Vector2 next = vertices.get((i + 1) % vertices.size()); // Connect to the next vertex (loop back)
            shapeRenderer.line(
                camera.position.x + current.getX(),
                camera.position.y + current.getY(),
                camera.position.x + next.getX(),
                camera.position.y + next.getY());
        }

        shapeRenderer.end();
    }

    public void drawLifeByCheckPoints(int lifeByCheckPoints) {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
