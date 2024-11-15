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

        degradePercentage = 0f;
        tires.degrade(degradePercentage);
        assertEquals(80, tires.getCurrentDurability());

        degradePercentage = .9f; // 100% degradation
        tires.degrade(degradePercentage);
        assertEquals(8, tires.getCurrentDurability());
    }

    @Test
    void degradeInvalidPercentage() {
        final float degradePercentage1 = -1f;
        tires.degrade(degradePercentage1);
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage1));

        final float degradePercentage2 = -.3f;
        tires.degrade(degradePercentage2);
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage2));

        final float degradePercentage3 = 1f;
        tires.degrade(degradePercentage3);
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage3));

        final float degradePercentage4 = -1.4f;
        tires.degrade(degradePercentage4);
        assertEquals(MAX_DURABILITY, tires.getCurrentDurability());
        assertThrows(IllegalArgumentException.class, () -> tires.degrade(degradePercentage4));
    }
}
