package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.model.lap_timer.LapTimer;
import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.ArrayList;
import java.util.List;

public class GhostCar {
    private List<GhostState> states = new ArrayList<>();
    private int currentStateIndex = 0;
    private LapTimer lapTimer;

    public GhostCar() {
        lapTimer = new LapTimer();
    }

    public GhostCar(GhostCar that) {
        for (GhostState state : that.states) {
            this.states.add(new GhostState(state));
        }
        this.currentStateIndex = that.currentStateIndex;
        this.lapTimer = new LapTimer(that.lapTimer);
    }

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

    public void goToFirstFrame() {
        currentStateIndex = 0;
    }

    public void reset() {
        currentStateIndex = 0;
        states.clear();
    }

    public void restart() {
        currentStateIndex = 0;
    }

    public void setLap(LapTimer lap) {
        lapTimer = lap;
    }

    public LapTimer getLapTimer() {
        return lapTimer;
    }

    public float getPositionX() {
        if (notEmpty()) {
            GhostState state = states.get(currentStateIndex);
            return state.getX();
        }
        return 0;
    }

    public float getPositionY() {
        if (notEmpty()) {
            GhostState state = states.get(currentStateIndex);
            return state.getY();
        }
        return 0;
    }

    public float getAngle() {
        if (notEmpty()) {
        GhostState state = states.get(currentStateIndex);
        return state.getAngle();
        }
        return 0;
    }

    public float getTimestamp() {
        if (notEmpty()) {
        GhostState state = states.get(currentStateIndex);
        return state.getTimestamp();
        }
        return 0;
    }

    public int getCurrentStateIndex() {
        return currentStateIndex;
    }

    public void setTimer(LapTimer timer) {
        this.lapTimer = timer;
    }

    private boolean notEmpty() {
        return !states.isEmpty();
    }
}
