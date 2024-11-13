package io.github.revNrun.revNrun.model.track;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplexNoiseTest {

    @Test
    void outputRange() {
        // every noise point should be a number between -1 and 1
        // we test multiple outputs and check they're inside that range

        float x, y, noise;

        // negative and small values
        for (x = -10; x < 100; x += 2.25f) {
            for (y = -10; y < 800; y += 1.75f) {
                noise = SimplexNoise.noise(x, y);
                assertTrue(-1 < noise && noise < 1);
            }
        }

        // big values
        for (x = -12321; x < -12300; x += 0.01f) {
            noise = SimplexNoise.noise(x, x);
            assertTrue(-1 < noise && noise < 1);
        }

        // 0
        noise = SimplexNoise.noise(0, 0);
        assertTrue(-1 < noise && noise < 1);

    }

    @Test
    void sameInputSameOutput() {
        // a specific input should always receive the same output

        // small values
        float a1 = SimplexNoise.noise(2, 4);
        float a2 = SimplexNoise.noise(2, 4);
        // 0 and same value with different representations
        float b1 = SimplexNoise.noise(0, 0);
        float b2 = SimplexNoise.noise(0.000f, -0.00f);
        // big values
        float c1 = SimplexNoise.noise(-12321, 32147);
        float c2 = SimplexNoise.noise(-12321, 32147);

        assertEquals(a1, a2);
        assertEquals(b1, b2);
        assertEquals(c1, c2);
    }

    @Test
    void smallInputChanges() {
        // a very slight difference in the input should result in a different output

        // just one coordinate is different
        float a1 = SimplexNoise.noise(2, 4);
        float a2 = SimplexNoise.noise(2.1f, 4);
        // both are different but very small change
        float b1 = SimplexNoise.noise(0, 0);
        float b2 = SimplexNoise.noise(0.0001f, 0.000001f);
        // big values
        float c1 = SimplexNoise.noise(-12322, 32147);
        float c2 = SimplexNoise.noise(-12321, 32148);

        assertNotEquals(a1, a2);
        assertNotEquals(b1, b2);
        assertNotEquals(c1, c2);
    }

    @Test
    void smoothRandom() {
        // output should be a random but just smoothly different
        // we test that comparing each value with the last value created in a sequence
        // test smoothness by checking the maximum rate of change
        // simplex noise has a theoretical maximum rate of change

        float step = 0.01f;
        float maxAllowedGradient = 10.0f;

        for (int x = -5; x <= 5; x += 1) {
            for (int y = -5; y <= 5; y += 1) {
                float n1 = SimplexNoise.noise(x, y);
                float n2 = SimplexNoise.noise(x + step, y);
                float n3 = SimplexNoise.noise(x, y + step);

                float gradientX = Math.abs(n2 - n1) / step;
                float gradientY = Math.abs(n3 - n1) / step;

                assertTrue(gradientX < maxAllowedGradient);
                assertTrue(gradientY < maxAllowedGradient);
            }
        }
    }
}
