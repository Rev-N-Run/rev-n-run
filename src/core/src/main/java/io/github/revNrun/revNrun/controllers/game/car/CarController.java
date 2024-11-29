package io.github.revNrun.revNrun.controllers.game.car;

import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.car.Car;

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
        } else if (input.isDownPressed()) {
            car.brakeAndReverse(delta);
        }

        if (input.isLeftPressed()) {
            car.moveLeft(delta);
        } else if (input.isRightPressed()) {
            car.moveRight(delta);
        }

        car.updatePosition(delta);
    }
}
