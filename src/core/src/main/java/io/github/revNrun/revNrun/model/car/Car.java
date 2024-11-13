package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.*;

public class Car {
    private final int N_TIRES = 4;

    private int speed;
    private int maxSpeed;
    private int fuel;
    private Engine engine;
    private Chasis chasis;
    private Tires[] tires;
    private Suspension[] suspension;
    private Brakes[] brakes;
    private Floor floor;
    private Front front;
    private Back back;
    private Sides sides;

    public Car() {
        this.speed = 0;
        this.maxSpeed = 100;
        this.fuel = 100;
        this.engine = new Engine();
        this.chasis = new Chasis();

        this.tires = new Tires[N_TIRES];
        for (int i = 0; i < N_TIRES; i++) {
            this.tires[i] = new Tires();
        }

        this.suspension = new Suspension[N_TIRES];
        for (int i = 0; i < N_TIRES; i++) {
            this.suspension[i] = new Suspension();
        }

        this.brakes = new Brakes[N_TIRES];
        for (int i = 0; i < N_TIRES; i++) {
            this.brakes[i] = new Brakes();
        }

        this.floor = new Floor();
        this.front = new Front();
        this.back = new Back();
        this.sides = new Sides();
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
