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
        this.name = name;
        this.weight = weight;
        this.maxDurability = maxDurability;
        this.currentDurability = currentDurability;
        this.effects = effects;
    }

    public String getName() {
        return name;
    }

    public float getWeight() {
        return weight;
    }

    public float getMaxDurability() {
        return maxDurability;
    }

    public float getCurrentDurability() {
        return currentDurability;
    }

    public List<Effect> getEffects() {
        return effects;
    }
}
