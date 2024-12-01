package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.model.vector.Vector2;

// TESTING RN
public class CarView extends View {
    private Texture carTexture;
    private Texture ghostTexture;
    private Sprite carSprite;
    private Texture trackTexture;
    private Sprite ghostSprite;


    public CarView() {
        carTexture = new Texture(Gdx.files.internal("car.png"));
        carSprite = new Sprite(carTexture);
        trackTexture = new Texture(Gdx.files.internal("track.png"));
        ghostTexture = new Texture(Gdx.files.internal("ghost.png"));
        ghostSprite = new Sprite(ghostTexture);
    }

    public void create(Vector2 position, float angle) {
        float width = 10;
        float aspectRatio = ViewUtils.WORLD_WIDTH / ViewUtils.WORLD_HEIGHT;
        float height = width * aspectRatio;
        carSprite.setSize(width, height);
        carSprite.setPosition(position.getX(), position.getY());
        carSprite.setOriginCenter();
        carSprite.setRotation(angle);
        ghostSprite.setSize(width * 1.3f, height * 1.3f);
        ghostSprite.setPosition(position.getX(), position.getY());
        ghostSprite.setOriginCenter();
        ghostSprite.setRotation(angle);
    }

    public void draw(Vector2 position, float angle) {
        spriteBatch.setProjectionMatrix(camera.combined);

        carSprite.setRotation(angle);
        carSprite.setX(position.getX());
        carSprite.setY(position.getY());

        spriteBatch.begin();
        carSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public void drawGhost(Vector2 position, float angle) {
        spriteBatch.setProjectionMatrix(camera.combined);

        ghostSprite.setRotation(angle);
        ghostSprite.setX(position.getX());
        ghostSprite.setY(position.getY());

        spriteBatch.begin();
        ghostSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public float getCarWidth() {
        return carSprite.getWidth();
    }

    public float getCarHeight() {
        return carSprite.getHeight();
    }
}
