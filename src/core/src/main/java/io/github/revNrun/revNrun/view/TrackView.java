package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.PolygonRegion;
import com.badlogic.gdx.graphics.g2d.PolygonSprite;
import com.badlogic.gdx.graphics.g2d.PolygonSpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class TrackView {
    private final ShapeRenderer shapeRenderer;

    public TrackView() {
        shapeRenderer = new ShapeRenderer();
    }

    public void drawTrack(List<Vector2> leftBorder, List<Vector2> rightBorder) {
        // Update the camera
        OrthographicCamera camera = ViewUtils.getCamera();
        camera.update();
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Begin rendering the track
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.WHITE);

        // Draw the track as filled triangles (get the points as quadrilaterals and make two triangles from them)
        for (int i = 0; i < leftBorder.size(); i++) {
            Vector2 bl = leftBorder.get(i);
            Vector2 br = rightBorder.get(i);
            Vector2 tl = leftBorder.get((i + 1) % leftBorder.size());
            Vector2 tr = rightBorder.get((i + 1) % rightBorder.size());
            shapeRenderer.triangle(
                camera.position.x + bl.getX(), camera.position.y + bl.getY(),
                camera.position.x + br.getX(), camera.position.y + br.getY(),
                camera.position.x + tr.getX(), camera.position.y + tr.getY());
            shapeRenderer.triangle(
                camera.position.x + bl.getX(), camera.position.y + bl.getY(),
                camera.position.x + tr.getX(), camera.position.y + tr.getY(),
                camera.position.x + tl.getX(), camera.position.y + tl.getY());
        }

        shapeRenderer.end();
    }

    public void drawLifeByCheckPoints(int lifeByCheckPoints) {

    }

    public void dispose() {
        shapeRenderer.dispose();
    }
}
