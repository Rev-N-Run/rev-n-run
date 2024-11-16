package io.github.revNrun.revNrun.model.car.components;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class AbstractComponentTest {
    private final String name = "test";
    private final float weight = 18.5f;
    private final int maxDurability = 100;
    private final List<Effect> effects = new ArrayList<>();
    private final float wearFactor = 0.1f;
    private Component component;

    @BeforeEach
    public void setUp() {
        component = new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, wearFactor) {};
    }

    @Test
    public void testCreateComponent() {
        assertNotNull(component);
        assertEquals(name, component.getName());
        assertEquals(weight, component.getWeight(), 0.001f);
        assertEquals(maxDurability, component.getMaxDurability());
        assertEquals(maxDurability, component.getCurrentDurability());
        assertEquals(effects, component.getEffects());
        assertDoesNotThrow(() -> new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, wearFactor) {});

        float[] badWearFactor = new float[]{1.2f, 1f, -1f, 0f};
        for (float factor : badWearFactor) {
            assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
                maxDurability, effects, factor) {});
        }
    }

    @Test
    public void checkNotNullObjects() {
        Component component = new AbstractComponent(null, 5, 10, 23, null, .1f) {};
        assertNotNull(component.getName());
        assertNotNull(component.getEffects());

    }

    @Test
    public void checkDegradeByCollision() {
        final float collisionDamage = .5f;

        assertDoesNotThrow(() -> component.degrade(collisionDamage));
        assertEquals(component.getMaxDurability() / 2, component.getCurrentDurability());
    }

    @Test
    public void invalidValuesDegrade() {
        // Check frontier and external frontier values
        final float[] collisionDamage = new float[]{1, 0, 1.01f, -0.1f, -1, 2};
        for (float damage : collisionDamage) {
            assertThrows(IllegalArgumentException.class, () -> component.degrade(damage));
            assertEquals(component.getMaxDurability(), component.getCurrentDurability());
        }

        // Check internal frontier values
        final float[] collisionDamage2 = new float[]{0.99f, 0.01f};
        float res;
        for (float damage : collisionDamage2) {
            res = (component.getCurrentDurability() * damage);
            assertDoesNotThrow(() -> component.degrade(damage));
            assertEquals(res, component.getCurrentDurability());
        }
    }
}
