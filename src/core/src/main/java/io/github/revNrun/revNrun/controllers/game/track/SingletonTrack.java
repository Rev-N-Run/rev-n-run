package io.github.revNrun.revNrun.controllers.game.track;

import io.github.revNrun.revNrun.model.track.Track;
import io.github.revNrun.revNrun.view.ViewUtils;

public class SingletonTrack {
    private static Track instance = null;

    public static Track getTrack() {
        if (instance == null) {
            instance = new Track();
            ViewUtils.setWorldDimensions(instance.getTrackRadius());
        }
        return instance;
    }
}
