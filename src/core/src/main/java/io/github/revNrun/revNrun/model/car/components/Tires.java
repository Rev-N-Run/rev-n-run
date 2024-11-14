package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Tires extends Component {
    private CarAxis axle;
    private CarSides side;

    public Tires(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects) {
        super(name, weight, maxDurability, currentDurability, effects);
    }

    @Override
    public void degrade(float percentage) {

    }

    public CarAxis getAxle() {
        return null;
    }

    public CarSides getSide() {
        return null;
    }
}
