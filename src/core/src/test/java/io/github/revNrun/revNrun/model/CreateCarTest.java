package io.github.revNrun.revNrun.model;

import io.github.revNrun.revNrun.model.car.Car;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CreateCarTest {

    @Test
    void createCar() {
        Car car = CreateCar.createCar();
        assertEquals(62, car.getTotalGrip());
    }
}
