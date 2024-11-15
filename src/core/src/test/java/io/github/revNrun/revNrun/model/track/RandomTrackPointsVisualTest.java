/**
 * RandomTrackPointsVisualTest is a test made with LibGDX logic with the
 * objective of qualifying the RandomTrackPoints data generation in an easy and
 * visual way.
 * The easy way to execute this test is to paste all the commented code and paste
 * it in the Main class, removing all its previous content. Or creating a new Main class
 * and renaming the original one. Once ended, it's very important to undo the changes.
 * This should be an extremely cautious and temporary test, as modifying other classes
 * can have bad consequences. So don't make changes to the code when it's in the Main class,
 * don't forget the code in the Main class, etc.
 */

/*
package io.github.revNrun.revNrun;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import io.github.revNrun.revNrun.model.track.RandomTrackPoints;
import io.github.revNrun.revNrun.model.vector.Vector2;

import java.util.List;

public class Main extends ApplicationAdapter {
    //public Main(){ System.out.println("This is the game's Main class"); }

    private OrthographicCamera camera;
    private ShapeRenderer shapeRenderer;
    private RandomTrackPoints randomTrackPoints;

    @Override
    public void create() {
        // Configura la cámara
        camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 600); // Cambia las dimensiones según tus necesidades
        camera.update();

        // Inicializa el ShapeRenderer para dibujar
        shapeRenderer = new ShapeRenderer();

        // Genera los puntos del circuito
        randomTrackPoints = new RandomTrackPoints();
    }

    @Override
    public void render() {
        // Limpia la pantalla
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        // Configura la cámara
        shapeRenderer.setProjectionMatrix(camera.combined);

        // Dibuja los puntos generados
        drawPoints();
        drawBasePoints();
        drawInitialPoints();
    }

    private void drawInitialPoints() {
        List<Vector2> points = randomTrackPoints.getInitialPoints();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.BLUE);

        for (Vector2 point : points) {
            // Ajusta las coordenadas al centro de la pantalla
            float x = point.getX() + Gdx.graphics.getWidth() / 2f;
            float y = point.getY() + Gdx.graphics.getHeight() / 2f;

            // Dibuja cada punto como un pequeño círculo
            shapeRenderer.circle(x, y, 2);
        }

        shapeRenderer.end();
    }

    private void drawBasePoints() {
        List<Vector2> points = randomTrackPoints.getBasePoints();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.GREEN);

        for (Vector2 point : points) {
            // Ajusta las coordenadas al centro de la pantalla
            float x = point.getX() + Gdx.graphics.getWidth() / 2f;
            float y = point.getY() + Gdx.graphics.getHeight() / 2f;

            // Dibuja cada punto como un pequeño círculo
            shapeRenderer.circle(x, y, 3);
        }

        shapeRenderer.end();
    }

    private void drawPoints() {
        List<Vector2> points = randomTrackPoints.getPoints();

        shapeRenderer.begin(ShapeRenderer.ShapeType.Filled);
        shapeRenderer.setColor(Color.RED);

        for (Vector2 point : points) {
            // Ajusta las coordenadas al centro de la pantalla
            float x = point.getX() + Gdx.graphics.getWidth() / 2f;
            float y = point.getY() + Gdx.graphics.getHeight() / 2f;

            // Dibuja cada punto como un pequeño círculo
            shapeRenderer.circle(x, y, 2);
        }

        shapeRenderer.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.update();
    }

    @Override
    public void dispose() {
        // Libera los recursos
        shapeRenderer.dispose();
    }
}
*/
