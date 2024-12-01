package io.github.revNrun.revNrun.controllers.game;

import com.badlogic.gdx.Game;
import io.github.revNrun.revNrun.controllers.screen.GameStatus;
import io.github.revNrun.revNrun.view.CountdownView;
import com.badlogic.gdx.math.Interpolation;

public class CountdownController {
    private final CountdownView countdownView;
    private float totalTime = 0;
    private int currentNumber = 3;
    private float fadeTime = 0.3f; // Tiempo de fade más rápido
    private float zoomTime = 0.7f; // Tiempo de zoom
    private boolean countdownFinished = false;
    private GameStatus status = GameStatus.STOP;

    public CountdownController() {
        countdownView = new CountdownView();
    }

    public GameStatus count(float delta) {
        totalTime += delta;

        if (!countdownFinished) {
            float cycleTime = totalTime % 1.0f;

            if (totalTime < 1.0f) {
                handleNumberTransition(3, cycleTime);
            } else if (totalTime < 2.0f) {
                handleNumberTransition(2, cycleTime);
            } else if (totalTime < 3.0f) {
                handleNumberTransition(1, cycleTime);
            } else if (totalTime < 4.0f) {
                countdownView.setShowRun(true);
                countdownView.setTitleAlpha(0);

                float runProgress = (totalTime - 3.0f);
                float runScale = Interpolation.swingOut.apply(0.5f, 1.5f, runProgress);
                countdownView.setScale(runScale);
                countdownView.setRunAlpha(1f);
                status = GameStatus.START;
            } else if (totalTime < 5.0f) {
                float alpha = 1 - (totalTime - 4.0f) / fadeTime;
                countdownView.setRunAlpha(Math.max(0, alpha));
            } else {
                countdownFinished = true;
                return GameStatus.ONGOING;
            }
        }

        countdownView.draw(currentNumber);
        return status;
    }

    private void handleNumberTransition(int number, float cycleTime) {
        currentNumber = number;

        if (cycleTime < zoomTime) {
            float progress = cycleTime / zoomTime;
            float scale = Interpolation.swingOut.apply(0.5f, 1.5f, progress);
            countdownView.setScale(scale);
            countdownView.setNumberAlpha(1f);
        } else {
            float fadeProgress = (cycleTime - zoomTime) / fadeTime;
            countdownView.setNumberAlpha(1 - fadeProgress);
            countdownView.setScale(1.5f);
        }
    }
}
