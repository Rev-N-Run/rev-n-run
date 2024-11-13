package io.github.revNrun.revNrun.model.car;

import org.junit.jupiter.api.Test;

import io.github.revNrun.revNrun.model.car.components.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    @Test
    public void testNewCarDefaultProperties() {
        Car car = new Car();

        assertEquals(0, car.getSpeed());
        assertEquals(100, car.getMaxSpeed());
        assertEquals(100, car.getFuelLevel());
    }

    @Test
    public void testNewCarHasRequiredComponents() {
        Car car = new Car();

        assertNotNull(car.getEngine());
        assertNotNull(car.getChasis());
        assertNotNull(car.getTires());
        assertNotNull(car.getSuspension());
        assertNotNull(car.getBrakes());
        assertNotNull(car.getFloor());
        assertNotNull(car.getFront());
        assertNotNull(car.getBack());
        assertNotNull(car.getSides());

        assertInstanceOf(Engine.class, car.getEngine());
        assertInstanceOf(Chasis.class, car.getChasis());
        assertInstanceOf(Tires.class, car.getTires());
        assertInstanceOf(Suspension.class, car.getSuspension());
        assertInstanceOf(Brakes.class, car.getBrakes());
        assertInstanceOf(Floor.class, car.getFloor());
        assertInstanceOf(Front.class, car.getFront());
        assertInstanceOf(Back.class, car.getBack());
        assertInstanceOf(Sides.class, car.getSides());
    }
}
