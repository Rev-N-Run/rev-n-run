package io.github.revNrun.revNrun.controllers.game.track;

import io.github.revNrun.revNrun.model.checkpoints.Checkpoints;
import io.github.revNrun.revNrun.model.checkpoints.LapStatus;
import io.github.revNrun.revNrun.model.track.Track;
import io.github.revNrun.revNrun.model.vector.Vector2;
import io.github.revNrun.revNrun.view.TrackView;

import java.util.ArrayList;
import java.util.List;

public class TrackController {

    private final Track track;
    private final Checkpoints checkpoints;
    private TrackView trackView;
    private List<Vector2> leftBorder;
    private List<Vector2> rightBorder;
    private boolean carInTrack;
    private int lifeByCheckPoints;

    public TrackController() {
        track = new Track();
        checkpoints = new Checkpoints(track.getControlPoints());
        trackView = new TrackView();
        leftBorder = track.getLeftBorder();
        rightBorder = track.getRightBorder();
        carInTrack = true;
        lifeByCheckPoints = 100;
    }

    /**
     * @return Get the track start point.
     */
    public Vector2 getStartPoint() {
        return checkpoints.getStartPoint();
    }

    /**
     * This method updates the car data in relation with the track, i.e. if the track is out of the track,
     * the skipped checkpoints and if it has completed a lap.
     *
     * @param carPos The actual car position.
     * @return enum: COMPLETE if the car just completed a lap, INCOMPLETE if the car completed a lap illegally,
     * GOOD if the car hasn't complete the lap, but it's still alive, WRONG if it hasn't complete the lap and it's
     * dead by skipped checkpoints.
     */
    public LapStatus updateCarInTrack(Vector2 carPos) {
        carInTrack = checkpoints.isInsideCircuit(carPos);
        lifeByCheckPoints = checkpoints.remainingLife();

        LapStatus status = checkpoints.lapStatus();
        if (status == LapStatus.COMPLETE) {
            checkpoints.resetProgress();
        }
        return status;
    }

    /**
     * @return True if the car is inside the track, else false.
     */
    public boolean isCarInTrack() {
        return carInTrack;
    }

    /**
     * @return The remaining checkpoints available to skip (on a scale from 0 to 100)
     */
    public int getLifeByCheckPoints() {
        return lifeByCheckPoints;
    }

    /**
     * Calls the view class to draw the track assets
     */
    public void draw() {
        trackView.drawTrack(leftBorder, rightBorder);
        trackView.drawLifeByCheckPoints(lifeByCheckPoints);
    }

    // TEST METHODS

    public void setLeftBorder(List<Vector2> leftBorder) {
        this.leftBorder = leftBorder;
    }

    public void setRightBorder(List<Vector2> rightBorder) {
        this.rightBorder = rightBorder;
    }

    public void setLifeByCheckPoints(int lifeByCheckPoints) {
        this.lifeByCheckPoints = lifeByCheckPoints;
    }

    public void setCarInTrack(boolean carInTrack) {
        this.carInTrack = carInTrack;
    }

    public void setTrackView(TrackView trackView) {
        this.trackView = trackView;
    }
}
