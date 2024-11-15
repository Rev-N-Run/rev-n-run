package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.mocks.MockBrakes;
import io.github.revNrun.revNrun.model.car.components.mocks.MockSuspension;
import io.github.revNrun.revNrun.model.car.components.mocks.MockTires;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.revNrun.revNrun.model.car.components.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

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

    private static Brakes[] getBrakes() {
        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);
        return new Brakes[]{brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private static Tires[] getTires() {
        Tires tireFL = new Tires("tireFL", 20f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);
        return new Tires[]{tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensions() {
        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);
        return new Suspension[]{suspensionFL, suspensionFR, suspensionRL, suspensionRR};
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
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, invalidSuspensions, brakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfBrakes() {
        Brakes[] invalidBrakes = new Brakes[3]; // Less than required
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, invalidBrakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidFuel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, floor, front, back, sides, -1);
        });
    }

    @Test
    public void testComponentsNotNullObjects() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, null, null, null, null, null, null, null, null, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, chasis, tires, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, null, tires, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, null, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, null, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, null, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, null, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, floor, null, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, floor, front, null, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, brakes, floor, front, back, null, 100);
        });

        Tires[] invalidTires = new Tires[]{null, null, null, null};
        Suspension[] invalidSuspension = new Suspension[]{null, null, null, null};
        Brakes[] invalidBrakes = new Brakes[]{null, null, null, null};

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, invalidTires, suspensions, brakes, floor, front, back, null, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, invalidSuspension, brakes, floor, front, back, null, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chasis, tires, suspensions, invalidBrakes, floor, front, back, null, 100);
        });
    }

    @Test
    public void testTireDegradation() {
        Tires[] mockTires = getMockTires();
        car = new Car(engine, chasis, mockTires, suspensions, brakes, floor, front, back, sides, 100);

        // Simulate turning to the right, so the exterior (right) should degrade more than the interior (left).
        // In this case, lets test if the logic of selecting the tires makes the degradation.
        // 70% degradation in the right and 30% in the left.
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, .3f);
        sides.put(CarSides.RIGHT, .7f);
        car.degradeTires(sides);

        Tires[] tires = car.getTires();

        Tires tireFL = null, tireFR = null, tireRL = null, tireRR = null;

        for (Tires tire : tires) {
            CarAxis axle = tire.getAxle();
            CarSides side = tire.getSide();
            switch(axle) {
                case FRONT:
                    switch (side) {
                        case LEFT:
                            tireFL = tire;
                            break;
                        case RIGHT:
                            tireFR = tire;
                            break;
                    }
                    break;
                case REAR:
                    switch (side) {
                        case LEFT:
                            tireRL = tire;
                            break;
                        case RIGHT:
                            tireRR = tire;
                            break;
                    }
                    break;
            }
        }

        assertEquals(.7f, tireFL.getCurrentDurability());
        assertEquals(.7f, tireRL.getCurrentDurability());
        assertEquals(.3f, tireFR.getCurrentDurability());
        assertEquals(.3f, tireRR.getCurrentDurability());

        MockTires mockTireFL = (MockTires) tireFL;
        MockTires mockTireFR = (MockTires) tireFR;
        MockTires mockTireRL = (MockTires) tireRL;
        MockTires mockTireRR = (MockTires) tireRR;

        assertEquals(sides.get(CarSides.LEFT), mockTireFL.getLastDegradation());
        assertEquals(sides.get(CarSides.LEFT), mockTireRL.getLastDegradation());
        assertEquals(sides.get(CarSides.RIGHT), mockTireFR.getLastDegradation());
        assertEquals(sides.get(CarSides.RIGHT), mockTireRR.getLastDegradation());
    }

    private static Tires[] getMockTires() {
        Tires mockTireFL = new MockTires("mockTireFL", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Tires mockTireFR = new MockTires("mockTireFR", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Tires mockTireRL = new MockTires("mockTireRL", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Tires mockTireRR = new MockTires("mockTireRR", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);

        return new Tires[]{mockTireFL, mockTireFR, mockTireRL, mockTireRR};
    }

    @Test
    public void testSuspensionDegradation() {
        Suspension[] mockSuspension = getMockSuspension();
        car = new Car(engine, chasis, tires, mockSuspension, brakes, floor, front, back, sides, 100);

        // Simulate turning to the right, so the exterior (right) should degrade more than the interior (left).
        // In this case, lets test if the logic of selecting the tires makes the degradation.
        // 70% degradation in the right and 30% in the left.
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, .3f);
        sides.put(CarSides.RIGHT, .7f);
        car.degradeSuspension(sides);

        Suspension[] suspension = car.getSuspension();

        Suspension suspensionFL = null, suspensionFR = null, suspensionRL = null, suspensionRR = null;

        for (Suspension tire : suspension) {
            CarAxis axle = tire.getAxle();
            CarSides side = tire.getSide();
            switch(axle) {
                case FRONT:
                    switch (side) {
                        case LEFT:
                            suspensionFL = tire;
                            break;
                        case RIGHT:
                            suspensionFR = tire;
                            break;
                    }
                    break;
                case REAR:
                    switch (side) {
                        case LEFT:
                            suspensionRL = tire;
                            break;
                        case RIGHT:
                            suspensionRR = tire;
                            break;
                    }
                    break;
            }
        }

        assertEquals(.7f, suspensionFL.getCurrentDurability());
        assertEquals(.7f, suspensionRL.getCurrentDurability());
        assertEquals(.3f, suspensionFR.getCurrentDurability());
        assertEquals(.3f, suspensionRR.getCurrentDurability());

        MockSuspension mockSuspensionFL = (MockSuspension) suspensionFL;
        MockSuspension mockSuspensionFR = (MockSuspension) suspensionFR;
        MockSuspension mockSuspensionRL = (MockSuspension) suspensionRL;
        MockSuspension mockSuspensionRR = (MockSuspension) suspensionRR;

        assertEquals(sides.get(CarSides.LEFT), mockSuspensionFL.getLastDegradation());
        assertEquals(sides.get(CarSides.LEFT), mockSuspensionRL.getLastDegradation());
        assertEquals(sides.get(CarSides.RIGHT), mockSuspensionFR.getLastDegradation());
        assertEquals(sides.get(CarSides.RIGHT), mockSuspensionRR.getLastDegradation());
    }

    private static Suspension[] getMockSuspension() {
        Suspension mockSuspensionFL = new MockSuspension("mockSuspensionFL", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Suspension mockSuspensionFR = new MockSuspension("mockSuspensionFR", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Suspension mockSuspensionRL = new MockSuspension("mockSuspensionRL", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Suspension mockSuspensionRR = new MockSuspension("mockSuspensionRR", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);

        return new Suspension[]{mockSuspensionFL, mockSuspensionFR, mockSuspensionRL, mockSuspensionRR};
    }

    @Test
    public void testBrakesDegradation() {
        Brakes[] mockBrakes = getMockBrakes();
        car = new Car(engine, chasis, tires, suspensions, mockBrakes, floor, front, back, sides, 100);

        car.degradeBrakes();
    }

    private static Brakes[] getMockBrakes() {
        Brakes mockBrakeFL = new MockBrakes("mockBrakeFL", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT);
        Brakes mockBrakeFR = new MockBrakes("mockBrakeFR", 18f, 100,1, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT);
        Brakes mockBrakeRL = new MockBrakes("mockBrakeRL", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT);
        Brakes mockBrakeRR = new MockBrakes("mockBrakeRR", 18f, 100,1, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT);

        return new Brakes[]{mockBrakeFL, mockBrakeFR, mockBrakeRL, mockBrakeRR};
    }

}
