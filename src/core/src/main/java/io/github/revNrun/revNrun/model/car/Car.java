package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.*;

import java.util.Map;

public class Car {
    private static final int N_TIRES = 4;
    private static final int N_BRAKES = N_TIRES / 2;
    private static final int MIN_FUEL = 0;

    private int positionX = 0;
    private int positionY = 0;
    private int speed = 0;
    private int maxSpeed = 100;
    private int maxFuel = 100;
    private int fuel = 100;
    private float weight = 0;
    private Engine engine;
    private Chasis chasis;
    private Tires[] tires;
    private Suspension[] suspension;
    private Brakes[] brakes;
    private Floor floor;
    private Front front;
    private Back back;
    private Sides sides;

    public Car(Engine engine, Chasis chasis, Tires[] tires, Suspension[] suspension, Brakes[] brakes,
               Floor floor, Front front, Back back, Sides sides, int fuel) {
        validateComponents(engine, chasis, tires, suspension, brakes, floor, front, back, sides);
        validateFuel(fuel);

        this.engine = engine;
        this.chasis = chasis;
        this.tires = tires.clone();
        this.suspension = suspension.clone();
        this.brakes = brakes.clone();
        this.floor = floor;
        this.front = front;
        this.back = back;
        this.sides = sides;
        this.fuel = fuel;
    }

    private void validateComponents(Engine engine, Chasis chasis, Tires[] tires, Suspension[] suspension, Brakes[] brakes,
                                    Floor floor, Front front, Back back, Sides sides) {
        if (engine == null || chasis == null || tires == null || suspension == null || brakes == null
            || floor == null || front == null || back == null || sides == null) {
            throw new IllegalArgumentException("Arguments can't be null");
        }
        if (tires.length != N_TIRES) {
            throw new IllegalArgumentException("Car must have exactly " + N_TIRES + " tires");
        }
        if (suspension.length != N_TIRES) {
            throw new IllegalArgumentException("Car must have exactly " + N_TIRES + " suspension units");
        }
        if (brakes.length != N_BRAKES) {
            throw new IllegalArgumentException("Car must have exactly " + N_BRAKES + " brakes");
        }

        for (Component component : tires) {
            if (component == null) {
                throw new IllegalArgumentException("Tire can't be null");
            }
        }

        for (Component component : brakes) {
            if (component == null) {
                throw new IllegalArgumentException("Brake can't be null");
            }
        }

        for (Component component : suspension) {
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


    public void degradeEngine() {

    }

    public void degradeChasis() {

    }

    public void degradeTires(Map<CarSides, Float> sides) {
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

        assert tireFL != null;
        assert tireFR != null;
        assert tireRL != null;
        assert tireRR != null;
        tireFL.degrade(sides.get(CarSides.LEFT));
        tireFR.degrade(sides.get(CarSides.RIGHT));
        tireRL.degrade(sides.get(CarSides.LEFT));
        tireRR.degrade(sides.get(CarSides.RIGHT));
    }

    public void degradeSuspension(Map<CarSides, Float> sides) {

    }

    public void degradeBrakes() {

    }

    public void degradeFloor() {

    }

    public void degradeFront() {

    }

    public void degradeBack() {

    }

    public void degradeSides() {

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

    public Chasis getChasis() {
        return chasis;
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
