package io.github.revNrun.revNrun.model.car.components.mocks;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.Effect;
import io.github.revNrun.revNrun.model.car.components.Tires;

import java.util.List;

public class MockTires extends Tires implements MockComponent {
    private float lastDegradation;

    public MockTires(String name, float weight, int maxDurability,
                     int currentDurability, List<Effect> effects,
                     CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side);
    }

    @Override
    public void degrade(float percentage) {
        currentDurability -= percentage;
        lastDegradation = percentage;
    }

    public float getLastDegradation() {
        return lastDegradation;
    }
}
