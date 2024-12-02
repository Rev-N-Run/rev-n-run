package io.github.revNrun.revNrun.view.track;

import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public interface ITrackView {
    void drawTrack(List<Vector2> leftBorder, List<Vector2> rightBorder);
    void drawLifeByCheckPoints(int lifeByCheckPoints);
}
