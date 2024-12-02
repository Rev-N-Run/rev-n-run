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
    private final float delta = 0.001f;
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

        float[] badWearFactor = new float[] {1.2f, 1f, -1f, 0f};
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

        assertDoesNotThrow(() -> component.degradeByImpact(collisionDamage));
        assertEquals(component.getMaxDurability() / 2, component.getCurrentDurability());
    }

    @Test
    public void checkDegrade() {
        float expected = maxDurability * (1 - wearFactor * delta);
        component.degrade(delta);
        assertEquals(expected, component.getCurrentDurability());
    }

    @Test
    public void repair() {
        Component component = new AbstractComponent(name, weight, maxDurability,
            1, effects, wearFactor) {};
        float value = 50;
        float prevValue = component.getCurrentDurability();
        component.repair(value);
        assertEquals(value + prevValue, component.getCurrentDurability());
    }

    @Test
    public void invalidRepairValues() {
        Component component = new AbstractComponent(name, weight, maxDurability,
            1, effects, wearFactor) {};
        float value = maxDurability * 2;
        component.repair(value);
        assertEquals(maxDurability, component.getCurrentDurability());

        // Limit values

        // External frontier values
        final float[] negativeValues = new float[] {-0.1f, -1f};
        final float[] positiveValues = new float[] {component.getMaxDurability() + 0.1f,
            component.getMaxDurability() + 20};
        float prev = component.getCurrentDurability();

        for (float value1 : negativeValues) {
            assertThrows(IllegalArgumentException.class, () -> component.repair(value1));
            assertEquals(prev, component.getCurrentDurability());
        }

        for (float value1 : positiveValues) {
            assertEquals(maxDurability, component.getCurrentDurability());
        }

        // Internal and frontier values
        final float[] validValues = new float[] {1, 1.1f, 5, 43, component.getMaxDurability() - 0.1f,
            component.getMaxDurability()};
        for (float value1 : validValues) {
            Component component1 = new AbstractComponent(name, weight, maxDurability,
                1, effects, wearFactor) {};
            float expected = value1 + component1.getCurrentDurability();
            if (expected > maxDurability) {
                expected = maxDurability;
            }
            component1.repair(value1);
            assertEquals(expected, component1.getCurrentDurability());
        }
    }

    @Test
    public void exceedsMaxDurability() {
        Component component = new AbstractComponent(name, weight, maxDurability,
            maxDurability - 1, effects, wearFactor) { };
        float value = maxDurability - 1;
        component.repair(value);
        assertEquals(maxDurability, component.getCurrentDurability());
    }

    @Test
    public void testIfNegativeValuesAreAcceptedCurrentAndMaxDurability() {
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
            -20, effects, wearFactor) {});
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, -20,
            maxDurability, effects, wearFactor) {});
        assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, -20,
            -20, effects, wearFactor) { });

        // Limit test

        // External frontier values
        final float[] invalidValues = new float[] {-2, -0.1f};
        for (float value : invalidValues) {
            assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, value,
                maxDurability, effects, wearFactor) {});
        }

        for (float value : invalidValues) {
            assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, maxDurability,
                value, effects, wearFactor) {});
        }

        for (float value : invalidValues) {
            assertThrows(IllegalArgumentException.class, () -> new AbstractComponent(name, weight, value,
                value, effects, wearFactor) {});
        }

        // Internal and frontier values
        final float[] validValues = new float[] {0.1f, 1, 2};
        for (float value : validValues) {
            Component component = new AbstractComponent(name, weight, maxDurability,
                value, effects, wearFactor) {};
            assertEquals(value, component.getCurrentDurability());
            assertEquals(maxDurability, component.getMaxDurability());
        }

        for (float value : validValues) {
            Component component = new AbstractComponent(name, weight, value,
                value - 0.01f, effects, wearFactor) {};
            assertEquals(value - 0.01f, component.getCurrentDurability());
            assertEquals(value, component.getMaxDurability());
        }

        for (float value : validValues) {
            Component component = new AbstractComponent(name, weight, value,
                value, effects, wearFactor) {};
            assertEquals(value, component.getCurrentDurability());
            assertEquals(value, component.getMaxDurability());
        }
    }

    @Test
    public void testIfCurrentDurabilityCanBeGreaterThanMaxDurability() {
        Component component = new AbstractComponent(name, weight, maxDurability,
            maxDurability + 20, effects, wearFactor) {};
        assertEquals(maxDurability, component.getCurrentDurability());

        // Limit testing

        // External frontier values
        component = new AbstractComponent(name, weight, maxDurability,
            maxDurability + 0.1f, effects, wearFactor) {};
        assertEquals(maxDurability, component.getCurrentDurability());

        // Internal and frontier values
        component = new AbstractComponent(name, weight, maxDurability,
            maxDurability, effects, wearFactor) {};
        assertEquals(maxDurability, component.getCurrentDurability());

        component = new AbstractComponent(name, weight, maxDurability,
            maxDurability - 0.1f, effects, wearFactor) {};
        assertEquals(maxDurability - 0.1f, component.getCurrentDurability());

        component = new AbstractComponent(name, weight, maxDurability,
            maxDurability - 5f, effects, wearFactor) {};
        assertEquals(maxDurability - 5f, component.getCurrentDurability());
    }

    @Test
    public void statementCoverageRepair() {
        assertThrows(IllegalArgumentException.class, () -> component.repair(-1));
        Component component1 = new AbstractComponent(name, weight, maxDurability,
            maxDurability - 2, effects, wearFactor) {};
        component1.repair(1);
        assertEquals(maxDurability - 1, component1.getCurrentDurability());

    }
}
