package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public class Brakes extends AbstractComponent {
    public Brakes(String name, float weight, int maxDurability, int currentDurability, List<Effect> effects) {
        super(name, weight, maxDurability, currentDurability, effects);
    }

    @Override
    public void degrade(float percentage) {

    }
}
