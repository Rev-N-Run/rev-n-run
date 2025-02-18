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

    public AbstractComponent(String name, float weight, float maxDurability,
                                float currentDurability, List<Effect> effects, float wearFactor) {
        if (currentDurability <= 0 || maxDurability <= 0) {
            throw new IllegalArgumentException("Durability must be greater than 0");
        }

        this.name = (name == null) ? "" : name;
        this.weight = weight;
        this.maxDurability = maxDurability;
        this.currentDurability = Math.min(currentDurability, maxDurability);
        this.effects = (effects == null) ? new ArrayList<>() : effects;
        if (wearFactor >= 1 || wearFactor <= 0) {
            throw new IllegalArgumentException("Wear factor cannot be greater or equal than 1");
        }
        this.wearFactor = wearFactor;
    }

    @Override
    public void degradeByImpact(float percentage) {
        currentDurability *= percentage;
    }

    @Override
    public void degrade(float delta) {
        currentDurability *= (1 - wearFactor * delta);
    }

    @Override
    public void repair(float value) {
        if (value <= 0) {
            throw new IllegalArgumentException("Value cannot be less than 0");
        }

        currentDurability = Math.min(currentDurability + value, maxDurability);
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

    public float getWearFactor() {
        return wearFactor;
    }
}
