package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Engine extends Component {
    public Engine(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects) {
        super(name, weight, maxDurability, currentDurability, effects);
    }
}
