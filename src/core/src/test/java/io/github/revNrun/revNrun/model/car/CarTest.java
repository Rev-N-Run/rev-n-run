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
        chassisWithEffects = new Chassis("chasis1", 500f, 100, 100,
            chassisEffects, .1f);
        tiresWithEffects = getTiresWithEffects();
        suspensionsWithEffects = getSuspensionsWithEffects();
        brakesWithEffects = getBrakesWithEffects();
        floorWithEffects = new Floor("floor", 20f, 100, 100, floorEffects, .1f);
        frontWithEffects = new Front("front", 20f, 100, 100, frontEffects, .1f);
        backWithEffects = new Back("back", 20f, 100, 100, backEffects, .1f);
        sidesWithEffects = new Sides("sides", 20f, 100, 100, sidesEffects,
            .1f);
        fuel = 100;

        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);
    }

    private static Brakes[] getBrakes() {
        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, new ArrayList<>(),
            CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, new ArrayList<>(),
            CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, new ArrayList<>(),
            CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, new ArrayList<>(),
            CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Brakes[] {brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private Brakes[] getBrakesWithEffects() {
        Effect brake15 = new Effect(EffectType.BRAKE, 15);
        brakesEffects = new ArrayList<>(Collections.singletonList(brake15));

        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, brakesEffects,
            CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, brakesEffects,
            CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, brakesEffects,
            CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, brakesEffects,
            CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[] {brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private static Tires[] getTires() {
        Tires tireFL = new Tires("tireFL", 20f, 100, 100, new ArrayList<>(),
            CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, new ArrayList<>(),
            CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, new ArrayList<>(),
            CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, new ArrayList<>(),
            CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Tires[] {tireFL, tireFR, tireRL, tireRR};
    }

    private Tires[] getTiresWithEffects() {
        Effect grip10 = new Effect(EffectType.GRIP, 10);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect maxSpeedMinus2 = new Effect(EffectType.MAX_SPEED, -2);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);

        tiresEffects = new ArrayList<>(Arrays.asList(grip10, brake3, maxSpeedMinus2, acceleration1));

        Tires tireFL = new Tires("tireFL", 20f, 100, 100, tiresEffects,
            CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, tiresEffects,
            CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, tiresEffects,
            CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, tiresEffects,
            CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Tires[] {tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensions() {
        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100,
            100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100,
            100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100,
            100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100,
            100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Suspension[] {suspensionFL, suspensionFR, suspensionRL, suspensionRR};
    }

    private Suspension[] getSuspensionsWithEffects() {
        Effect grip3 = new Effect(EffectType.GRIP, 3);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);
        Effect maxSpeed1 = new Effect(EffectType.MAX_SPEED, 1);

        suspensionEffects = new ArrayList<>(Arrays.asList(grip3, brake3, acceleration1, maxSpeed1));

        Suspension suspensionFL = new Suspension("suspensionFL", 25f, 100,
            100, suspensionEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension suspensionFR = new Suspension("suspensionFR", 25f, 100,
            100, suspensionEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension suspensionRL = new Suspension("suspensionRL", 25f, 100,
            100, suspensionEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension suspensionRR = new Suspension("suspensionRR", 25f, 100,
            100, suspensionEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Suspension[] {suspensionFL, suspensionFR, suspensionRL, suspensionRR};
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
        assertEquals(150, car.getMaxSpeed());
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
            new Car(null, null, null, null, null, null, null, null,
                null, -1);
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

        Tires[] invalidTires = new Tires[] {null, null, null, null};
        Suspension[] invalidSuspension = new Suspension[] {null, null, null, null};
        Brakes[] invalidBrakes = new Brakes[] {null, null, null, null};

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

    private WheelMountedComponent getComponentByPosition(WheelMountedComponent[] components, CarAxis axle,
                                                            CarSides side) {
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
        Tires mockTireFL = new MockTires("mockTireFL", 18f, 100,100,
            new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires mockTireFR = new MockTires("mockTireFR", 18f, 100,100,
            new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires mockTireRL = new MockTires("mockTireRL", 18f, 100,100,
            new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Tires mockTireRR = new MockTires("mockTireRR", 18f, 100,100,
            new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Tires[] {mockTireFL, mockTireFR, mockTireRL, mockTireRR};
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
        Suspension mockSuspensionFL = new MockSuspension("mockSuspensionFL", 18f, 100,
            100, new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Suspension mockSuspensionFR = new MockSuspension("mockSuspensionFR", 18f, 100,
            100, new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Suspension mockSuspensionRL = new MockSuspension("mockSuspensionRL", 18f, 100,
            100, new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Suspension mockSuspensionRR = new MockSuspension("mockSuspensionRR", 18f, 100,
            100, new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Suspension[] {mockSuspensionFL, mockSuspensionFR, mockSuspensionRL, mockSuspensionRR};
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
        Brakes mockBrakeFL = new MockBrakes("mockBrakeFL", 18f, 100,100,
            new ArrayList<>(), CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes mockBrakeFR = new MockBrakes("mockBrakeFR", 18f, 100,100,
            new ArrayList<>(), CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes mockBrakeRL = new MockBrakes("mockBrakeRL", 18f, 100,100,
            new ArrayList<>(), CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes mockBrakeRR = new MockBrakes("mockBrakeRR", 18f, 100,100,
            new ArrayList<>(), CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[] {mockBrakeFL, mockBrakeFR, mockBrakeRL, mockBrakeRR};
    }

    @Test
    public void testCarAttributes() {
        float expectedSpeed = 158;
        float expectedTireGrip = 40;
        float expectedComplementGrip = 22;
        float expectedTotalGrip = expectedTireGrip + expectedComplementGrip;
        float expectedBrake = 86;
        float expectedAcceleration = 35;
        float expectedWeight = engineWithEffects.getWeight() + chassisWithEffects.getWeight() +
            tiresWithEffects[0].getWeight() * 4 + suspensionsWithEffects[0].getWeight() * 4 +
            brakesWithEffects[0].getWeight() * 4 + floorWithEffects.getWeight() + frontWithEffects.getWeight() +
            backWithEffects.getWeight() + sidesWithEffects.getWeight();

        assertEquals(expectedAcceleration, carWithEffects.getAcceleration());
        assertEquals(expectedSpeed, carWithEffects.getMaxSpeed());
        assertEquals(expectedComplementGrip, carWithEffects.getComplementGrip());
        assertEquals(expectedTireGrip, carWithEffects.getTireGrip());
        assertEquals(expectedTotalGrip, carWithEffects.getTotalGrip());
        assertEquals(expectedBrake, carWithEffects.getBrakePower());
        assertEquals(expectedWeight, carWithEffects.getWeight());
    }

    @Test
    public void accelerate() {
        float delta = 0.1f;
        float expected = 35 * 0.1f;
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
        float expected = carWithEffects.getSpeed() -
            (carWithEffects.getBrakePower() * 0.1f * carWithEffects.getReverseAcceleration() * delta);

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

        float initialX = car.getPositionX();
        float initialY = car.getPositionY();

        car.updatePosition(1);

        assertEquals(initialX, car.getPositionX());
        assertEquals(initialY, car.getPositionY());
    }

    @Test
    public void testForwardMovement() {
        float initialX = carWithEffects.getPositionX();
        float initialY = carWithEffects.getPositionY();

        carWithEffects.accelerate(1);
        carWithEffects.updatePosition(1);

        assertTrue(carWithEffects.getPositionX() > initialX);
        assertEquals(initialY, carWithEffects.getPositionY());
    }

    @Test
    public void testBasicTurnRight() {
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.accelerate(1);
        float grip = carWithEffects.getTireGrip();
        float complementGrip = carWithEffects.getComplementGrip();

        carWithEffects.moveRight(1);

        assertTrue(carWithEffects.getAngle() < initialAngle);
    }

    @Test
    void testBasicLeftTurn() {
        carWithEffects.accelerate(1.0f);
        float initialAngle = carWithEffects.getAngle();

        carWithEffects.moveLeft(1.0f);

        // Angle should increase when turning left
        assertTrue(carWithEffects.getAngle() > initialAngle);
    }

    @Test
    public void testSpeedLimitInTurn() {
        float maxTurnSpeed = carWithEffects.getMaxSpeed() - 20;
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(0.1f);
        }

        carWithEffects.moveRight(1);

        assertTrue(carWithEffects.getSpeed() <= maxTurnSpeed);
    }

    @Test
    public void testGripAffectsTurnRate() {
        Car lowGripCar = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);

        while(lowGripCar.getTireGrip() > 30) {
            lowGripCar.degradeTiresByImpact(0.9f);
        }

        carWithEffects.accelerate(1);
        lowGripCar.accelerate(1);
        carWithEffects.moveRight(1);
        lowGripCar.moveRight(1);

        float highGripTurn = Math.abs(carWithEffects.getAngle());
        float lowGripTurn = Math.abs(lowGripCar.getAngle());
        assertTrue(highGripTurn > lowGripTurn);
    }

    @Test
    public void testSymmetricalTurning() {
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.accelerate(1);

        carWithEffects.moveRight(1);
        float rightTurnAngle = carWithEffects.getAngle();

        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);
        carWithEffects.accelerate(1);
        carWithEffects.moveLeft(1);
        float leftTurnAngle = carWithEffects.getAngle();

        assertEquals(initialAngle + (rightTurnAngle - initialAngle),
            initialAngle - (leftTurnAngle - initialAngle),
            0.001f);
    }

    private Tires[] getTiresWithLowDurability() {
        Tires tireFL = new Tires("tireFL", 20f, 100, 1, new ArrayList<>(),
            CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 1, new ArrayList<>(),
            CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 1, new ArrayList<>(),
            CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 1, new ArrayList<>(),
            CarAxis.REAR, CarSides.RIGHT, .1f);
        return new Tires[] {tireFL, tireFR, tireRL, tireRR};
    }

    @Test
    public void testNoTireGrip() {
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tires, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);
        carWithEffects.accelerate(1);

        assertEquals(0, carWithEffects.getSpeed());
        assertEquals(0, carWithEffects.getPositionX());
        assertEquals(0, carWithEffects.getPositionY());

        Tires[] tires = getTiresWithLowDurability();

        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tires, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);

        for (int i = 0; i < 10000; i++) {
            carWithEffects.accelerate(1f);
            carWithEffects.naturalSlowDown(1f);
            Map<CarSides, Float> sides = new HashMap<>();
            sides.put(CarSides.LEFT, .5f);
            sides.put(CarSides.RIGHT, .5f);
            carWithEffects.degradeTires(1f, sides);
        }

        assertEquals(0, carWithEffects.getSpeed());
    }

    @Test
    void testConstructorWithMinimumValidFuel() {
        // Valid equivalent partition: fuel = 0
        Car car = new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 0);
        assertEquals(0, car.getFuelLevel());
    }

    @Test
    void testConstructorWithMaximumValidFuel() {
        // Valid equivalent partition: fuel = 100
        Car car = new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 100);
        assertEquals(100, car.getFuelLevel());
    }

    @Test
    void testConstructorWithMidRangeFuel() {
        // Valid equivalent partition: fuel entre 0 y 100
        Car car = new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 50);
        assertEquals(50, car.getFuelLevel());
    }

    @Test
    void testConstructorWithNegativeFuel() {
        // Frontier value invalid: fuel < 0
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, -0.1f);
        });
    }

    @Test
    void testConstructorWithFuelJustBelowMinimum() {
        // Frontier value invalid: fuel < 0
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, -0.000001f);
        });
    }

    @Test
    void testConstructorWithFuelJustAboveMaximum() {
        // Frontier value invalid: fuel > 100
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 100.001f);
        });
    }

    @Test
    void testConstructorWithExcessiveFuel() {
        // Frontier value invalid: fuel > 100
        assertThrows(IllegalArgumentException.class, () -> {
            new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, 101f);
        });
    }

    @Test
    void testBasicAcceleration() {
        // Test normal acceleration from standstill
        float delta = 0.1f;
        float expectedSpeed = carWithEffects.getAcceleration() * delta;
        carWithEffects.accelerate(delta);
        assertEquals(expectedSpeed, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testAccelerationToMaxSpeed() {
        // Test acceleration up to max speed
        float delta = 0.1f;
        float maxSpeed = carWithEffects.getMaxSpeed();

        while(carWithEffects.getSpeed() < maxSpeed) {
            carWithEffects.accelerate(delta);
        }

        // Try to exceed max speed
        carWithEffects.accelerate(delta);
        assertEquals(maxSpeed, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testBrakingFromForwardMotion() {
        // Accelerate first
        carWithEffects.accelerate(1.0f);
        float initialSpeed = carWithEffects.getSpeed();
        float delta = 0.1f;

        // Apply brakes
        carWithEffects.brakeAndReverse(delta);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);
    }

    @Test
    void testCompleteStop() {
        // First get to some speed
        carWithEffects.accelerate(1.0f);
        float delta = 0.1f;

        // Brake until complete stop
        while (carWithEffects.getSpeed() > 0) {
            carWithEffects.brakeAndReverse(delta);
        }

        assertEquals(0, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testMaxReverseSpeed() {
        float delta = 0.1f;
        float expectedMaxReverse = carWithEffects.getMaxReverseSpeed();

        while(carWithEffects.getSpeed() > expectedMaxReverse) {
            carWithEffects.brakeAndReverse(delta);
        }

        // Try to exceed max reverse speed
        carWithEffects.brakeAndReverse(delta);
        assertEquals(expectedMaxReverse, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testNoMovementWithoutGrip() {
        // Degrade tires to eliminate grip
        while (carWithEffects.getTireGrip() > 0.1f) {
            carWithEffects.degradeTiresByImpact(1f);
        }

        // Try to accelerate
        carWithEffects.accelerate(1.0f);
        assertEquals(0, carWithEffects.getSpeed(), 0.001f);

        // Try to brake/reverse
        carWithEffects.brakeAndReverse(1.0f);
        assertEquals(0, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testNaturalDeceleration() {
        // Get to some speed first
        carWithEffects.accelerate(1.0f);
        float initialSpeed = carWithEffects.getSpeed();

        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);
    }

    @Test
    void testNaturalDecelerationInReverse() {
        // Get to reverse speed
        float delta = 0.1f;
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(delta);
        }
        float initialReverseSpeed = carWithEffects.getSpeed();

        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(Math.abs(carWithEffects.getSpeed()) < Math.abs(initialReverseSpeed));
    }

    @Test
    void testTransitionForwardToReverse() {
        // Start moving forward
        carWithEffects.accelerate(1.0f);
        float delta = 0.1f;

        // Brake to stop and continue to reverse
        while (carWithEffects.getSpeed() >= 0) {
            carWithEffects.brakeAndReverse(delta);
        }

        assertTrue(carWithEffects.getSpeed() < 0);
    }

    @Test
    void testTurningSpeedLimit() {
        // Accelerate to max speed
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(0.1f);
        }
        float maxTurnSpeed = carWithEffects.getMaxSpeed() - 20;

        // Try to turn at max speed
        carWithEffects.moveRight(1.0f);

        // Speed should be reduced to turning speed limit
        assertTrue(carWithEffects.getSpeed() <= maxTurnSpeed);
    }

    @Test
    void testTurnRateWithDifferentGrip() {
        // Test with normal grip
        carWithEffects.accelerate(1.0f);
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(1.0f);
        float normalGripTurnRate = Math.abs(carWithEffects.getAngle() - initialAngle);

        // Reset car with degraded grip
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);

        carWithEffects.degradeTiresByImpact(0.5f);
        carWithEffects.accelerate(1.0f);
        initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(1.0f);
        float lowGripTurnRate = Math.abs(carWithEffects.getAngle() - initialAngle);

        // Turn rate should be lower with less grip
        assertTrue(lowGripTurnRate < normalGripTurnRate);
    }

    @Test
    void testNoTurnWithoutSpeed() {
        float initialAngle = carWithEffects.getAngle();

        carWithEffects.moveRight(1.0f);

        // Car shouldn't turn when not moving
        assertEquals(initialAngle, carWithEffects.getAngle(), 0.001f);
    }

    @Test
    void testNoTurnWithoutGrip() {
        carWithEffects.accelerate(1.0f);
        carWithEffects.degradeTiresByImpact(1f); // Eliminate grip
        float initialAngle = carWithEffects.getAngle();

        carWithEffects.moveRight(1.0f);

        // Car shouldn't turn without grip
        assertEquals(initialAngle, carWithEffects.getAngle(), 0.001f);
    }

    @Test
    void testTurnRateWithSpeed() {
        // Test turn rate at low speed
        carWithEffects.accelerate(0.1f);
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(1.0f);
        float lowSpeedTurnRate = Math.abs(carWithEffects.getAngle() - initialAngle);

        // Reset and test at high speed
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);

        // Accelerate to higher speed
        carWithEffects.accelerate(1.0f);
        initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(1.0f);
        float highSpeedTurnRate = Math.abs(carWithEffects.getAngle() - initialAngle);

        // Turn rate should be different at different speeds
        assertNotEquals(lowSpeedTurnRate, highSpeedTurnRate, 0.001f);
    }

    @Test
    void testReverseTurning() {
        // Get to reverse speed
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(0.1f);
        }

        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(1.0f);

        // Turning should work in reverse
        assertNotEquals(initialAngle, carWithEffects.getAngle(), 0.001f);
    }

    @Test
    void testUniformTireDegradation() {
        float initialGrip = carWithEffects.getTireGrip();
        float delta = 0.1f;

        // Set uniform degradation for both sides
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, 0.5f);
        sides.put(CarSides.RIGHT, 0.5f);

        carWithEffects.degradeTires(delta, sides);

        // Grip should be reduced
        assertTrue(carWithEffects.getTireGrip() < initialGrip);

        // All tires should have same durability
        Tires[] tires = carWithEffects.getTires();
        float firstTireDurability = tires[0].getCurrentDurability();
        for(Tires tire : tires) {
            assertEquals(firstTireDurability, tire.getCurrentDurability(), 0.001f);
        }
    }

    // PAIRWISE TESTING

    @Test
    void testZeroSpeedWithGripTurningLeft() {
        // Test 1: Zero Speed, With Grip, Turning Left
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveLeft(0.1f);
        assertEquals(0, carWithEffects.getSpeed());
        assertEquals(initialAngle, carWithEffects.getAngle());
    }

    @Test
    void testZeroSpeedNoGripTurningRight() {
        // Test 2: Zero Speed, No Grip, Turning Right
        carWithEffects.degradeTiresByImpact(0.01f);
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(0.1f);
        assertEquals(0, carWithEffects.getSpeed());
        assertEquals(initialAngle, carWithEffects.getAngle());
    }

    @Test
    void testForwardSpeedWithGripTurningRight() {
        // Test 3: Forward Speed, With Grip, Turning Right
        carWithEffects.accelerate(0.1f);
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(0.1f);
        assertTrue(carWithEffects.getSpeed() > 0);
        assertTrue(carWithEffects.getAngle() < initialAngle);
    }

    @Test
    void testForwardSpeedNoGripStraight() {
        // Test 4: Forward Speed, No Grip, Straight
        carWithEffects.accelerate(0.1f);
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.degradeTiresByImpact(0.01f);
        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);
    }

    @Test
    void testReverseSpeedWithGripTurningLeft() {
        // Test 5: Reverse Speed, With Grip, Turning Left
        float delta = 0.1f;
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(delta);
        }
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveLeft(delta);
        assertTrue(carWithEffects.getSpeed() < 0);
        assertTrue(carWithEffects.getAngle() < initialAngle);
    }

    @Test
    void testReverseSpeedNoGripStraight() {
        // Test 6: Reverse Speed, No Grip, Straight
        float delta = 0.1f;
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(delta);
        }
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.degradeTiresByImpact(0.01f);
        carWithEffects.naturalSlowDown(delta);
        assertTrue(Math.abs(carWithEffects.getSpeed()) < Math.abs(initialSpeed));
    }

    @Test
    void testForwardSpeedWithGripStraight() {
        // Test 7: Forward Speed, With Grip, Straight
        carWithEffects.accelerate(0.1f);
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);
    }

    @Test
    void testZeroSpeedWithGripStraight() {
        // Test 8: Zero Speed, With Grip, Straight
        assertEquals(0, carWithEffects.getSpeed());
    }

    @Test
    void testReverseSpeedWithGripTurningRight() {
        // Test 9: Reverse Speed, With Grip, Turning Right
        float delta = 0.1f;
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(delta);
        }
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(delta);
        assertTrue(carWithEffects.getSpeed() < 0);
        assertTrue(carWithEffects.getAngle() > initialAngle);
    }

    // STATEMENT COVERAGE
    @Test
    void testUpdatePositionStatements() {
        // Test when speed is 0 (early return)
        carWithEffects.updatePosition(0.1f);
        assertEquals(0, carWithEffects.getPositionX());
        assertEquals(0, carWithEffects.getPositionY());

        // Test with non-zero speed (covers all statements)
        carWithEffects.accelerate(1.0f);
        float expectedX = (float) (carWithEffects.getSpeed() * Math.cos(Math.toRadians(carWithEffects.getAngle())));
        float expectedY = (float) (carWithEffects.getSpeed() * Math.sin(Math.toRadians(carWithEffects.getAngle())));
        carWithEffects.updatePosition(1.0f);
        assertEquals(expectedX, carWithEffects.getPositionX(), 0.001f);
        assertEquals(expectedY, carWithEffects.getPositionY(), 0.001f);
    }

    @Test
    void testAccelerateStatements() {
        // Test with no grip (early return)
        carWithEffects.degradeTiresByImpact(1f);
        carWithEffects.accelerate(1.0f);
        assertEquals(0, carWithEffects.getSpeed());

        // Test normal acceleration
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);
        carWithEffects.accelerate(1.0f);
        assertEquals(carWithEffects.getAcceleration(), carWithEffects.getSpeed());

        // Test acceleration limit
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(1.0f);
        }
        float maxSpeed = carWithEffects.getSpeed();
        carWithEffects.accelerate(1.0f);
        assertEquals(maxSpeed, carWithEffects.getSpeed());
    }

    @Test
    void testBrakeAndReverseStatements() {
        // Test with no grip (early return)
        carWithEffects.degradeTiresByImpact(1f);
        carWithEffects.brakeAndReverse(1.0f);
        assertEquals(0, carWithEffects.getSpeed());

        // Reset car
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);

        // Test braking from forward motion
        carWithEffects.accelerate(1.0f);
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.brakeAndReverse(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);

        // Test stopping
        while(carWithEffects.getSpeed() > 0.01f) {
            carWithEffects.brakeAndReverse(0.1f);
        }
        assertEquals(0, carWithEffects.getSpeed(), 0.01f);

        // Test reverse acceleration
        carWithEffects.brakeAndReverse(1.0f);
        assertTrue(carWithEffects.getSpeed() < 0);

        // Test reverse speed limit
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(0.1f);
        }
        float maxReverseSpeed = carWithEffects.getSpeed();
        carWithEffects.brakeAndReverse(0.1f);
        assertEquals(maxReverseSpeed, carWithEffects.getSpeed(), 0.001f);
    }

    @Test
    void testNaturalSlowDownStatements() {
        // Test at zero speed
        carWithEffects.naturalSlowDown(0.1f);
        assertEquals(0, carWithEffects.getSpeed());

        // Test slowdown from forward speed
        carWithEffects.accelerate(1.0f);
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);

        // Test slowdown from reverse speed
        carWithEffects.brakeAndReverse(1.0f);
        while(carWithEffects.getSpeed() > carWithEffects.getMaxReverseSpeed()) {
            carWithEffects.brakeAndReverse(0.1f);
        }
        initialSpeed = carWithEffects.getSpeed();
        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() > initialSpeed);

        // Test with no grip
        carWithEffects.degradeTiresByImpact(1f);
        carWithEffects.accelerate(1.0f);
        carWithEffects.naturalSlowDown(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);
    }

    @Test
    void testTurningMethodsStatements() {
        // Test moveRight with zero speed
        float initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(0.1f);
        assertEquals(initialAngle, carWithEffects.getAngle());

        // Test moveRight at speed
        carWithEffects.accelerate(1.0f);
        initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(0.1f);
        assertTrue(carWithEffects.getAngle() < initialAngle);

        // Test moveRight at high speed (speed reduction)
        while(carWithEffects.getSpeed() < carWithEffects.getMaxSpeed()) {
            carWithEffects.accelerate(0.1f);
        }
        float initialSpeed = carWithEffects.getSpeed();
        carWithEffects.moveRight(0.1f);
        assertTrue(carWithEffects.getSpeed() < initialSpeed);

        // Test moveLeft
        carWithEffects = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects,
            suspensionsWithEffects, brakesWithEffects, floorWithEffects, frontWithEffects,
            backWithEffects, sidesWithEffects, 100);
        carWithEffects.accelerate(1.0f);
        initialAngle = carWithEffects.getAngle();
        carWithEffects.moveLeft(0.1f);
        assertTrue(carWithEffects.getAngle() > initialAngle);

        // Test with no grip
        carWithEffects.degradeTiresByImpact(1f);
        initialAngle = carWithEffects.getAngle();
        carWithEffects.moveRight(0.1f);
        assertEquals(initialAngle, carWithEffects.getAngle());
        carWithEffects.moveLeft(0.1f);
        assertEquals(initialAngle, carWithEffects.getAngle());
    }

    @Test
    void testDegradationMethodsStatements() {
        // Test uniform degradation
        Map<CarSides, Float> sides = new HashMap<>();
        sides.put(CarSides.LEFT, 0.5f);
        sides.put(CarSides.RIGHT, 0.5f);
        carWithEffects.degradeTires(0.1f, sides);
        carWithEffects.degradeSuspension(0.1f, sides);
        carWithEffects.degradeBrakes(0.1f);

        // Test impact degradation
        float impactValue = 0.8f;
        carWithEffects.degradeEngineByImpact(impactValue);
        carWithEffects.degradeChassisByImpact(impactValue);
        carWithEffects.degradeTiresByImpact(impactValue);
        carWithEffects.degradeSuspensionByImpact(impactValue);
        carWithEffects.degradeBrakesByImpact(impactValue);
        carWithEffects.degradeFloorByImpact(impactValue);
        carWithEffects.degradeFrontByImpact(impactValue);
        carWithEffects.degradeBackByImpact(impactValue);
        carWithEffects.degradeSidesByImpact(impactValue);
    }

    @Test
    void test() {
        assertEquals(0, car.getTotalGrip());
    }

    @Test
    void testBrakeNull() {
        brakes[0] = null;

        assertThrows(IllegalArgumentException.class, () -> new Car(engine, chassis, tires, suspensions, brakes,
            floor, front, back, sides, 100));
    }

    @Test
    void testSuspensionNull() {
        suspensions[0] = null;

        assertThrows(IllegalArgumentException.class, () -> new Car(engine, chassis, tires, suspensions, brakes,
            floor, front, back, sides, 100));
    }

    @Test
    void testTireNull() {
        tires[0] = null;

        assertThrows(IllegalArgumentException.class, () -> new Car(engine, chassis, tires, suspensions, brakes,
            floor, front, back, sides, 100));
    }
}
