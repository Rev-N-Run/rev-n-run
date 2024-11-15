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

        Component component = new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects) {
            @Override
            public void degrade(float value) {

            }

            @Override
            public void degrade(float value, float delta) {

            }
        };

        assertNotNull(component);
        assertEquals(name, component.getName());
        assertEquals(weight, component.getWeight(), 0.001f);
        assertEquals(maxDurability, component.getMaxDurability());
        assertEquals(maxDurability, component.getCurrentDurability());
        assertEquals(effects, component.getEffects());
    }

    @Test
    public void checkNotNullObjects() {
        Component component = new AbstractComponent(null, 5, 10, 23, null) {
            @Override
            public void degrade(float value) {

            }

            @Override
            public void degrade(float value, float delta) {

            }
        };
        assertNotNull(component.getName());
        assertNotNull(component.getEffects());

    }


}
