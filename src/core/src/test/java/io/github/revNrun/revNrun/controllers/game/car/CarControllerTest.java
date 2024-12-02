package io.github.revNrun.revNrun.controllers.game.car;

import com.badlogic.gdx.Input;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.InputHelper;
import io.github.revNrun.revNrun.model.car.Car;
import io.github.revNrun.revNrun.model.car.components.*;
import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;
import io.github.revNrun.revNrun.model.ghost_car.GhostCar;
import io.github.revNrun.revNrun.model.lap_timer.LapTimer;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.CarViewMock;
import io.github.revNrun.revNrun.view.ViewUtils;
import io.github.revNrun.revNrun.view.car.ICarView;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CarControllerTest {
    List<Effect> engineEffects;
    List<Effect> chassisEffects;
    List<Effect> floorEffects;
    List<Effect> frontEffects;
    List<Effect> backEffects;
    List<Effect> sidesEffects;
    List<Effect> tiresEffects;
    List<Effect> suspensionEffects;
    List<Effect> brakesEffects;
    Car car;
    Engine engineWithEffects;
    Chassis chassisWithEffects;
    Tires[] tiresWithEffects;
    Suspension[] suspensionsWithEffects;
    Brakes[] brakesWithEffects;
    Floor floorWithEffects;
    Front frontWithEffects;
    Back backWithEffects;
    Sides sidesWithEffects;
    InputHelper mockInputHelper;
    InputHandler input;
    CarController controller;
    ICarView view;

    @BeforeEach
    void setUp() {
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
        float fuel = 100;

        car = new Car(engineWithEffects, chassisWithEffects, tiresWithEffects, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);

        mockInputHelper = mock(InputHelper.class);
        input = new InputHandler(mockInputHelper);
        view = new CarViewMock();
        controller = new CarController(car, input, view);
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

        return new Tires[] {tireFL, tireFR, tireRL, tireRR};
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

    private Brakes[] getBrakesWithEffects() {
        Effect brake15 = new Effect(EffectType.BRAKE, 15);
        brakesEffects = new ArrayList<>(Collections.singletonList(brake15));

        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[] {brakeFL, brakeFR, brakeRL, brakeRR};
    }

    @Test
    void handleInput() {
        // Check if car is moving forward
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        controller.handleInput(1);

        assertTrue(car.getSpeed() > 0);
        assertEquals(0, car.getAngle());
        assertTrue(car.getPositionX() != 0);

        float currentSpeed = car.getSpeed();

        // Check if car is braking
        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);
        controller.handleInput(1);

        assertTrue(car.getSpeed() < currentSpeed);
        assertEquals(0, car.getAngle());
        assertTrue(car.getPositionX() != 0);

        // Check if car is turning left
        while (car.getSpeed() <= 0) {
            car.accelerate(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        controller.handleInput(1);

        assertTrue(car.getAngle() > 0);
        assertTrue(car.getPositionX() != 0);
        assertTrue(car.getPositionY() != 0);

        // Check if car is turning right
        while (car.getSpeed() <= 0) {
            car.accelerate(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        controller.handleInput(1);
        controller.handleInput(1);
        controller.handleInput(1);

        assertTrue(car.getAngle() < 0);
        assertTrue(car.getPositionX() != 0);
        assertTrue(car.getPositionY() != 0);
    }

    @Test
    void degradationAcceleration() {
        // Check if car is moving forward
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        controller.handleInput(1);

        Tires[] tires = car.getTires();
        float symmetricalDurability = tires[0].getCurrentDurability();
        for (Tires tire : tires) {
            assertTrue(tire.getCurrentDurability() < tire.getMaxDurability());
            assertEquals(symmetricalDurability, tire.getCurrentDurability());
        }

        Suspension[] suspensions = car.getSuspension();
        float symmetricalDurability1 = suspensions[0].getCurrentDurability();
        for (Suspension suspension : suspensions) {
            assertTrue(suspension.getCurrentDurability() < suspension.getMaxDurability());
            assertEquals(symmetricalDurability1, suspension.getCurrentDurability());
        }

        Engine engine = car.getEngine();
        assertTrue(engine.getCurrentDurability() < engine.getMaxDurability());
    }

    @Test
    void degradationBrake() {
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        for (int i = 0; i < 3; i++) {
            controller.handleInput(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);
        controller.handleInput(1);

        Tires[] tires = car.getTires();
        float symmetricalDurability = tires[0].getCurrentDurability();
        for (Tires tire : tires) {
            assertTrue(tire.getCurrentDurability() < tire.getMaxDurability());
            assertEquals(symmetricalDurability, tire.getCurrentDurability());
        }

        Suspension[] suspensions = car.getSuspension();
        float symmetricalDurability1 = suspensions[0].getCurrentDurability();
        for (Suspension suspension : suspensions) {
            assertTrue(suspension.getCurrentDurability() < suspension.getMaxDurability());
            assertEquals(symmetricalDurability1, suspension.getCurrentDurability());
        }

        Brakes[] brakes = car.getBrakes();

        Brakes brakeFL = getWheelMountedComponent(brakes, CarAxis.FRONT, CarSides.LEFT);
        Brakes brakeFR = getWheelMountedComponent(brakes, CarAxis.FRONT, CarSides.RIGHT);
        Brakes brakeRL = getWheelMountedComponent(brakes, CarAxis.REAR, CarSides.LEFT);
        Brakes brakeRR = getWheelMountedComponent(brakes, CarAxis.REAR, CarSides.RIGHT);

        assertEquals(brakeFL.getCurrentDurability(), brakeFR.getCurrentDurability());
        assertEquals(brakeRL.getCurrentDurability(), brakeRR.getCurrentDurability());
        assertNotEquals(brakeFL.getCurrentDurability(), brakeRR.getCurrentDurability());

        for (Brakes brake : brakes) {
            assertTrue(brake.getCurrentDurability() < brake.getMaxDurability());
        }
    }

    private <T extends WheelMountedComponent> T getWheelMountedComponent(T[] components, CarAxis axle,
                                                                            CarSides side) {
        for (T component : components) {
            if (component.getAxle() == axle && component.getSide() == side) {
                return component;
            }
        }

        return null;
    }


    @Test
    void degradationLeftTurn() {
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        for (int i = 0; i < 3; i++) {
            controller.handleInput(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        controller.handleInput(1);

        Tires[] tires = car.getTires();

        Tires tireFL = getWheelMountedComponent(tires, CarAxis.FRONT, CarSides.LEFT);
        Tires tireFR = getWheelMountedComponent(tires, CarAxis.FRONT, CarSides.RIGHT);
        Tires tireRL = getWheelMountedComponent(tires, CarAxis.REAR, CarSides.LEFT);
        Tires tireRR = getWheelMountedComponent(tires, CarAxis.REAR, CarSides.RIGHT);

        assertEquals(tireFL.getCurrentDurability(), tireRL.getCurrentDurability());
        assertEquals(tireFR.getCurrentDurability(), tireRR.getCurrentDurability());
        assertNotEquals(tireFL.getCurrentDurability(), tireRR.getCurrentDurability());

        for (Tires tire : tires) {
            assertTrue(tire.getCurrentDurability() < tire.getMaxDurability());
        }

        Suspension[] suspensions = car.getSuspension();

        Suspension suspensionFL = getWheelMountedComponent(suspensions, CarAxis.FRONT, CarSides.LEFT);
        Suspension suspensionFR = getWheelMountedComponent(suspensions, CarAxis.FRONT, CarSides.RIGHT);
        Suspension suspensionRL = getWheelMountedComponent(suspensions, CarAxis.REAR, CarSides.LEFT);
        Suspension suspensionRR = getWheelMountedComponent(suspensions, CarAxis.REAR, CarSides.RIGHT);

        assertEquals(suspensionFL.getCurrentDurability(), suspensionRL.getCurrentDurability());
        assertEquals(suspensionFR.getCurrentDurability(), suspensionRR.getCurrentDurability());
        assertNotEquals(suspensionFL.getCurrentDurability(), suspensionRR.getCurrentDurability());

        for (Suspension suspension : suspensions) {
            assertTrue(suspension.getCurrentDurability() < suspension.getMaxDurability());
        }
    }

    @Test
    void degradationRightTurn() {
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        for (int i = 0; i < 3; i++) {
            controller.handleInput(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        controller.handleInput(1);

        Tires[] tires = car.getTires();

        Tires tireFL = getWheelMountedComponent(tires, CarAxis.FRONT, CarSides.LEFT);
        Tires tireFR = getWheelMountedComponent(tires, CarAxis.FRONT, CarSides.RIGHT);
        Tires tireRL = getWheelMountedComponent(tires, CarAxis.REAR, CarSides.LEFT);
        Tires tireRR = getWheelMountedComponent(tires, CarAxis.REAR, CarSides.RIGHT);

        assertEquals(tireFL.getCurrentDurability(), tireRL.getCurrentDurability());
        assertEquals(tireFR.getCurrentDurability(), tireRR.getCurrentDurability());
        assertNotEquals(tireFL.getCurrentDurability(), tireRR.getCurrentDurability());

        for (Tires tire : tires) {
            assertTrue(tire.getCurrentDurability() < tire.getMaxDurability());
        }

        Suspension[] suspensions = car.getSuspension();

        Suspension suspensionFL = getWheelMountedComponent(suspensions, CarAxis.FRONT, CarSides.LEFT);
        Suspension suspensionFR = getWheelMountedComponent(suspensions, CarAxis.FRONT, CarSides.RIGHT);
        Suspension suspensionRL = getWheelMountedComponent(suspensions, CarAxis.REAR, CarSides.LEFT);
        Suspension suspensionRR = getWheelMountedComponent(suspensions, CarAxis.REAR, CarSides.RIGHT);

        assertEquals(suspensionFL.getCurrentDurability(), suspensionRL.getCurrentDurability());
        assertEquals(suspensionFR.getCurrentDurability(), suspensionRR.getCurrentDurability());
        assertNotEquals(suspensionFL.getCurrentDurability(), suspensionRR.getCurrentDurability());

        for (Suspension suspension : suspensions) {
            assertTrue(suspension.getCurrentDurability() < suspension.getMaxDurability());
        }
    }

    @Test
    void carDoesNotTurnIfNoSpeed() {
        when(mockInputHelper.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        controller.handleInput(1);

        assertEquals(0, car.getAngle());
        assertEquals(0, car.getPositionX());
        assertEquals(0, car.getPositionY());

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        controller.handleInput(1);

        assertEquals(0, car.getAngle());
        assertEquals(0, car.getPositionX());
        assertEquals(0, car.getPositionY());
    }

    @Test
    void carTurnsInReverse1() {
        when(mockInputHelper.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);
        for (int i = 0; i < 3; i++) {
            controller.handleInput(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.LEFT)).thenReturn(true);
        controller.handleInput(1);

        assertTrue(car.getAngle() < 0);
        assertTrue(car.getPositionX() < 0);
        assertTrue(car.getPositionY() > 0);
    }

    @Test
    void carTurnsInReverse2() {
        when(mockInputHelper.isKeyPressed(Input.Keys.DOWN)).thenReturn(true);
        for (int i = 0; i < 3; i++) {
            controller.handleInput(1);
        }

        reset(mockInputHelper);
        when(mockInputHelper.isKeyPressed(Input.Keys.RIGHT)).thenReturn(true);
        controller.handleInput(1);

        assertTrue(car.getAngle() > 0);
        assertTrue(car.getPositionX() < 0);
        assertTrue(car.getPositionY() < 0);
    }

    @Test
    void recordGhost() {
        int iterations = 5;
        List<Vector2> vectors = new ArrayList<>();
        List<Float> angles = new ArrayList<>();
        when(mockInputHelper.isKeyPressed(Input.Keys.UP)).thenReturn(true);
        for (int i = 0; i < iterations; i++) {
            if (i == 3) {
                reset(mockInputHelper);
            }
            controller.handleInput(1);
            Vector2 pos = controller.getCarPosition();
            vectors.add(new Vector2(pos.getX(), pos.getY()));
            angles.add(controller.getCar().getAngle());
            controller.recordGhost();
        }

        GhostCar ghost = controller.getCurrentGhost();

        for (int i = 0; i < iterations; i++) {
            assertEquals(vectors.get(i).getX(), ghost.getPositionX());
            assertEquals(vectors.get(i).getY(), ghost.getPositionY());
            assertEquals(angles.get(i), ghost.getAngle());
            ghost.nextFrame();
        }
    }

    @Test
    void getCurrentLap() {
        LapTimer timer = new LapTimer();
        assertEquals(timer.getCurrentLapTime(), controller.getCurrentLap());
    }

    @Test
    void getBestGhostLap() throws InterruptedException {
        LapTimer timer = new LapTimer();
        assertEquals(timer.getCurrentLapTime(), controller.getBestGhostLap());

        controller.startLap();
        Thread.sleep(100);
        controller.stopLap();
        controller.compareAndSetLaps();

        assertNotEquals(timer.getCurrentLapTime(), controller.getBestGhostLap());
    }

    @Test
    void testUpdatePositionOutOfBoundsX() {
        Vector2 position = new Vector2(-1, 100);
        controller.getCar().setPosition(position);
        controller.handleInput(.1f);

        Vector2 newPos = car.getPosition();
        assertEquals(0, newPos.getX());
        assertEquals(100, newPos.getY());
    }

    @Test
    void outOfBoundsX() {
        Vector2 position = new Vector2(ViewUtils.WORLD_WIDTH, 100);
        controller.getCar().setPosition(position);
        controller.handleInput(.1f);

        Vector2 newPos = car.getPosition();
        assertEquals(999.0, newPos.getX());
        assertEquals(100, newPos.getY());
    }

    @Test
    void outOfBoundsY1() {
        Vector2 position = new Vector2(100, -1);
        controller.getCar().setPosition(position);
        controller.handleInput(.1f);

        Vector2 newPos = car.getPosition();
        assertEquals(100, newPos.getX());
        assertEquals(0, newPos.getY());
    }

    @Test
    void outOfBoundsY2() {
        Vector2 position = new Vector2(100, ViewUtils.WORLD_HEIGHT);
        controller.getCar().setPosition(position);
        controller.handleInput(.1f);

        Vector2 newPos = car.getPosition();
        assertEquals(100, newPos.getX());
        assertEquals(999.0f, newPos.getY());
    }

    @Test
    void compareAndSetLaps() throws InterruptedException {
        controller.compareAndSetLaps();
        assertEquals(controller.getCurrentGhost().getLapTimer().getLastLapTime(),
            controller.getBestGhost().getLapTimer().getLastLapTime());

        controller.startLap();
        Thread.sleep(50);
        controller.stopLap();
        controller.compareAndSetLaps();
        assertNotEquals(controller.getCurrentGhost().getLapTimer().getLastLapTime(),
            controller.getBestGhost().getLapTimer().getLastLapTime());
    }

    @Test
    void compareAndSetLaps2() throws InterruptedException {
        controller.startLap();
        Thread.sleep(50);
        controller.stopLap();
        controller.compareAndSetLaps();
        assertEquals(controller.getCurrentGhost().getLapTimer().getLastLapTime(),
            controller.getBestGhost().getLapTimer().getLastLapTime());

        controller.startLap();
        controller.stopLap();
        controller.compareAndSetLaps();
        assertEquals(controller.getCurrentGhost().getLapTimer().getLastLapTime(),
            controller.getBestGhost().getLapTimer().getLastLapTime());
    }

    @Test
    void drawCar() {
        Vector2 pos = controller.getCarPosition();
        controller.drawCar();
        assertEquals(pos.getX(), controller.getCarPosition().getX());
        assertEquals(pos.getY(), controller.getCarPosition().getY());
    }

    @Test
    void drawGhost() {
        controller.drawCar();
        assertNull(controller.getBestGhost());

        controller.startLap();
        controller.stopLap();
        controller.compareAndSetLaps();
        Vector2 pos = new Vector2(controller.getBestGhost().getPositionX(), controller.getBestGhost().getPositionY());
        controller.drawGhost();
        assertEquals(pos.getX(), controller.getBestGhost().getPositionX());
        assertEquals(pos.getY(), controller.getBestGhost().getPositionY());
    }

    @Test
    void resetGhost() throws InterruptedException {
        controller.startLap();
        Thread.sleep(50);
        controller.stopLap();
        controller.resetGhost();

        //assertEquals("00:00.000", controller.getCurrentGhost().getLapTimer().getLastLapTime());
        assertEquals(0, controller.getCurrentGhost().getCurrentStateIndex());
    }

    @Test
    void restartGhost() throws InterruptedException {
        controller.restartGhost();
        assertNull(controller.getBestGhost());

        controller.startLap();
        Thread.sleep(50);
        controller.stopLap();
        controller.compareAndSetLaps();

        controller.restartGhost();
        assertEquals(0, controller.getBestGhost().getCurrentStateIndex());
    }

    @Test
    void isLapRunning() {
        assertFalse(controller.isLapRunning());

        controller.startLap();
        assertTrue(controller.isLapRunning());

        controller.stopLap();
        assertFalse(controller.isLapRunning());
    }

    @Test
    void setCarPosition() {
        controller.setCarPosition(new Vector2(1 ,2));
        assertEquals(1, controller.getCar().getPositionX());
        assertEquals(2, controller.getCar().getPositionY());
    }

    @Test
    void getCarWidth() {
        assertEquals(1, controller.getCarWidth());
    }

    @Test
    void getCarHeight() {
        assertEquals(1, controller.getCarHeight());
    }
}
