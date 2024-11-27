package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Front extends AbstractComponent {
    public Front(String name, float weight, float maxDurability, float currentDurability, List<Effect> effects,
                    float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, wearFactor);
    }
}
