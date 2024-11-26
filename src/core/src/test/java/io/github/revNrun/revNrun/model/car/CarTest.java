package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;
import io.github.revNrun.revNrun.model.car.components.mocks.MockBrakes;
import io.github.revNrun.revNrun.model.car.components.mocks.MockSuspension;
import io.github.revNrun.revNrun.model.car.components.mocks.MockTires;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import io.github.revNrun.revNrun.model.car.components.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

public class CarTest {
    Engine engine;
    Chassis chassis;
    Tires[] tires;
    Suspension[] suspensions;
    Brakes[] brakes;
    Floor floor;
    Front front;
    Back back;
    Sides sides;
    int fuel;
    Car car;
    float delta = 0.001f;
    List<Effect> engineEffects;
    List<Effect> chassisEffects;
    List<Effect> floorEffects;
    List<Effect> frontEffects;
    List<Effect> backEffects;
    List<Effect> sidesEffects;
    List<Effect> tiresEffects;
    List<Effect> suspensionEffects;
    List<Effect> brakesEffects;
    Car carWithEffects;
    Engine engineWithEffects;
    Chassis chassisWithEffects;
    Tires[] tiresWithEffects;
    Suspension[] suspensionsWithEffects;
    Brakes[] brakesWithEffects;
    Floor floorWithEffects;
    Front frontWithEffects;
    Back backWithEffects;
    Sides sidesWithEffects;

    @BeforeEach
    public  void setUp() {
        engine = new Engine("engine1", 200f, 100, 100, new ArrayList<>(), .1f);
        chassis = new Chassis("chasis1", 500f, 100, 100, new ArrayList<>(), .1f);
        tires = getTires();
        suspensions = getSuspensions();
        brakes = getBrakes();
        floor = new Floor("floor", 20f, 100, 100, new ArrayList<>(), .1f);
        front = new Front("front", 20f, 100, 100, new ArrayList<>(), .1f);
        back = new Back("back", 20f, 100, 100, new ArrayList<>(), .1f);
        sides = new Sides("sides", 20f, 100, 100, new ArrayList<>(), .1f);
        fuel = 100;
        car = new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 100);

        Effect acceleration10 = new Effect(EffectType.ACCELERATION, 10);
        Effect maxSpeed15 = new Effect(EffectType.MAX_SPEED, 15);
        Effect grip4 = new Effect(EffectType.GRIP, 4);
        Effect brake1 = new Effect(EffectType.BRAKE, 1);
        Effect accelerationMinus1 = new Effect(EffectType.ACCELERATION, -1);
        Effect maxSpeedMinus1 = new Effect(EffectType.MAX_SPEED, -1);
        Effect grip2 = new Effect(EffectType.GRIP, 2);
        Effect accelerationMinus2 = new Effect(EffectType.ACCELERATION, -2);
        Effect maxSpeedMinus2 = new Effect(EffectType.MAX_SPEED, -2);
        Effect grip1 = new Effect(EffectType.GRIP, 1);


        engineEffects = new ArrayList<>(Arrays.asList(acceleration10, maxSpeed15));
        chassisEffects = new ArrayList<>(Collections.singletonList(grip1));
        floorEffects = new ArrayList<>(Arrays.asList(grip4, brake1, accelerationMinus1, maxSpeedMinus1));
        frontEffects = new ArrayList<>(Arrays.asList(grip2, brake1, accelerationMinus2, maxSpeedMinus2));
        backEffects = new ArrayList<>(Collections.singletonList(grip1));
        sidesEffects = new ArrayList<>(Collections.singletonList(grip2));

