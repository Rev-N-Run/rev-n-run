package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GhostCar {
    private List<GhostState> states = new ArrayList<>();
    private int currentStateIndex = 0;

    public void recordState(Vector2 position, float angle, float timestamp) {
        states.add(new GhostState(position, angle, timestamp));
    }

    public void nextFrame() {
        currentStateIndex++;
        int size = states.size();
        if (currentStateIndex >= size) {
            currentStateIndex = size - 1;
        }
    }

    public void reset() {
        currentStateIndex = 0;
        states.clear();
    }

    public float getPositionX() {
        GhostState state = states.get(currentStateIndex);
        return state.getX();
    }

    public float getPositionY() {
        GhostState state = states.get(currentStateIndex);
        return state.getY();
    }

    public float getAngle() {
        GhostState state = states.get(currentStateIndex);
        return state.getAngle();
    }

    public float getTimestamp() {
        GhostState state = states.get(currentStateIndex);
        return state.getTimestamp();
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }
}
