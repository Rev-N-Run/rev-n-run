package io.github.revNrun.revNrun.model.car.components.mocks;

import io.github.revNrun.revNrun.model.car.components.*;
import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public class MockBrakes extends Brakes implements MockComponent {
    private float lastDegradation;

    public MockBrakes(String name, float weight, int maxDurability, int currentDurability,
                      List<Effect> effects, CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side, wearFactor);
    }

    @Override
    public void degrade(float delta, float percentage) {
        currentDurability *= percentage;
        lastDegradation = percentage;
    }

    @Override
    public float getLastDegradation() {
        return lastDegradation;
    }
}
