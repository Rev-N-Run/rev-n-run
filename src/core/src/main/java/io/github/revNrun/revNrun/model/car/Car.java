package io.github.revNrun.revNrun.model.car;

import io.github.revNrun.revNrun.model.car.components.*;
import io.github.revNrun.revNrun.model.car.components.enums.CarAxis;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.car.components.enums.EffectType;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Car {
    private static final int N_TIRES = 4;
    private static final int MIN_FUEL = 0;

    private int positionX = 0;
    private int positionY = 0;
    private float speed = 0;
    private float maxSpeed = 100;
    private int maxFuel = 100;
    private float fuel = 100;
    private float weight;
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
    private float brakePower = 0;
    private float acceleration;
    private float tireGrip = 0;
    private float optionalGrip = 0;
    private float maxReverseSpeed;
    private float reverseAcceleration;
    private float angle = 0;

    public Car(Engine engine, Chassis chassis, Tires[] tires, Suspension[] suspension, Brakes[] brakes,
               Floor floor, Front front, Back back, Sides sides, float fuel) {
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

        setAttributes();
    }

    public void updatePosition(float delta) {
        if (speed == 0) {
            return;
        }

        // Calculate velocity components
        float velocityX = (float) (speed * Math.cos(angle));
        float velocityY = (float) (speed * Math.sin(angle));

        // Update position
        positionX += velocityX * delta;
        positionY += velocityY * delta;
    }

    public void accelerate(float delta) {
        float newSpeed = speed + acceleration * delta;
        speed = Math.min(newSpeed, maxSpeed);
    }

    public void brakeAndReverse(float delta) {
        float newSpeed = speed - reverseAcceleration * delta;
        speed = Math.max(newSpeed, maxReverseSpeed);
    }

    public void moveRight(float delta) {
        if (tireGrip == 0) {
            return;
        }

        float maxTurnSpeed = maxSpeed - 20;
        float turnRate = (tireGrip + optionalGrip) * 0.01f * delta;

        if (speed > maxTurnSpeed) {
            speed = Math.max(maxTurnSpeed, speed - (speed - maxTurnSpeed) * delta);
        }

        angle += turnRate;
    }

    public void moveLeft(float delta) {
        if (tireGrip == 0) {
            return;
        }

        float maxTurnSpeed = maxSpeed - 20;
        float turnRate = (tireGrip + optionalGrip) * 0.01f * delta;

        if (speed > maxTurnSpeed) {
            speed = Math.max(maxTurnSpeed, speed - (speed - maxTurnSpeed) * delta);
        }

        angle -= turnRate;
    }

    private void degradeTireGrip() {
        tireGrip = 0;
        List<Effect> effects = getWheelMountedComponentEffects(tires);
        for (Effect effect : effects) {
            if (effect.getEffect() == EffectType.GRIP) {
                tireGrip += effect.getValue();
            }
        }
    }

    private float getWheelMountedComponentsWeight(WheelMountedComponent[] components) {
        float weight = 0;

        for (Component component : components) {
            weight += component.getWeight();
        }

        return weight;
    }

    private float getWeightSum() {
        return engine.getWeight() + chassis.getWeight() + getWheelMountedComponentsWeight(tires) +
            getWheelMountedComponentsWeight(suspension) + getWheelMountedComponentsWeight(brakes) +
            floor.getWeight() + front.getWeight() + back.getWeight() + sides.getWeight();
    }

    private List<Effect> getWheelMountedComponentEffects(WheelMountedComponent[] components) {
        ArrayList<Effect> effects = new ArrayList<>();

        for (WheelMountedComponent component : components) {
            effects.addAll(component.getEffects());
        }

        return effects;
    }

    private void setAttributes() {
        weight = getWeightSum();
        List<Effect> effects = new ArrayList<>();
        effects.addAll(engine.getEffects());
        effects.addAll(chassis.getEffects());
        effects.addAll(getWheelMountedComponentEffects(suspension));
        effects.addAll(getWheelMountedComponentEffects(brakes));
        effects.addAll(floor.getEffects());
        effects.addAll(front.getEffects());
        effects.addAll(back.getEffects());
        effects.addAll(sides.getEffects());

        List<Effect> tiresEffects = getWheelMountedComponentEffects(tires);
        List<Effect> effectsToAdd = new ArrayList<>(tiresEffects);
        for (Effect effect : tiresEffects) {
            if (effect.getEffect() == EffectType.GRIP) {
                tireGrip += effect.getValue();
            } else  {
                effectsToAdd.add(effect);
            }
        }

        effects.addAll(effectsToAdd);

        for (Effect effect : effects) {
            switch (effect.getEffect()) {
                case GRIP:
                    optionalGrip += effect.getValue();
                    break;
                case MAX_SPEED:
                    maxSpeed += effect.getValue();
                    break;
                case ACCELERATION:
                    acceleration += effect.getValue();
                    break;
                case BRAKE:
                    brakePower += effect.getValue();
                    break;
            }
        }

        maxReverseSpeed = -maxSpeed / 4f;
        reverseAcceleration = acceleration / 4f;
    }

    public void degradeEngine(float delta) {
        engine.degrade(delta);
    }

    public void degradeChassis(float delta) {
        chassis.degrade(delta);
    }

    public void degradeTires(float delta, Map<CarSides, Float> sides) {
        degradeBySide(delta, tires, sides);
        degradeTireGrip();
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
        chassis.degradeByImpact(value);
    }

    public void degradeTiresByImpact(float percentage) {
        degradeByImpactWheelMounted(percentage, tires);
        degradeTireGrip();
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

    public float getSpeed() {
        return speed;
    }

    public float getMaxSpeed() {
        return maxSpeed;
    }

    public float getFuelLevel() {
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

    public float getBrakePower() {
        return brakePower;
    }

    public float getAcceleration() {
        return acceleration;
    }

    public float getTireGrip() {
        return tireGrip;
    }

    public float getReverseAcceleration() {
        return reverseAcceleration;
    }

    public float getMaxReverseSpeed() {
        return maxReverseSpeed;
    }

    public float getAngle() {
        return angle;
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

    private void validateFuel(float fuel) {
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
}
