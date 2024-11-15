package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.List;

public abstract class WheelMountedComponent extends AbstractComponent {
    protected CarAxis axle;
    protected CarSides side;

    public WheelMountedComponent(String name, float weight, int maxDurability, int currentDurability,
                                 List<Effect> effects, CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects);
        if (axle == null || side == null) {
            throw new IllegalArgumentException("Axle or Side cannot be null");
        }
        this.axle = axle;
        this.side = side;
    }

    public CarAxis getAxle() {
        return axle;
    }

    public CarSides getSide() {
        return side;
    }
}
