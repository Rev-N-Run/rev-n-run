package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;

import java.util.List;

public class Tires extends WheelMountedComponent {
    public Tires(String name, float weight, float maxDurability, float currentDurability,
                 List<Effect> effects, CarAxis axle, CarSides side, float wearFactor) {
        super(name, weight, maxDurability, currentDurability, effects, axle, side, wearFactor);
        for (Effect effect : effects) {
            if (effect.getEffect() == EffectType.GRIP) {
                float value = (effect.getValue() < 0) ? 0 : effect.getValue();
                effect.setValue(value);
            }
        }
        this.effects = effects;
    }

    @Override
    public void degrade(float delta, float percentage) {
        currentDurability *= (1 - wearFactor * delta * percentage);
        for (Effect effect : effects) {
            if (effect.getEffect() == EffectType.GRIP) {
                float factor = Math.min(currentDurability / 100f, 1f);
                float grip = effect.getValue() * factor;
                effect.setValue(grip);
            }
        }
    }
}
