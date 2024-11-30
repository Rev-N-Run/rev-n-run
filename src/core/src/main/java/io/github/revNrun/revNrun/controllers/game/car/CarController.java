package io.github.revNrun.revNrun.controllers.game.car;

import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.car.Car;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;

import java.util.HashMap;
import java.util.Map;

public class CarController {
    private InputHandler input;
    private Car car;

    public CarController(Car car, InputHandler input) {
        this.car = car;
        this.input = input;
    }

    public void execute(float delta) {
        if (input.isUpPressed()) {
            car.accelerate(delta);
            Map<CarSides, Float> sides = new HashMap<>();
            sides.put(CarSides.LEFT, 0.5f);
            sides.put(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
        } else if (input.isDownPressed()) {
            car.brakeAndReverse(delta);
            Map<CarSides, Float> sides = new HashMap<>();
            sides.put(CarSides.LEFT, 0.5f);
            sides.put(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
            car.degradeBrakes(delta);
        }

        if (input.isLeftPressed()) {
            car.moveLeft(delta);
        } else if (input.isRightPressed()) {
            car.moveRight(delta);
        }

        car.updatePosition(delta);
    }
}
