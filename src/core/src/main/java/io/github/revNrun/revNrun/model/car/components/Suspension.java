package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public class Suspension extends WheelMountedComponent {
    public Suspension(String name, float weight, float maxDurability, float currentDurability, List<Effect> effects,
                      CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side, wearFactor);
    }
}
