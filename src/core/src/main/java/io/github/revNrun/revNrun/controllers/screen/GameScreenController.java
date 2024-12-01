package io.github.revNrun.revNrun.controllers.screen;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.viewport.Viewport;
import io.github.revNrun.revNrun.Main;
import io.github.revNrun.revNrun.controllers.camera.CameraController;
import io.github.revNrun.revNrun.controllers.game.car.CarController;
import io.github.revNrun.revNrun.controllers.game.track.TrackController;
import io.github.revNrun.revNrun.controllers.input.InputHandler;
import io.github.revNrun.revNrun.controllers.input.LibGDXInputHelper;
import io.github.revNrun.revNrun.model.CreateCar;
import io.github.revNrun.revNrun.model.checkpoints.LapStatus;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.GameView;

public class GameScreenController extends ScreenController {
    private final CarController carController;
    private final TrackController trackController;
    private final CameraController cameraController;
    //private final OrthographicCamera camera;
    private GameStatus gameStatus;
    private LapStatus status;
    private boolean wasInTrack = true;
    private boolean hasStartedLap = false;

    public GameScreenController(Main game, SpriteBatch batch, Viewport viewport, OrthographicCamera camera) {
        super(game);
        view = new GameView();
        carController = new CarController(CreateCar.createCar(), new InputHandler(new LibGDXInputHelper()));
        trackController = new TrackController();
        carController.setCarPosition(trackController.getStartPoint());
        cameraController = new CameraController();
        gameStatus = GameStatus.ONGOING;
    }

    @Override
    public void render(float delta) {
        if (gameStatus != GameStatus.ONGOING) {
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
            hasStartedLap = true;
            return;
        }

        if (!isInTrack && wasInTrack) {
            carController.stopLap();
            carController.restartGhost();
            wasInTrack = false;
            return;
        }

        if (isInTrack && carController.isLapRunning()) {
            carController.recordGhost();

            if (isInStart && hasStartedLap) {
                carController.stopLap();
                carController.compareAndSetLaps();
                carController.restartGhost();
                hasStartedLap = false;
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
        carController.draw();
    }
}
