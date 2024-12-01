package io.github.revNrun.revNrun.model;

import io.github.revNrun.revNrun.model.car.Car;
import io.github.revNrun.revNrun.model.car.components.*;
import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CreateCar {
    static List<Effect> engineEffects;
    static List<Effect> chassisEffects;
    static List<Effect> floorEffects;
    static List<Effect> frontEffects;
    static List<Effect> backEffects;
    static List<Effect> sidesEffects;
    static List<Effect> tiresEffects;
    static List<Effect> suspensionEffects;
    static List<Effect> brakesEffects;
    static Engine engineWithEffects;
    static Chassis chassisWithEffects;
    static Tires[] tiresWithEffects;
    static Suspension[] suspensionsWithEffects;
    static Brakes[] brakesWithEffects;
    static Floor floorWithEffects;
    static Front frontWithEffects;
    static Back backWithEffects;
    static Sides sidesWithEffects;

    public static Car createCar() {
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
        return new Car(engineWithEffects, chassisWithEffects, tiresWithEffects, suspensionsWithEffects,
            brakesWithEffects, floorWithEffects, frontWithEffects, backWithEffects, sidesWithEffects, fuel);
    }

    private static Tires[] getTiresWithEffects() {
        Effect grip10 = new Effect(EffectType.GRIP, 10);
        Effect brake3 = new Effect(EffectType.BRAKE, 3);
        Effect maxSpeedMinus2 = new Effect(EffectType.MAX_SPEED, -2);
        Effect acceleration1 = new Effect(EffectType.ACCELERATION, 1);

        tiresEffects = new ArrayList<>(Arrays.asList(grip10, brake3, maxSpeedMinus2, acceleration1));

        Tires tireFL = new Tires("tireFL", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.LEFT, .9f);
        Tires tireFR = new Tires("tireFR", 20f, 100, 100, tiresEffects, CarAxis.FRONT, CarSides.RIGHT, .9f);
        Tires tireRL = new Tires("tireRL", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.LEFT, .9f);
        Tires tireRR = new Tires("tireRR", 20f, 100, 100, tiresEffects, CarAxis.REAR, CarSides.RIGHT, .9f);

        return new Tires[] {tireFL, tireFR, tireRL, tireRR};
    }

    private static Suspension[] getSuspensionsWithEffects() {
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

    private static Brakes[] getBrakesWithEffects() {
        Effect brake15 = new Effect(EffectType.BRAKE, 15);
        brakesEffects = new ArrayList<>(Collections.singletonList(brake15));

        Brakes brakeFL = new Brakes("brakeFL", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.LEFT, .1f);
        Brakes brakeFR = new Brakes("brakeFR", 5f, 100, 100, brakesEffects, CarAxis.FRONT, CarSides.RIGHT, .1f);
        Brakes brakeRL = new Brakes("brakeRL", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.LEFT, .1f);
        Brakes brakeRR = new Brakes("brakeRR", 5f, 100, 100, brakesEffects, CarAxis.REAR, CarSides.RIGHT, .1f);

        return new Brakes[] {brakeFL, brakeFR, brakeRL, brakeRR};
    }
}
