package io.github.revNrun.revNrun.view.car;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.View;
import io.github.revNrun.revNrun.view.ViewUtils;

public class CarView extends View implements ICarView {
    private Texture carTexture;
    private Texture ghostTexture;
    private Sprite carSprite;
    private Sprite ghostSprite;


    public CarView() {
        carTexture = new Texture(Gdx.files.internal("car.png"));
        carSprite = new Sprite(carTexture);
        ghostTexture = new Texture(Gdx.files.internal("ghost.png"));
        ghostSprite = new Sprite(ghostTexture);
    }

    @Override
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

    @Override
    public void draw(Vector2 position, float angle) {
        spriteBatch.setProjectionMatrix(camera.combined);

        carSprite.setRotation(angle);
        carSprite.setX(position.getX());
        carSprite.setY(position.getY());

        spriteBatch.begin();
        carSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void drawGhost(Vector2 position, float angle) {
        spriteBatch.setProjectionMatrix(camera.combined);

        ghostSprite.setRotation(angle);
        ghostSprite.setX(position.getX());
        ghostSprite.setY(position.getY());

        spriteBatch.begin();
        ghostSprite.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public float getCarWidth() {
        return carSprite.getWidth();
    }

    @Override
    public float getCarHeight() {
        return carSprite.getHeight();
    }
}
