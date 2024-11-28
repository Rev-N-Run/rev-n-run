package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.controllers.vectorController.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GhostCar {
    private List<GhostState> states = new ArrayList<>();
    private int curretStateIndex = 0;

    public void recordState(Vector2 position, float angle, float timestamp) {
        states.add(new GhostState(position, angle, timestamp));
    }

    public float getPositionX(int index) {
        GhostState state = states.get(index);
        return state.getX();
    }

    public float getPositionY(int index) {
        GhostState state = states.get(index);
        return state.getY();
    }

    public float getAngle(int index) {
        GhostState state = states.get(index);
        return state.getAngle();
    }

    public float getTimestamp(int index) {
        GhostState state = states.get(index);
        return state.getTimestamp();
    }
}
