package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class TiresTest {
    private Tires tires;
    private static final String TIRE_NAME = "Test Tire";
    private static final float TIRE_WEIGHT = 10.0f;
    private static final int MAX_DURABILITY = 100;

    @BeforeEach
    void setUp() {
        List<Effect> effects = new ArrayList<>();
        tires = new Tires(TIRE_NAME, TIRE_WEIGHT, MAX_DURABILITY, MAX_DURABILITY,
            effects, CarAxis.FRONT, CarSides.LEFT);
    }

    @Test
    void degrade() {
        float degradePercentage = .2f; // 20% degradation
        tires.degrade(degradePercentage);
        assertEquals(80, tires.getCurrentDurability());

        degradePercentage = .9f; // 100% degradation
        tires.degrade(degradePercentage);
        assertEquals(8, tires.getCurrentDurability(), 0.001f);
    }

    @Test
    void degradeInvalidPercentage() {
        final float degradePercentage1 = -1f;
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage1));
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());

        final float degradePercentage2 = -.3f;
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage2));
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());

        final float degradePercentage3 = 1f;
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage3));
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());

        final float degradePercentage4 = -1.4f;
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage4));
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
    }

    @Test
    void limitValuesDegrade() {
        // Limit values (frontier and external frontiers)
        final float[] degradePercentage = new float[]{1, 0, 1.01f, -0.1f, -2f, 2f};
        for (float percentage : degradePercentage) {
            assertThrows(IllegalArgumentException.class, () -> tires.degrade(percentage));
            assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
        }

        // Internal frontier
        final float degradePercentage1 = .01f;
        final float degradePercentage2 = .99f;

        assertDoesNotThrow(() -> tires.degrade(degradePercentage1));
        assertEquals(99, tires.getCurrentDurability());

        assertDoesNotThrow(() -> tires.degrade(degradePercentage2));
        assertEquals(0.99f, tires.getCurrentDurability(), 0.001f);

        // Normal values tested in previous methods.
    }
}
