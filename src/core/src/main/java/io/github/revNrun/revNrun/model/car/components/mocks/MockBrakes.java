package io.github.revNrun.revNrun.model.car.components.mocks;

import io.github.revNrun.revNrun.model.car.components.*;

import java.util.List;

public class MockBrakes extends Brakes implements MockComponent {
    private float lastDegradation;

    public MockBrakes(String name, float weight, int maxDurability, int currentDurability,
                                 List<Effect> effects, CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side);
    }

    @Override
    public float getLastDegradation() {
        return 0;
    }
}
