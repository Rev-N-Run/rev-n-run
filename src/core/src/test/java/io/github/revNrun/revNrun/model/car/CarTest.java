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
    }

    private static Brakes[] getBrakes() {
        Effect brake15 = new Effect(EffectType.BRAKE, 15);
        List<Effect> brakesEffects = new ArrayList<>(Arrays.asList(brake15));

        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[]{brakeFL, brakeFR, brakeRL, brakeRR};
    }

    private static Tires[] getTires() {
        Effect grip10 = new Effect(EffectType.GRIP, 10);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect maxSpeedMinus2 = new Effect(EffectType.MAX_SPEED, -2);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);

        List<Effect> tiresEffects = new ArrayList<>(Arrays.asList(grip10, brake3, maxSpeedMinus2, acceleration1));

        Tires tireFL = new Tires("tireFL", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Tires[]{tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensions() {
        Effect grip3 = new Effect(EffectType.GRIP, 3);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);
        Effect maxSpeed1 = new Effect(EffectType.MAX_SPEED, 1);

        List<Effect> suspensionEffects = new ArrayList<>(Arrays.asList(grip3, brake3, acceleration1, maxSpeed1));

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


        List<Effect> engineEffects = new ArrayList<>(Arrays.asList(acceleration10, maxSpeed15));
        List<Effect> chassisEffects = new ArrayList<>(Collections.singletonList(grip1));
        List<Effect> floorEffects = new ArrayList<>(Arrays.asList(grip4, brake1, accelerationMinus1, maxSpeedMinus1));
        List<Effect> frontEffects = new ArrayList<>(Arrays.asList(grip2, brake1, accelerationMinus2, maxSpeedMinus2));
        List<Effect> backEffects = new ArrayList<>(Collections.singletonList(grip1));
        List<Effect> sidesEffects = new ArrayList<>(Collections.singletonList(grip2));

        engine = new Engine("engine1", 200f, 100, 100, engineEffects, .1f);
        chassis = new Chassis("chasis1", 500f, 100, 100, chassisEffects, .1f);
        tires = getTires();
        suspensions = getSuspensions();
        brakes = getBrakes();
        floor = new Floor("floor", 20f, 100, 100, floorEffects, .1f);
        front = new Front("front", 20f, 100, 100, frontEffects, .1f);
        back = new Back("back", 20f, 100, 100, backEffects, .1f);
        sides = new Sides("sides", 20f, 100, 100, sidesEffects, .1f);
        fuel = 100;

        car = new Car(engine, chassis, tires, suspensions, brakes, floor, front, back, sides, fuel);

        float expectedSpeed = car.getSpeed();
        float expectedGrip = 0;
        float expectedBrake = 0;
        float expectedAcceleration = 0;
        float expectedWeight = engine.getWeight() + chassis.getWeight() + tires[0].getWeight() * 4 +
            suspensions[0].getWeight() * 4 + brakes[0].getWeight() * 4 + floor.getWeight() + front.getWeight() +
            back.getWeight() + sides.getWeight();

        List<Effect> effects = new ArrayList<>();
        effects.addAll(engineEffects);
        effects.addAll(chassisEffects);
        effects.addAll(floorEffects);
        effects.addAll(frontEffects);
        effects.addAll(backEffects);
        effects.addAll(sidesEffects);

        List<Effect> tiresEffects = new ArrayList<>();

        for (Tires tire : tires) {
            tiresEffects.addAll(tire.getEffects());
        }

        List<Effect> suspensionEffects = new ArrayList<>();

        for (Suspension suspension : suspensions) {
            suspensionEffects.addAll(suspension.getEffects());
        }

        List<Effect> brakesEffects = new ArrayList<>();

        for (Brakes brake : brakes) {
            brakesEffects.addAll(brake.getEffects());
        }

        effects.addAll(tiresEffects);
        effects.addAll(suspensionEffects);
        effects.addAll(brakesEffects);

        for (Effect effect : effects) {
            switch (effect.getEffect()) {
                case GRIP:
                    expectedGrip += effect.getValue();
                    break;
                case MAX_SPEED:
                    expectedSpeed += effect.getValue();
                    break;
                case ACCELERATION:
                    expectedAcceleration += effect.getValue();
                    break;
                case BRAKE:
                    expectedBrake += effect.getValue();
                    break;
            }
        }

        assertEquals(expectedAcceleration, car.getAcceleration());
        assertEquals(expectedSpeed, car.getMaxSpeed());
        assertEquals(expectedGrip, car.getGrip());
        assertEquals(expectedBrake, car.getBrakePower());
        assertEquals(expectedWeight, car.getWeight());
    }
}
