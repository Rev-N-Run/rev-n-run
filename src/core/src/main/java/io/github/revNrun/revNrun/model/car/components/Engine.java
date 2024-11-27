package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Engine extends AbstractComponent {
    public Engine(String name, float weight, float maxDurability, float currentDurability, List<Effect> effects,
                  float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, wearFactor);
    }
}
