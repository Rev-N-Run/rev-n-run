package io.github.revNrun.revNrun.model.car.components;

public class Effect {
    private EffectType effect;
    private float value;

    public Effect(EffectType effect, float value) {
        this.effect = effect;
        this.value = value;
    }

    public EffectType getEffect() {
        return effect;
    }

    public float getValue() {
        return value;
    }
}
