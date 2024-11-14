package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Suspension extends WheelMountedComponent {
    public Suspension(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects,
                      CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side);
    }

    @Override
    public void degrade(float percentage) {

    }
}
