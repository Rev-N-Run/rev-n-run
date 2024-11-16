package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public abstract class WheelMountedComponent extends AbstractComponent {
    protected CarAxis axle;
    protected CarSides side;

    public WheelMountedComponent(String name, float weight, float maxDurability, float currentDurability,
                                 List<Effect> effects, CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, wearFactor);
        if (axle == null || side == null) {
            throw new IllegalArgumentException("Axle or Side cannot be null");
        }
        this.axle = axle;
        this.side = side;
    }

    public void degrade(float delta, float percentage) {
        currentDurability *= (1 - wearFactor * delta * percentage);
    }

    public CarAxis getAxle() {
        return axle;
    }

    public CarSides getSide() {
        return side;
    }
}
