package io.github.revNrun.revNrun.model.car.components.mocks;

import io.github.revNrun.revNrun.model.car.components.CarAxis;
import io.github.revNrun.revNrun.model.car.components.CarSides;
import io.github.revNrun.revNrun.model.car.components.Effect;
import io.github.revNrun.revNrun.model.car.components.Suspension;

import java.util.List;

public class MockSuspension extends Suspension implements MockComponent {
    private float lastDegradation;

    public MockSuspension(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects,
                          CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side);
    }

    @Override
    public float getLastDegradation() {
        return lastDegradation;
    }
}
