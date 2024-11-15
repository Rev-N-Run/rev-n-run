package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public class Tires extends WheelMountedComponent {

    public Tires(String name, float weight, int maxDurability, int currentDurability,
                 List<Effect> effects, CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side, wearFactor);
    }

    @Override
    public void degrade(float percentage) {
        if (percentage >= 1 || percentage <= 0) {
            throw new IllegalArgumentException("Invalid percentage: " + percentage +
                "\nNeeds to be in range (0-1)");
        }
        currentDurability *= (1 - percentage);
    }

    @Override
    public void degrade(float value, float delta) {

    }
}
