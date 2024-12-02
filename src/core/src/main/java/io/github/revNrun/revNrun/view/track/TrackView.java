package io.github.revNrun.revNrun.view.track;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.View;

import java.util.List;

public class TrackView extends View implements ITrackView {
    private final ShapeRenderer shapeRenderer;

    public TrackView() {
        shapeRenderer = new ShapeRenderer();
    }

    @Override
    public void drawTrack(List<Vector2> leftBorder, List<Vector2> rightBorder) {
        ScreenUtils.clear(0, 0, 0, 1);
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
                bl.getX(), bl.getY(),
                br.getX(), br.getY(),
                tr.getX(), tr.getY());
            shapeRenderer.triangle(
                bl.getX(), bl.getY(),
                tr.getX(), tr.getY(),
                tl.getX(), tl.getY());
        }

        shapeRenderer.end();
    }

    @Override
    public void drawLifeByCheckPoints(int lifeByCheckPoints) {

    }

    /*public void dispose() {
        shapeRenderer.dispose();
    }*/
}
