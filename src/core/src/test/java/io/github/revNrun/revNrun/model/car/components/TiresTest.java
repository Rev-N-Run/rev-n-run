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
    private static final float TEST_WEAR_FACTOR = 0.1f;

    @BeforeEach
    void setUp() {
        List<Effect> effects = new ArrayList<>();
        tires = new Tires(TIRE_NAME, TIRE_WEIGHT, MAX_DURABILITY, MAX_DURABILITY,
            effects, CarAxis.FRONT, CarSides.LEFT, TEST_WEAR_FACTOR);
    }
}
