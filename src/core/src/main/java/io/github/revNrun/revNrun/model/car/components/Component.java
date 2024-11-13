package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public abstract class Component {
    protected String name;
    protected float weight;
    protected int maxDurability;
    protected int currentDurability;
    protected List<Effect> effects;

    public Component(String name, float weight, int maxDurability,
                     int currentDurability, List<Effect> effects) {

    }

    public String getName() {
        return null;
    }

    public float getWeight() {
        return 0;
    }

    public float getMaxDurability() {
        return 0;
    }

    public float getCurrentDurability() {
        return 0;
    }

    public List<Effect> getEffects() {
        return null;
    }
}
