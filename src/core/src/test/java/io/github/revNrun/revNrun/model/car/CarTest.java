package io.github.revNrun.revNrun.model.car;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.revNrun.revNrun.model.car.components.*;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    Engine engine;
    Chasis chasis;
    Tires[] tires;
    Suspension[] suspensions;
    Brakes[] brakes;
    Floor floor;
    Front front;
    Back back;
    Sides sides;
    int fuel;
    Car car;

    @BeforeEach
    public  void setUp() {
        engine = new Engine("engine1", 200f, 100, 100, new ArrayList<>());
        chasis = new Chasis("chasis1", 500f, 100, 100, new ArrayList<>());
        tires = getTires();
        suspensions = getSuspensions();
        brakes = getBrakes();
        floor = new Floor("floor", 20f, 100, 100, new ArrayList<>());
        front = new Front("front", 20f, 100, 100, new ArrayList<>());
        back = new Back("back", 20f, 100, 100, new ArrayList<>());
        sides = new Sides("sides", 20f, 100, 100, new ArrayList<>());
        fuel = 100;
        car = new Car(engine, chasis, tires, suspensions, brakes, floor, front, back, sides, 100);
    }

    @Test
    public void testCarConstructor() {
        // Verify all components are correctly initialized
        assertEquals(engine, car.getEngine());
        assertEquals(chasis, car.getChasis());
        assertArrayEquals(tires, car.getTires());
        assertArrayEquals(suspensions, car.getSuspension());
        assertArrayEquals(brakes, car.getBrakes());
        assertEquals(floor, car.getFloor());
        assertEquals(front, car.getFront());
        assertEquals(back, car.getBack());
        assertEquals(sides, car.getSides());
        assertEquals(100, car.getFuelLevel());
    }

    @Test
    public void testInitialCarState() {
        // Verify initial state of the car
        assertEquals(0, car.getPositionX());
        assertEquals(0, car.getPositionY());
        assertEquals(0, car.getSpeed());
        assertEquals(100, car.getMaxSpeed());
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfTires() {
        Tires[] invalidTires = new Tires[3]; // Less than required
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, invalidTires, suspensions, brakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfSuspensions() {
        Suspension[] invalidSuspensions = new Suspension[3]; // Less than required
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, floor, front, back, sides, -1);
        });
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfBrakes() {
        Brakes[] invalidBrakes = new Brakes[3]; // Less than required
        
    }

    @Test
    public void testCarConstructorWithInvalidFuel() {
        try {
            new Car(engine, chasis, tires, suspensions, brakes, floor, front, back, sides, -1);
            fail("Should throw IllegalArgumentException");
        } catch (IllegalArgumentException e) {
            assertEquals("Fuel must be between 0 and 100", e.getMessage());
        }
    }

    private static Brakes[] getBrakes() {
        Brakes brakeFL = new Brakes("brakeFL", 20f, 100, 100, new ArrayList<>());
        Brakes brakeFR = new Brakes("brakeFR", 20f, 100, 100, new ArrayList<>());
        Brakes brakeRL = new Brakes("brakeRL", 20f, 100, 100, new ArrayList<>());
        Brakes brakeRR = new Brakes("brakeRR", 20f, 100, 100, new ArrayList<>());
        return new Brakes[]{brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private static Tires[] getTires() {
        Tires tireFL = new Tires("tireFL", 20f, 100, 100, new ArrayList<>());
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, new ArrayList<>());
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, new ArrayList<>());
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, new ArrayList<>());
        return new Tires[]{tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensions() {
        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100, 100, new ArrayList<>());
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100, 100, new ArrayList<>());
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100, 100, new ArrayList<>());
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100, 100, new ArrayList<>());
        return new Suspension[]{suspensionFL, suspensionFR, suspensionRL, suspensionRR};
    }
}
