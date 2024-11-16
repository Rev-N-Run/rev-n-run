package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.*;
import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.Map;

public class Car {
    private static final int N_TIRES = 4;
    private static final int MIN_FUEL = 0;

    private int positionX = 0;
    private int positionY = 0;
    private int speed = 0;
    private int maxSpeed = 100;
    private int maxFuel = 100;
    private int fuel = 100;
    private float weight = 0;
    private Engine engine;
    private Chassis chassis;
    private Tires[] tires;
    private Suspension[] suspension;
    private Brakes[] brakes;
    private Floor floor;
    private Front front;
    private Back back;
    private Sides sides;
    private float brakeBalance = .5f;

    public Car(Engine engine, Chassis chassis, Tires[] tires, Suspension[] suspension, Brakes[] brakes,
               Floor floor, Front front, Back back, Sides sides, int fuel) {
        validateComponents(engine, chassis, tires, suspension, brakes, floor, front, back, sides);
        validateFuel(fuel);

        this.engine = engine;
        this.chassis = chassis;
        this.tires = tires.clone();
        this.suspension = suspension.clone();
        this.brakes = brakes.clone();
        this.floor = floor;
        this.front = front;
        this.back = back;
        this.sides = sides;
        this.fuel = fuel;
    }

    private void validateComponents(Engine engine, Chassis chassis, Tires[] tires, Suspension[] suspension, Brakes[] brakes,
                                    Floor floor, Front front, Back back, Sides sides) {
        if (engine == null || chassis == null || tires == null || suspension == null || brakes == null
            || floor == null || front == null || back == null || sides == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        if (tires.length != N_TIRES) {
            throw new IllegalArgumentException("Car must have exactly " + N_TIRES + " tires");
        }
        if (suspension.length != N_TIRES) {
            throw new IllegalArgumentException("Car must have exactly " + N_TIRES + " suspension units");
        }
        if (brakes.length != N_TIRES) {
            throw new IllegalArgumentException("Car must have exactly " + N_TIRES + " brakes");
        }

        for (AbstractComponent component : tires) {
            if (component == null) {
                throw new IllegalArgumentException("Tire can't be null");
            }
        }

        for (AbstractComponent component : brakes) {
            if (component == null) {
                throw new IllegalArgumentException("Brake can't be null");
            }
        }

        for (AbstractComponent component : suspension) {
            if (component == null) {
                throw new IllegalArgumentException("Suspension can't be null");
            }
        }
    }

    private void validateFuel(int fuel) {
        if (fuel < MIN_FUEL || fuel > maxFuel) {
            throw new IllegalArgumentException("Fuel must be between " + MIN_FUEL + " and " + maxFuel);
        }
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

    private WheelMountedComponent getComponentFL(WheelMountedComponent[] components) {
        return getComponentByPosition(components, CarAxis.FRONT, CarSides.LEFT);
    }

    private WheelMountedComponent getComponentFR(WheelMountedComponent[] components) {
        return getComponentByPosition(components, CarAxis.FRONT, CarSides.RIGHT);
    }

    private WheelMountedComponent getComponentRL(WheelMountedComponent[] components) {
        return getComponentByPosition(components, CarAxis.REAR, CarSides.LEFT);
    }

    private WheelMountedComponent getComponentRR(WheelMountedComponent[] components) {
        return getComponentByPosition(components, CarAxis.REAR, CarSides.RIGHT);
    }

    private void degradeBySide(float delta, WheelMountedComponent[] components, Map<CarSides, Float> sides) {
        WheelMountedComponent componentFL = getComponentFL(components);
        WheelMountedComponent componentFR = getComponentFR(components);
        WheelMountedComponent componentRL = getComponentRL(components);
        WheelMountedComponent componentRR = getComponentRR(components);

        assert componentFL != null;
        assert componentFR != null;
        assert componentRL != null;
        assert componentRR != null;

        componentFL.degrade(delta, sides.get(CarSides.LEFT));
        componentFR.degrade(delta, sides.get(CarSides.RIGHT));
        componentRL.degrade(delta, sides.get(CarSides.LEFT));
        componentRR.degrade(delta, sides.get(CarSides.RIGHT));
    }

    private void degradeByImpactWheelMounted(float percentage, WheelMountedComponent[] components) {
        for (WheelMountedComponent component : components) {
            component.degradeByImpact(percentage);
        }
    }

    public void degradeEngine(float delta) {
        engine.degrade(delta);
    }

    public void degradeChassis(float delta) {
        engine.degrade(delta);
    }

    public void degradeTires(float delta, Map<CarSides, Float> sides) {
        degradeBySide(delta, tires, sides);
    }

    public void degradeSuspension(float delta, Map<CarSides, Float> sides) {
        degradeBySide(delta, suspension, sides);
    }

    public void degradeBrakes(float delta) {
        WheelMountedComponent brakeFL = getComponentFL(brakes);
        WheelMountedComponent brakeFR = getComponentFR(brakes);
        WheelMountedComponent brakeRL = getComponentRL(brakes);
        WheelMountedComponent brakeRR = getComponentRR(brakes);

        assert brakeFL != null;
        assert brakeFR != null;
        assert brakeRL != null;
        assert brakeRR != null;

        brakeFL.degrade(delta,brakeBalance - .05f);
        brakeFR.degrade(delta,brakeBalance - .05f);
        brakeRL.degrade(delta,brakeBalance + .05f);
        brakeRR.degrade(delta,brakeBalance + .05f);
    }

    public void degradeEngineByImpact(float value) {
        engine.degradeByImpact(value);
    }

    public void degradeChassisByImpact(float value) {
        engine.degradeByImpact(value);
    }

    public void degradeTiresByImpact(float percentage) {
        degradeByImpactWheelMounted(percentage, tires);
    }

    public void degradeSuspensionByImpact(float percentage) {
        degradeByImpactWheelMounted(percentage, suspension);
    }

    public void degradeBrakesByImpact(float percentage) {
        degradeByImpactWheelMounted(percentage, brakes);
    }

    public void degradeFloorByImpact(float value) {
        floor.degradeByImpact(value);
    }

    public void degradeFrontByImpact(float value) {
        front.degradeByImpact(value);
    }

    public void degradeBackByImpact(float value) {
        back.degradeByImpact(value);
    }

    public void degradeSidesByImpact(float value) {
        sides.degradeByImpact(value);
    }

    public int getPositionX() {
        return positionX;
    }

    public int getPositionY() {
        return positionY;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxSpeed() {
        return maxSpeed;
    }

    public int getFuelLevel() {
        return fuel;
    }

    public Engine getEngine() {
        return engine;
    }

    public Chassis getChassis() {
        return chassis;
    }

    public Tires[] getTires() {
        return tires;
    }

    public Suspension[] getSuspension() {
        return suspension;
    }

    public Brakes[] getBrakes() {
        return brakes;
    }

    public Floor getFloor() {
        return floor;
    }

    public Front getFront() {
        return front;
    }

    public Back getBack() {
        return back;
    }

    public Sides getSides() {
        return sides;
    }

    public float getWeight() {
        return weight;
    }
}
