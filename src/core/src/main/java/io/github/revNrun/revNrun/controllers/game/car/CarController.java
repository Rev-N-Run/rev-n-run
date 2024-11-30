package io.github.revNrun.revNrun.controllers.game.car;

import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.car.Car;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.ghost_car.GhostCar;

import java.util.HashMap;
import java.util.Map;

public class CarController {
    private InputHandler input;
    private Car car;
    Map<CarSides, Float> sides;
    GhostCar bestGhost;
    GhostCar ghost;


    public CarController(Car car, InputHandler input) {
        this.car = car;
        this.input = input;
        this.sides = new HashMap<>();
        sides.put(CarSides.LEFT, 0f);
        sides.put(CarSides.RIGHT, 0f);
        ghost = new GhostCar();
    }

    public void execute(float delta) {
        if (input.isUpPressed()) {
            car.accelerate(delta);
            sides.replace(CarSides.LEFT, 0.5f);
            sides.replace(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
            System.out.println("up");
        } else if (input.isDownPressed()) {
            car.brakeAndReverse(delta);
            sides.replace(CarSides.LEFT, 0.5f);
            sides.replace(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
            car.degradeBrakes(delta);
            System.out.println("down");
        }

        if (input.isLeftPressed()) {
            car.moveLeft(delta);
            sides.replace(CarSides.LEFT, 0.7f);
            sides.replace(CarSides.RIGHT, 0.3f);
            car.degradeTires(delta, sides);
            System.out.println("left");
        } else if (input.isRightPressed()) {
            car.moveRight(delta);
            sides.replace(CarSides.LEFT, 0.3f);
            sides.replace(CarSides.RIGHT, 0.7f);
            car.degradeTires(delta, sides);
            System.out.println("right");
        }

        car.updatePosition(delta);
        ghost.recordState(car.getPosition(), car.getAngle(), 0);
    }

    public Car getCar() {
        return car;
    }

    public GhostCar getCurrentGhost() {
        return ghost;
    }

    public GhostCar getBestGhost() {
        return bestGhost;
    }

    public void restartGhost() {
        ghost.reset();
    }

    public void setBestGhost(GhostCar bestGhost) {
        this.bestGhost = bestGhost;
    }
}
