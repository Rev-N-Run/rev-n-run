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
    Sprite carSprite;
    Texture trackTexture;


    public CarView() {
        carTexture = new Texture(Gdx.files.internal("car.png"));
        carSprite = new Sprite(carTexture);
        trackTexture = new Texture(Gdx.files.internal("track.png"));
    }

    public void create(Vector2 position, float angle) {
        carSprite.setSize(100, 40);
        carSprite.setPosition(position.getX(), position.getY());
        carSprite.setOrigin(carSprite.getWidth() / 2, carSprite.getHeight() / 2);
        carSprite.setRotation(angle);
    }

    public void draw(Vector2 position, float angle) {
        ScreenUtils.clear(0, 0, 0, 1);
        spriteBatch.setProjectionMatrix(camera.combined);

        carSprite.setRotation(angle);
        carSprite.setX(position.getX());
        carSprite.setY(position.getY());

        spriteBatch.begin();
        carSprite.draw(spriteBatch);
        spriteBatch.end();
    }
}
