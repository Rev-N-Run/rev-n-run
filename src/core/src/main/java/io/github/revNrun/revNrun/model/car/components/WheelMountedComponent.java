package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public abstract class WheelMountedComponent extends AbstractComponent {
    protected CarAxis axle;
    protected CarSides side;

    public WheelMountedComponent(String name, float weight, int maxDurability, int currentDurability,
                                 List<Effect> effects, CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects);
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
