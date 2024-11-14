package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Tires extends AbstractComponent {
    private CarAxis axle;
    private CarSides side;

    public Tires(String name, float weight, int maxDurability, int currentDurability,
                 List<Effect> effects, CarAxis axle, CarSides side) {
        super(name, weight, maxDurability, currentDurability, effects);
        this.axle = axle;
        this.side = side;
    }

    @Override
    public void degrade(float percentage) {

    }

    public CarAxis getAxle() {
        return axle;
    }

    public CarSides getSide() {
        return side;
    }
}