        engineWithEffects = new Engine("engine1", 200f, 100, 100, engineEffects, .1f);
        chassisWithEffects = new Chassis("chasis1", 500f, 100, 100, chassisEffects, .1f);
        tiresWithEffects = getTiresWithEffects();
        suspensionsWithEffects = getSuspensionsWithEffects();
        brakesWithEffects = getBrakesWithEffects();
        floorWithEffects = new Floor("floor", 20f, 100, 100, floorEffects, .1f);
        frontWithEffects = new Front("front", 20f, 100, 100, frontEffects, .1f);
        backWithEffects = new Back("back", 20f, 100, 100, backEffects, .1f);
        sidesWithEffects = new Sides("sides", 20f, 100, 100, sidesEffects, .1f);
        fuel = 100;

        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);
    }

    private static Brakes[] getBrakes() {
        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Brakes[]{brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private Brakes[] getBrakesWithEffects() {
        Effect brake15 = new Effect(EffectType.BRAKE, 15);
        brakesEffects = new ArrayList<>(Collections.singletonList(brake15));

        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[]{brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private static Tires[] getTires() {
        Tires tireFL = new Tires("tireFL", 20f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Tires[]{tireFL, tireFR, tireRL, tireRR};
    }

    private Tires[] getTiresWithEffects() {
        Effect grip10 = new Effect(EffectType.GRIP, 10);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect maxSpeedMinus2 = new Effect(EffectType.MAX_SPEED, -2);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);

        tiresEffects = new ArrayList<>(Arrays.asList(grip10, brake3, maxSpeedMinus2, acceleration1));

        Tires tireFL = new Tires("tireFL", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Tires[]{tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensions() {
        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100, 100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100, 100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Suspension[]{suspensionFL, suspensionFR, suspensionRL, suspensionRR};
    }

    private Suspension[] getSuspensionsWithEffects() {
        Effect grip3 = new Effect(EffectType.GRIP, 3);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);
        Effect maxSpeed1 = new Effect(EffectType.MAX_SPEED, 1);

        suspensionEffects = new ArrayList<>(Arrays.asList(grip3, brake3, acceleration1, maxSpeed1));

        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100, 100, suspensionEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100, 100, suspensionEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100, 100, suspensionEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100, 100, suspensionEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Suspension[]{suspensionFL, suspensionFR, suspensionRL, suspensionRR};
    }

    @Test
    public void testCarConstructor() {
        // Verify all components are correctly initialized
        assertEquals(engine, car.getEngine());
        assertEquals(chassis, car.getChassis());
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
            new Car(engine, chassis, invalidTires, suspensions, brakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfSuspensions() {
        Suspension[] invalidSuspensions = new Suspension[3]; // Less than required
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, invalidSuspensions, brakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidNumberOfBrakes() {
        Brakes[] invalidBrakes = new Brakes[3]; // Less than required
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, invalidBrakes, floor, front, back, sides, 100);
        });
    }

    @Test
    public void testCarConstructorWithInvalidFuel() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, -1);
        });
    }

    @Test
    public void testComponentsNotNullObjects() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, null, null, null, null, null, null, null, null, -1);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(null, chassis, tires, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, null, tires, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, null, suspensions, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, null, brakes, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, null, floor, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, null, front, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, null, back, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, null, sides, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, null, 100);
        });

        Tires[] invalidTires = new Tires[]{null, null, null, null};
        Suspension[] invalidSuspension = new Suspension[]{null, null, null, null};
        Brakes[] invalidBrakes = new Brakes[]{null, null, null, null};

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, invalidTires, suspensions, brakes, floor, front, back, null, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, invalidSuspension, brakes, floor, front, back, null, 100);
        });

        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, invalidBrakes, floor, front, back, null, 100);
        });
    }

    private boolean isSpecifiedComponent(WheelMountedComponent component, CarAxis axle, CarSides side) {
        return component.getAxle() == axle && component.getSide() == side;
    }

    private WheelMountedComponent getComponentByPosition(WheelMountedComponent[] components, CarAxis axle, CarSides side) {
        for (WheelMountedComponent component : components) {
            if (isSpecifiedComponent(component, axle, side)) {
                return component;
            }
        }
        return null;
    }

    @Test
    public void testTireDegradation() {
        Tires[] mockTires = getMockTires();
        car = new Car(engine, chassis, mockTires, suspensions, brakes, floor, front, back, sides, 100);

        // Simulate turning to the right, so the exterior (right) should degrade more than the interior (left).
        // In this case, lets test if the logic of selecting the tires makes the degradation.
        // 70% degradation in the right and 30% in the left.
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, 30f);
        sides.put(CarSides.RIGHT, 70f);
        car.degradeTires(delta, sides);

        WheelMountedComponent tireFL, tireFR, tireRL, tireRR;

        tireFL = getComponentByPosition(car.getTires(), CarAxis.FRONT, CarSides.LEFT);
        tireFR = getComponentByPosition(car.getTires(), CarAxis.FRONT, CarSides.RIGHT);
        tireRL = getComponentByPosition(car.getTires(), CarAxis.REAR, CarSides.LEFT);
        tireRR = getComponentByPosition(car.getTires(), CarAxis.REAR, CarSides.RIGHT);

        assertEquals(70f, tireFL.getCurrentDurability());
        assertEquals(70f, tireRL.getCurrentDurability());
        assertEquals(30f, tireFR.getCurrentDurability());
        assertEquals(30f, tireRR.getCurrentDurability());

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
        Tires mockTireFL = new MockTires("mockTireFL", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires mockTireFR = new MockTires("mockTireFR", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires mockTireRL = new MockTires("mockTireRL", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Tires mockTireRR = new MockTires("mockTireRR", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Tires[]{mockTireFL, mockTireFR, mockTireRL, mockTireRR};
    }

    @Test
    public void testSuspensionDegradation() {
        Suspension[] mockSuspension = getMockSuspension();
        car = new Car(engine, chassis, tires, mockSuspension, brakes, floor, front, back, sides, 100);

        // Simulate turning to the right, so the exterior (right) should degrade more than the interior (left).
        // In this case, lets test if the logic of selecting the tires makes the degradation.
        // 70% degradation in the right and 30% in the left.
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, 30f);
        sides.put(CarSides.RIGHT, 70f);
        car.degradeSuspension(delta, sides);

        WheelMountedComponent suspensionFL, suspensionFR, suspensionRL, suspensionRR;

        suspensionFL = getComponentByPosition(car.getSuspension(), CarAxis.FRONT, CarSides.LEFT);
        suspensionFR = getComponentByPosition(car.getSuspension(), CarAxis.FRONT, CarSides.RIGHT);
        suspensionRL = getComponentByPosition(car.getSuspension(), CarAxis.REAR, CarSides.LEFT);
        suspensionRR = getComponentByPosition(car.getSuspension(), CarAxis.REAR, CarSides.RIGHT);

        assertEquals(70f, suspensionFL.getCurrentDurability());
        assertEquals(70f, suspensionRL.getCurrentDurability());
        assertEquals(30f, suspensionFR.getCurrentDurability());
        assertEquals(30f, suspensionRR.getCurrentDurability());

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
        Suspension mockSuspensionFL = new MockSuspension("mockSuspensionFL", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension mockSuspensionFR = new MockSuspension("mockSuspensionFR", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension mockSuspensionRL = new MockSuspension("mockSuspensionRL", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension mockSuspensionRR = new MockSuspension("mockSuspensionRR", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Suspension[]{mockSuspensionFL, mockSuspensionFR, mockSuspensionRL, mockSuspensionRR};
    }

    @Test
    public void testBrakesDegradation() {
        Brakes[] mockBrakes = getMockBrakes();
        car = new Car(engine, chassis, tires, suspensions, mockBrakes, floor, front, back, sides, 100);

        car.degradeBrakes(delta);

        WheelMountedComponent brakeFL, brakeFR, brakeRL, brakeRR;

        brakeFL = getComponentByPosition(car.getBrakes(), CarAxis.FRONT, CarSides.LEFT);
        brakeFR = getComponentByPosition(car.getBrakes(), CarAxis.FRONT, CarSides.RIGHT);
        brakeRL = getComponentByPosition(car.getBrakes(), CarAxis.REAR, CarSides.LEFT);
        brakeRR = getComponentByPosition(car.getBrakes(), CarAxis.REAR, CarSides.RIGHT);

        assertEquals(45f, brakeFL.getCurrentDurability());
        assertEquals(45f, brakeFR.getCurrentDurability());
        assertEquals(55f, brakeRL.getCurrentDurability());
        assertEquals(55f, brakeRR.getCurrentDurability());

        MockBrakes mockBrakesFL = (MockBrakes) brakeFL;
        MockBrakes mockBrakesFR = (MockBrakes) brakeFR;
        MockBrakes mockBrakesRL = (MockBrakes) brakeRL;
        MockBrakes mockBrakesRR = (MockBrakes) brakeRR;

        assertEquals(.45f, mockBrakesFL.getLastDegradation());
        assertEquals(.55f, mockBrakesRL.getLastDegradation());
        assertEquals(.45f, mockBrakesFR.getLastDegradation());
        assertEquals(.55f, mockBrakesRR.getLastDegradation());
    }

    private static Brakes[] getMockBrakes() {
        Brakes mockBrakeFL = new MockBrakes("mockBrakeFL", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes mockBrakeFR = new MockBrakes("mockBrakeFR", 18f, 100,100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes mockBrakeRL = new MockBrakes("mockBrakeRL", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes mockBrakeRR = new MockBrakes("mockBrakeRR", 18f, 100,100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[]{mockBrakeFL, mockBrakeFR, mockBrakeRL, mockBrakeRR};
    }

    @Test
    public void testCarAttributes() {
        float expectedSpeed = 108;
        float expectedGrip = 62;
        float expectedBrake = 86;
        float expectedAcceleration = 15;
        float expectedWeight = engineWithEffects.getWeight() + chassisWithEffects.getWeight() +
            tiresWithEffects[0].getWeight() * 4 + suspensionsWithEffects[0].getWeight() * 4 +
            brakesWithEffects[0].getWeight() * 4 + floorWithEffects.getWeight() + frontWithEffects.getWeight() +
            backWithEffects.getWeight() + sidesWithEffects.getWeight();

        assertEquals(expectedAcceleration, carWithEffects.getAcceleration());
        assertEquals(expectedSpeed, carWithEffects.getMaxSpeed());
        assertEquals(expectedGrip, carWithEffects.getGrip());
        assertEquals(expectedBrake, carWithEffects.getBrakePower());
        assertEquals(expectedWeight, carWithEffects.getWeight());
    }

    @Test
    public void accelerate() {
        float delta = 0.1f;
        float expected = 15 * 0.1f;
        carWithEffects.accelerate(delta);

        assertEquals(expected, carWithEffects.getSpeed());
    }

    @Test
    public void accelerateToMaximumAndKeep() {
        float delta = 0.1f;
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(delta);
        }

        float expected = carWithEffects.getMaxSpeed();

        assertEquals(expected, carWithEffects.getSpeed());

        for (int i = 0; i < 10000; i++) {
            carWithEffects.accelerate(delta);
            assertEquals(expected, carWithEffects.getSpeed());
        }
    }

    @Test
    public void brakeAndReverse() {
        float delta = 0.1f;
        for (int i = 0; i < 20; i++) {
            carWithEffects.accelerate(delta);
        }
        float expected = carWithEffects.getSpeed() - (carWithEffects.getReverseAcceleration()) * delta;

        carWithEffects.brakeAndReverse(delta);

        assertEquals(expected, carWithEffects.getSpeed());

        while (carWithEffects.getSpeed() > 0) {
            carWithEffects.brakeAndReverse(delta);
        }

        expected = carWithEffects.getSpeed() - (carWithEffects.getReverseAcceleration()) * delta;

        carWithEffects.brakeAndReverse(delta);

        assertEquals(expected, carWithEffects.getSpeed());
    }

    @Test
    public void reverseToMaximumAndKeep() {
        float delta = 0.1f;
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(delta);
        }

        float expected = carWithEffects.getMaxReverseSpeed();

        assertEquals(expected, carWithEffects.getSpeed());

        for (int i = 0; i < 10000; i++) {
            carWithEffects.brakeAndReverse(delta);
            assertEquals(expected, carWithEffects.getSpeed());
        }
    }

    @Test
    public void testNoMovementAtZeroSpeed() {
        int initialX = car.getPositionX();
        int initialY = car.getPositionY();

        car.updatePosition(1);

        assertEquals(initialX, car.getPositionX());
        assertEquals(initialY, car.getPositionY());
    }

    @Test
    public void testForwardMovement() {
        int initialX = carWithEffects.getPositionX();
        int initialY = carWithEffects.getPositionY();

        carWithEffects.accelerate(1);
        carWithEffects.updatePosition(1);

        assertTrue(carWithEffects.getPositionX() > initialX);
        assertEquals(initialY, carWithEffects.getPositionY());
    }

    @Test
    public void testBasicTurnRight() {
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.accelerate(1);
        float grip = carWithEffects.getGrip();

        carWithEffects.moveRight(1);

        assertTrue(carWithEffects.getAngle() > initialAngle);
        assertEquals(initialAngle + (grip * 0.01f), carWithEffects.getAngle(), 0.001f);
    }

    @Test
    public void testSpeedLimitInTurn() {
        float maxTurnSpeed = carWithEffects.getMaxSpeed() - 20;
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(0.1f);
            System.out.println(carWithEffects.getSpeed());
        }

        carWithEffects.moveRight(1);

        assertTrue(carWithEffects.getSpeed() <= maxTurnSpeed);
    }
}
