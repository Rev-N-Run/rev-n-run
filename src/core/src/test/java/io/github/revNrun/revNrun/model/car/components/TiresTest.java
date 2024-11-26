package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TiresTest {

    @Test
    void degrade() {
        float value = 20;
        Effect effect = new Effect(EffectType.GRIP, value);
        List<Effect> effects = new ArrayList<>();
        effects.add(effect);
        Tires tire = new Tires("", 0, 100, 100, effects, CarAxis.FRONT, CarSides.LEFT, 0.2f);
        tire.degrade(0.1f, 0.7f);

        assertTrue(tire.getCurrentDurability() < tire.getMaxDurability());

        List<Effect> tireEffects = tire.getEffects();
        assertTrue(tireEffects.get(0).getValue() < value);
    }
}
