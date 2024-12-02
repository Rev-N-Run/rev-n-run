package io.github.revNrun.revNrun.view.countdown;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class CountdownView implements ICountdownView{
    private BitmapFont titleFont;
    private BitmapFont countdownFont;
    private SpriteBatch batch;
    private Viewport viewport;
    private float titleAlpha = 1f;
    private float numberAlpha = 1f;
    private float runAlpha = 1f;
    private float scale = 1f;
    private boolean showRun = false;
    private GlyphLayout glyphLayout;

    public CountdownView() {
        this.batch = new SpriteBatch();
        this.viewport = new ScreenViewport();
        this.glyphLayout = new GlyphLayout();
        createFonts();
    }

    private void createFonts() {
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(
            Gdx.files.internal("GeistMonoNerdFontPropo-Medium.otf"));

        FreeTypeFontGenerator.FreeTypeFontParameter titleParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
        titleParam.size = 72;
        titleParam.color = Color.RED;
        titleFont = generator.generateFont(titleParam);

        FreeTypeFontGenerator.FreeTypeFontParameter countdownParam = new FreeTypeFontGenerator.FreeTypeFontParameter();
        countdownParam.size = 96;
        countdownParam.color = Color.RED;
        countdownFont = generator.generateFont(countdownParam);

        generator.dispose();
    }

    @Override
    public void draw(int number) {
        viewport.update(Gdx.graphics.getWidth(), Gdx.graphics.getHeight(), true);
        batch.setProjectionMatrix(viewport.getCamera().combined);

        batch.begin();

        if (!showRun) {
            titleFont.setColor(1, 1, 1, titleAlpha);
            glyphLayout.setText(titleFont, "REV!");
            titleFont.draw(batch, "REV!",
                (Gdx.graphics.getWidth() - glyphLayout.width) / 2f,
                Gdx.graphics.getHeight() * 0.85f);
        }

        if (showRun) {
            countdownFont.setColor(1, 1, 1, runAlpha);
            glyphLayout.setText(countdownFont, "RUN!");
            drawCenteredText("RUN!", glyphLayout);
        } else if (number > 0) {
            countdownFont.setColor(1, 1, 1, numberAlpha);
            String numberStr = String.valueOf(number);
            glyphLayout.setText(countdownFont, numberStr);
            drawCenteredText(numberStr, glyphLayout);
        }

        batch.end();
    }


    private void drawCenteredText(String text, GlyphLayout layout) {
        float centerX = Gdx.graphics.getWidth() / 2f;
        float centerY = Gdx.graphics.getHeight() / 2f;

        batch.end();
        batch.begin();

        float yOffset = layout.height / 2f;

        batch.setTransformMatrix(batch.getTransformMatrix()
            .setToTranslation(centerX, centerY, 0)
            .scale(scale, scale, 1)
            .translate(-layout.width/2f, yOffset, 0));

        countdownFont.draw(batch, text, 0, 0);

        batch.setTransformMatrix(batch.getTransformMatrix().idt());
    }

    @Override
    public void setShowRun(boolean show) {
        this.showRun = show;
    }

    @Override
    public void setTitleAlpha(float alpha) {
        this.titleAlpha = alpha;
    }

    @Override
    public void setNumberAlpha(float alpha) {
        this.numberAlpha = alpha;
    }

    @Override
    public void setRunAlpha(float alpha) {
        this.runAlpha = alpha;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }
}
