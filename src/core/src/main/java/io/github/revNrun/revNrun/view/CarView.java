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
    private Sprite carSprite;
    private Texture trackTexture;


    public CarView() {
        carTexture = new Texture(Gdx.files.internal("car.png"));
        carSprite = new Sprite(carTexture);
        trackTexture = new Texture(Gdx.files.internal("track.png"));
    }

    public void create(Vector2 position, float angle) {
        float width = 100;
        float aspectRatio = ViewUtils.WORLD_WIDTH / ViewUtils.WORLD_HEIGHT;
        float height = width * aspectRatio;
        carSprite.setSize(width, height);
        carSprite.setPosition(position.getX(), position.getY());
        //carSprite.setOrigin(carSprite.getWidth() / 2, carSprite.getHeight() / 2);
        carSprite.setOriginCenter();
        carSprite.setRotation(angle);
    }

    public void draw(Vector2 position, float angle) {
        //ScreenUtils.clear(0, 0, 0, 1);
        spriteBatch.setProjectionMatrix(camera.combined);

        carSprite.setRotation(angle);
        carSprite.setX(position.getX());
        carSprite.setY(position.getY());

        spriteBatch.begin();
        //spriteBatch.draw(trackTexture, 0, 0, ViewUtils.WORLD_WIDTH, ViewUtils.WORLD_HEIGHT);
        carSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    public float getCarWidth() {
        return carSprite.getWidth();
    }

    public float getCarHeight() {
        return carSprite.getHeight();
    }
}
