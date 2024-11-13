package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Floor extends Component {
    public Floor(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects) {
        super(name, weight, maxDurability, currentDurability, effects);
    }
}
