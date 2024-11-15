package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public class Brakes extends WheelMountedComponent {
    public Brakes(String name, float weight, int maxDurability, int currentDurability,
                  List<Effect> effects, CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side);
    }

    @Override
    public void degrade(float percentage) {

    }

    @Override
    public void degrade(float value, float delta) {

    }
}
