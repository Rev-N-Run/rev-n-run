package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.controllers.camera.CameraController;
import io.github.revNrun.revNrun.controllers.game.CountdownController;
import io.github.revNrun.revNrun.controllers.game.car.CarController;
import io.github.revNrun.revNrun.controllers.game.track.TrackController;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.CreateCar;
import io.github.revNrun.revNrun.model.checkpoints.LapStatus;
import io.github.revNrun.revNrun.view.GameView;
import io.github.revNrun.revNrun.view.TimerView;

public class GameScreenController extends ScreenController {
    private final CarController carController;
    private final TrackController trackController;
    private final CameraController cameraController;
    private final TimerView timerView;
    private final CountdownController countdownController;
    private GameStatus gameStatus;
    private boolean wasInTrack = true;
    private boolean hasStartedLap = false;
    private boolean hasLeftStart = false;

    public GameScreenController(Main game) {
        super(game);
        view = new GameView();
        carController = new CarController(CreateCar.createCar(), new InputHandler(new LibGDXInputHelper()));
        trackController = new TrackController();
        carController.setCarPosition(trackController.getStartPoint());
        cameraController = new CameraController();
        gameStatus = GameStatus.STOP;
        timerView = new TimerView();
        countdownController = new CountdownController();
    }

    @Override
    public void render(float delta) {
        if (gameStatus == GameStatus.STOP) {
            updateCameraAndRender();
            if (countdownController.count(delta)) {
                gameStatus = GameStatus.ONGOING;
            }
            return;
        }

        carController.handleInput(delta);

        updateGameState();

        updateCameraAndRender();
    }

    private void updateGameState() {
        trackController.updateCarInTrack(carController.getCarPosition());
        boolean isInTrack = trackController.isCarInTrack();
        boolean isInStart = trackController.isCarInStart(carController.getCarPosition());

        if (isInStart && !hasStartedLap && isInTrack) {
            carController.startLap();
            carController.resetGhost();
            carController.restartGhost();
            hasStartedLap = true;
            hasLeftStart = false;
            return;
        }

        if (!isInStart && hasStartedLap && !hasLeftStart) {
            hasLeftStart = true;
        }

        if (!isInTrack && wasInTrack) {
            carController.stopLap();
            carController.resetGhost();
            hasStartedLap = false;
            hasLeftStart = false;
            wasInTrack = false;
            return;
        }

        if (isInTrack && carController.isLapRunning()) {
            carController.recordGhost();

            if (isInStart && hasLeftStart) {
                carController.stopLap();
                carController.compareAndSetLaps();
                hasStartedLap = false;
                hasLeftStart = false;
            }
        }

        wasInTrack = isInTrack;
    }

    private void updateCameraAndRender() {
        cameraController.calculateCameraPosition(
            carController.getCarPosition(),
            carController.getCarWidth(),
            carController.getCarHeight()
        );

        cameraController.update();
        trackController.draw();
        carController.drawGhost();
        carController.drawCar();
        timerView.drawCurrentTime(carController.getCurrentLap(), carController.getBestGhostLap());
    }
}
