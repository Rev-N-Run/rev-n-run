package io.github.revNrun.revNrun.model.car.components;

import java.util.List;

public interface Component {
    String getName();
    float getWeight();
    int getMaxDurability();
    int getCurrentDurability();
    List<Effect> getEffects();
    void degrade(float value);
    void degrade(float value, float delta);
}
