package io.github.revNrun.revNrun.view;

import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.car.ICarView;

public class CarViewMock implements ICarView {
    @Override
    public void create(Vector2 position, float angle) {

    }

    @Override
    public void draw(Vector2 position, float angle) {

    }

    @Override
    public void drawGhost(Vector2 position, float angle) {

    }

    @Override
    public float getCarWidth() {
        return 1;
    }

    @Override
    public float getCarHeight() {
        return 1;
    }
}
