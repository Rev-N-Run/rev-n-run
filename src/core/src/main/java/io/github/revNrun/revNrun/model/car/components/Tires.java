package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Tires extends Component {
    public Tires(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects) {
        super(name, weight, maxDurability, currentDurability, effects);
    }
}
