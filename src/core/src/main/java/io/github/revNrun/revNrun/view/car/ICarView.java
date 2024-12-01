package io.github.revNrun.revNrun.view.car;

import io.github.revNrun.revNrun.model.vector.Vector2;

public interface ICarView {
    void create(Vector2 position, float angle);
    void draw(Vector2 position, float angle);
    void drawGhost(Vector2 position, float angle);
    float getCarWidth();
    float getCarHeight();
}
