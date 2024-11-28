package io.github.revNrun.revNrun.model.ghost_car;

import io.github.revNrun.revNrun.model.car.Car;

import java.util.ArrayList;
import java.util.List;

public class GhostCar {
    private List<GhostState> states = new ArrayList<>();
    private int curretStateIndex = 0;

    public void recordState(Car car, float timestamp) {
        states.add(new GhostState(car.getPosition(), car.getAngle(), timestamp));
    }
}
