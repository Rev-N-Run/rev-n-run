package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.EffectType;

public class Effect {
    private static final float MAX_VALUE = 20f;
    private static final float MIN_VALUE = -20f;

    private final EffectType effect;
    private final float value;

    public Effect(EffectType effect, float value) {
        this.effect = effect;
        if (value < MIN_VALUE) {
            this.value = MIN_VALUE;
        } else this.value = Math.min(value, MAX_VALUE);
    }

    public EffectType getEffect() {
        return effect;
    }

    public float getValue() {
        return value;
    }
}
