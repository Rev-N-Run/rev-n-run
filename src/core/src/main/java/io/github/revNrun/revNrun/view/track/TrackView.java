package io.github.revNrun.revNrun.view.track;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.ScreenUtils;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.View;
import io.github.revNrun.revNrun.view.ViewUtils;

import java.util.List;

public class TrackView extends View implements ITrackView {
    private final ShapeRenderer shapeRenderer;
    private final Texture sandTexture;
    private final TextureRegion sandRegion;
    private final Color trackColor;
    private static final float TEXTURE_SCALE = 5f;
    private float worldWidth;
    private float worldHeight;
    private boolean boundsInitialized = false;

    public TrackView() {
        shapeRenderer = new ShapeRenderer();

        sandTexture = new Texture(Gdx.files.internal("textures/sand.png"));
        sandTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);

        // Create a scaled texture region
        sandRegion = new TextureRegion(sandTexture);

        trackColor = new Color(0.3f, 0.3f, 0.3f, 1f);
    }

    private void initWorldBounds(List<Vector2> leftBorder, List<Vector2> rightBorder) {
        if (boundsInitialized) return;

        worldWidth = ViewUtils.WORLD_WIDTH;
        worldHeight = ViewUtils.WORLD_HEIGHT;

        // Set the texture region to tile based on world size
        float textureWidth = sandTexture.getWidth() * (1 / TEXTURE_SCALE);
        float textureHeight = sandTexture.getHeight() * (1 / TEXTURE_SCALE);
        sandRegion.setRegion(0, 0, worldWidth / textureWidth, worldHeight / textureHeight);

        boundsInitialized = true;
    }

    @Override
    public void drawTrack(List<Vector2> leftBorder, List<Vector2> rightBorder) {
        initWorldBounds(leftBorder, rightBorder);

        // Clear with a base sand color
        ScreenUtils.clear(0.87f, 0.73f, 0.53f, 1);

        // Draw the textured background
        spriteBatch.begin();
        spriteBatch.setProjectionMatrix(camera.combined);
        spriteBatch.draw(sandRegion, 0, 0, worldWidth, worldHeight);
        spriteBatch.end();

        // Draw the track
        shapeRenderer.setProjectionMatrix(camera.combined);
        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(trackColor);

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

    public void dispose() {
        shapeRenderer.dispose();
        sandTexture.dispose();
    }
}
