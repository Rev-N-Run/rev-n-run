package io.github.revNrun.revNrun.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class TimerView {
    private BitmapFont fontCurrentLap;
    private BitmapFont fontBestLap;
    private SpriteBatch batch;
    private final float PADDING = 10f;
    private Viewport viewport;

    public TimerView() {
        this.batch = new SpriteBatch();
        this.viewport = new ScreenViewport();
        fontCurrentLap = new BitmapFont();
        fontCurrentLap.setColor(Color.BLUE);
        fontCurrentLap.getData().setScale(2);
        fontBestLap = new BitmapFont();
        fontBestLap.setColor(Color.GREEN);
        fontBestLap.getData().setScale(2);
        createFont();
    }

    private void createFont() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
            Gdx.files.internal("GeistMonoNerdFontPropo-Medium.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = 18;
        parameter.color = Color.CYAN;
        parameter.borderWidth = 2;
        parameter.borderColor = Color.BLACK;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.shadowColor = new Color(0, 0, 0, 0.75f);

        fontCurrentLap = generator.generateFont(parameter);

        FreeTypeFontGenerator.FreeTypeFontParameter parameter1 = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter1.size = 18;
        parameter1.color = Color.GREEN;
        parameter1.borderWidth = 2;
        parameter1.borderColor = Color.BLACK;
        parameter1.shadowOffsetX = 2;
        parameter1.shadowOffsetY = 2;
        parameter1.shadowColor = new Color(0, 0, 0, 0.75f);

        fontBestLap = generator.generateFont(parameter1);

        generator.dispose();
    }

    public void drawCurrentTime(String currentTime, String bestTime) {
        bestTime = "Best lap: " + bestTime;
        currentTime = "Current lap: " + currentTime;
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();
        fontCurrentLap.draw(batch, currentTime,
            Gdx.graphics.getWidth() - 300 - PADDING,
            Gdx.graphics.getHeight() - PADDING);
        fontBestLap.draw(batch, bestTime,
            Gdx.graphics.getWidth() - 260 - PADDING,
            Gdx.graphics.getHeight() - PADDING - 30);
        batch.end();
    }
}
