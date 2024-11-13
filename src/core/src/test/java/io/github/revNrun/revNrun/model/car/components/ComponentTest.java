package io.github.revNrun.revNrun.model.car.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ComponentTest {

    @Test
    public void testCreateComponent() {
        String name = "test";
        float weight = 18.5f;
        int maxDurability = 100;
        int currentDurability = maxDurability;
        List<Effect> effects = new ArrayList<>();

        Component component = new Component(name, weight, maxDurability,
            currentDurability, effects) {};

        assertEquals(name, component.getName());
        assertEquals(weight, component.getWeight(), 0.001f);
        assertEquals(maxDurability, component.getMaxDurability());
        assertEquals(currentDurability, component.getCurrentDurability());
        assertEquals(effects, component.getEffects());
    }


}
