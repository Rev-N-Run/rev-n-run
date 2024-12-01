package io.github.revNrun.revNrun.controllers.game.car;

import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.model.car.Car;
import io.github.revNrun.revNrun.model.car.components.enums.CarSides;
import io.github.revNrun.revNrun.model.ghost_car.GhostCar;
import io.github.revNrun.revNrun.model.lap_timer.LapTimer;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.car.CarView;
import io.github.revNrun.revNrun.view.ViewUtils;
import io.github.revNrun.revNrun.view.car.ICarView;

import java.util.HashMap;
import java.util.Map;

public class CarController {
    private InputHandler input;
    private Car car;
    private Map<CarSides, Float> sides;
    private GhostCar bestGhost;
    private GhostCar ghost;
    private LapTimer currentLap;
    private ICarView carView;
    private Viewport viewport = ViewUtils.getViewport();

    public CarController(Car car, InputHandler input, ICarView carView) {
        this.car = car;
        this.input = input;
        this.sides = new HashMap<>();
        sides.put(CarSides.LEFT, 0f);
        sides.put(CarSides.RIGHT, 0f);
        ghost = new GhostCar();
        bestGhost = null;
        currentLap = new LapTimer();
        this.carView = carView;
        carView.create(car.getPosition(), car.getAngle());
    }

    public String getCurrentLap() {
        return currentLap.getCurrentLapTime();
    }

    public String getBestGhostLap() {
        if (bestGhost == null) {
            return "00:00.000";
        }
        return bestGhost.getLapTimer().getLastLapTime();
    }

    public void handleInput(float delta) {
        if (input.isUpPressed()) {
            car.accelerate(delta);
            sides.replace(CarSides.LEFT, 0.5f);
            sides.replace(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
            car.degradeSuspension(delta, sides);
            car.degradeEngine(delta);
        } else if (input.isDownPressed()) {
            car.brakeAndReverse(delta);
            sides.replace(CarSides.LEFT, 0.5f);
            sides.replace(CarSides.RIGHT, 0.5f);
            car.degradeTires(delta, sides);
            car.degradeSuspension(delta, sides);
            car.degradeBrakes(delta);
        }

        if (input.isLeftPressed()) {
            car.moveLeft(delta);
            sides.replace(CarSides.LEFT, 0.7f);
            sides.replace(CarSides.RIGHT, 0.3f);
            car.degradeTires(delta, sides);
            car.degradeSuspension(delta, sides);
        } else if (input.isRightPressed()) {
            car.moveRight(delta);
            sides.replace(CarSides.LEFT, 0.3f);
            sides.replace(CarSides.RIGHT, 0.7f);
            car.degradeTires(delta, sides);
            car.degradeSuspension(delta, sides);
        }

        if (!input.isUpPressed() && !input.isDownPressed()) {
            car.naturalSlowDown(delta);
        }

        updatePosition(delta);
    }

    private void updatePosition(float delta) {
        Vector2 pos = new Vector2(car.getPosition());
        if (car.getPositionX() < 0) {
            pos.setX(0);
        } else if (car.getPositionX() > ViewUtils.WORLD_WIDTH - carView.getCarWidth()) {
            pos.setX(ViewUtils.WORLD_WIDTH - carView.getCarWidth());
        }

        if (car.getPositionY() < 0 ) {
            pos.setY(0);
        } else if (car.getPositionY() > ViewUtils.WORLD_HEIGHT - carView.getCarHeight()) {
            pos.setY(ViewUtils.WORLD_HEIGHT - carView.getCarHeight());
        }
        car.setPosition(pos);
        car.updatePosition(delta);
    }

    public void recordGhost() {
        ghost.recordState(car.getPosition(), car.getAngle(), 0);
    }

    public void compareAndSetLaps() {
        if (bestGhost == null) {
            bestGhost = new GhostCar(ghost);
            return;
        }
        LapTimer bestLap = bestGhost.getLapTimer();
        if (currentLap.isFasterThan(bestLap)) {
            bestGhost = new GhostCar(ghost);
        }
    }

    public void drawCar() {
        carView.draw(car.getPosition(), car.getAngle());
    }

    public void drawGhost() {
        if (bestGhost == null) {
            return;
        }
        carView.drawGhost(new Vector2(bestGhost.getPositionX(), bestGhost.getPositionY()), bestGhost.getAngle());
        bestGhost.nextFrame();
    }

    public Car getCar() {
        return car;
    }

    public Vector2 getCarPosition() {
        return car.getPosition();
    }

    public GhostCar getCurrentGhost() {
        return ghost;
    }

    public GhostCar getBestGhost() {
        return bestGhost;
    }

    public void resetGhost() {
        ghost.reset();
    }

    public void restartGhost() {
        if (bestGhost == null) {
            return;
        }
        bestGhost.restart();
    }

    public void setBestGhost(GhostCar bestGhost) {
        this.bestGhost = bestGhost;
    }

    public boolean isLapRunning() {
        return currentLap.isRunning();
    }

    public void startLap() {
        currentLap.start();
    }

    public void stopLap() {
        currentLap.stop();
        ghost.setTimer(new LapTimer(currentLap));
    }

    public void setCarPosition(Vector2 position) {
        car.setPosition(position);
    }

    public float getCarWidth() {
        return carView.getCarWidth();
    }

    public float getCarHeight() {
        return carView.getCarHeight();
    }
}
