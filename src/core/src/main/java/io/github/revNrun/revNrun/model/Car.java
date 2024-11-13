package io.github.revNrun.revNrun.model;

public class Car {
    private int speed;
    private int maxSpeed;
    private int fuel;
    private Engine engine;
    private Chasis chasis;
    private Tires tires;
    private Suspension suspension;
    private Brakes brakes;
    private Floor floor;
    private Front front;
    private Back back;
    private Sides sides;

    public Car() {

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

    public Tires getTires() {
        return tires;
    }

    public Suspension getSuspension() {
        return suspension;
    }

    public Brakes getBrakes() {
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
