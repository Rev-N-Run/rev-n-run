package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class MockComponent extends Tires {
    private float lastDegradation;
    private final CarAxis axle;
    private final CarSides side;

    public MockComponent(String name, float weight, int maxDurability,
                         int currentDurability, List<Effect> effects,
                         CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects);
        this.axle = axle;
        this.side = side;
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
