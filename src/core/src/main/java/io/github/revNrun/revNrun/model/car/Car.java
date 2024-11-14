package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.*;

public class Car {
    private static final int N_TIRES = 4;

    private int positionX = 0;
    private int positionY = 0;
    private int speed = 0;
    private int maxSpeed = 100;
    private int maxFuel = 100;
    private int fuel = 100;
    private int weight = 0;
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
}
