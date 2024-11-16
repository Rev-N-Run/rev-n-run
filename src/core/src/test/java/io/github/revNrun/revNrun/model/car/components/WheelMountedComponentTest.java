package io.github.revNrun.revNrun.model.car.components;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class WheelMountedComponentTest {
    private WheelMountedComponent wheelMountedComponent;
    private List<Effect> testEffects;
    private static final String TEST_NAME = "TestComponent";
    private static final float TEST_WEIGHT = 10.5f;
    private static final int TEST_MAX_DURABILITY = 100;
    private static final int TEST_CURRENT_DURABILITY = 90;
    private final CarAxis TEST_AXLE = CarAxis.FRONT;
    private final CarSides TEST_SIDE = CarSides.LEFT;
    private final float TEST_WEAR_FACTOR = .1f;

    @BeforeEach
    void setUp() {
        testEffects = new ArrayList<>();
        wheelMountedComponent = new WheelMountedComponent(TEST_NAME, TEST_WEIGHT, TEST_MAX_DURABILITY,
            TEST_CURRENT_DURABILITY, testEffects, TEST_AXLE, TEST_SIDE, TEST_WEAR_FACTOR) {};
    }

    @Test
    void nullParametersConstructor() {
        assertThrows(IllegalArgumentException.class, () -> {
            new WheelMountedComponent(TEST_NAME, TEST_WEIGHT, TEST_MAX_DURABILITY,
            TEST_CURRENT_DURABILITY, testEffects, null, null, TEST_WEAR_FACTOR) {};
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new WheelMountedComponent(TEST_NAME, TEST_WEIGHT, TEST_MAX_DURABILITY,
                TEST_CURRENT_DURABILITY, testEffects, null, TEST_SIDE, TEST_WEAR_FACTOR) {};
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new WheelMountedComponent(TEST_NAME, TEST_WEIGHT, TEST_MAX_DURABILITY,
                TEST_CURRENT_DURABILITY, testEffects, TEST_AXLE, null, TEST_WEAR_FACTOR) {};
        });
    }

    @Test
    void getAxle() {
        CarAxis result = wheelMountedComponent.getAxle();
        assertEquals(TEST_AXLE, result);
    }

    @Test
    void getSide() {
        CarSides result = wheelMountedComponent.getSide();
        assertEquals(TEST_SIDE, result);
    }
}
