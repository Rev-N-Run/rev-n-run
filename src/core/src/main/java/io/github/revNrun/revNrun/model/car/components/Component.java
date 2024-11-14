package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public interface Component {
    String getName();
    float getWeight();
    float getMaxDurability();
    float getCurrentDurability();
    List<Effect> getEffects();
    void degrade(float value);
}
