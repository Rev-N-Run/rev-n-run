package io.github.revNrun.revNrun.model.car.components;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractComponentTest {

    @Test
    public void testCreateComponent() {
        String name = "test";
        float weight = 18.5f;
        int maxDurability = 100;
        List<Effect> effects = new ArrayList<>();
        float wearFactor = 0.1f;

        Component component = new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, wearFactor) {};

        assertNotNull(component);
        assertEquals(name, component.getName());
        assertEquals(weight, component.getWeight(), 0.001f);
        assertEquals(maxDurability, component.getMaxDurability());
        assertEquals(maxDurability, component.getCurrentDurability());
        assertEquals(effects, component.getEffects());
        assertDoesNotThrow(() -> new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, wearFactor) {});

        float badWearFactor1 = 1.2f;
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, badWearFactor1) {});

        float badWearFactor2 = 1f;
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, badWearFactor2) {});

        float badWearFactor3 = -1f;
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, badWearFactor3) {});
    }

    @Test
    public void checkNotNullObjects() {
        Component component = new AbstractComponent(null, 5, 10, 23, null, .1f) {};
        assertNotNull(component.getName());
        assertNotNull(component.getEffects());

    }


}
