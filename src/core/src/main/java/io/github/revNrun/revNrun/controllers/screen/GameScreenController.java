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
import io.github.revNrun.revNrun.view.GameView;
import io.github.revNrun.revNrun.view.ViewUtils;

public class GameScreenController extends ScreenController {
    private final CarController carController;
    private final TrackController trackController;
    private final CameraController cameraController;
    //private final OrthographicCamera camera;
    private GameStatus gameStatus;

    public GameScreenController(Main game, SpriteBatch batch, Viewport viewport, OrthographicCamera camera) {
        super(game);
        //this.camera = camera;
        view = new GameView();
        carController = new CarController(CreateCar.createCar(), new InputHandler(new LibGDXInputHelper()));
        TrackController testTrackController = new TrackController();
        carController.setCarPosition(testTrackController.getStartPoint());
        while (testTrackController.updateCarInTrack(carController.getCarPosition()) == LapStatus.FATAL) {
            testTrackController = new TrackController();
        }
        trackController = testTrackController;
        cameraController = new CameraController();
        gameStatus = GameStatus.ONGOING;
    }

    @Override
    public void render(float delta) {

        // Clear screen
        ScreenUtils.clear(0, 0, 0, 1);

        // TODO set camera position according the car coordinates and appropriate dimensions (zoom)
        //camera.setToOrtho(false, ViewUtils.WORLD_WIDTH, ViewUtils.WORLD_HEIGHT);
        //camera.position.set(ViewUtils.WORLD_WIDTH / 2, ViewUtils.WORLD_HEIGHT / 2, 0);
        //camera.update();

        if (gameStatus == GameStatus.ONGOING) {
            if (!carController.isLapRunning()) {
                carController.startLap();
            }
            carController.handleInput(delta);
            LapStatus status = trackController.updateCarInTrack(carController.getCarPosition());
            switch (status) {
                case GOOD:
                    carController.recordGhost();
                    break;
                case COMPLETE:
                    carController.stopLap();
                    carController.compareAndSetLaps();
                    carController.restartGhost();
                    break;
                case INCOMPLETE:
                    carController.stopLap();
                    carController.restartGhost();
                    break;
            }
        }

        cameraController.calculateCameraPosition(carController.getCarPosition(), carController.getCarWidth(),
            carController.getCarHeight());
        cameraController.update();
        //trackController.draw();
        carController.draw();
    }
}
