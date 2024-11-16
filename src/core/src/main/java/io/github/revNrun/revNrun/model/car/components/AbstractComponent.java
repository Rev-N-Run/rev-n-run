package io.github.revNrun.revNrun.model.car.components;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractComponent implements Component {
    protected String name;
    protected float weight;
    protected float maxDurability;
    protected float currentDurability;
    protected List<Effect> effects;
    protected float wearFactor;

    public AbstractComponent(String name, float weight, int maxDurability,
                             int currentDurability, List<Effect> effects, float wearFactor) {
        this.name = (name == null) ? "" : name;
        this.weight = weight;
        this.maxDurability = maxDurability;
        this.currentDurability = currentDurability;
        this.effects = (effects == null) ? new ArrayList<>() : effects;
        if (wearFactor >= 1 || wearFactor <= 0) {
            throw new IllegalArgumentException("Wear factor cannot be greater or equal than 1");
        }
        this.wearFactor = wearFactor;
    }

    @Override
    public void degradeByImpact(float percentage) {
        if (percentage >= 1 || percentage <= 0) {
            throw new IllegalArgumentException("Not a valid value");
        }
        currentDurability *= percentage;
    }

    @Override
    public void degrade(float delta) {
        currentDurability *= (1 - wearFactor * delta);
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
