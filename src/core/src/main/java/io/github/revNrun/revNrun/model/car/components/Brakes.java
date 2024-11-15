package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public class Brakes extends WheelMountedComponent {
    public Brakes(String name, float weight, int maxDurability, int currentDurability,
                  List<Effect> effects, CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side, wearFactor);
    }

    @Override
    public void degrade(float percentage) {

    }

    @Override
    public void degrade(float value, float delta) {

    }
}
