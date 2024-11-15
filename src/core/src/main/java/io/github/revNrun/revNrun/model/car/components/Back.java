package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Back extends AbstractComponent {
    public Back(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, wearFactor);
    }

    @Override
    public void degrade(float value) {

    }

    @Override
    public void degrade(float value, float delta) {

    }
}
