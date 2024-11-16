package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Sides extends AbstractComponent {
    public Sides(String name, float weight, float maxDurability, float currentDurability, List<Effect> effects, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, wearFactor);
    }
}
