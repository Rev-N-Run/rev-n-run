package io.github.revNrun.revNrun.controllers.trackController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SimplexNoiseTest {

    // every noise point should be a number between -1 and 1
    // we test multiple outputs and check they're inside that range
    @Test
    void outputRange() {
        double x, y, noise;

        // negative and small values
        for (x = -10; x < 100; x += 2.25) {
            for (y = -10; y < 800; y += 1.75) {
                noise = SimplexNoise.noise(x, y);
                assertTrue(-1 < noise && noise < 1);
            }
        }

        // big values
        for (x = -12321; x < -12300; x += 0.01) {
            noise = SimplexNoise.noise(x, x);
            assertTrue(-1 < noise && noise < 1);
        }

        // 0
        noise = SimplexNoise.noise(0, 0);
        assertTrue(-1 < noise && noise < 1);

    }

    // a specific input should always receive the same output
    @Test
    void sameInputSameOutput() {
        // small values
        double a1 = SimplexNoise.noise(2, 4);
        double a2 = SimplexNoise.noise(2, 4);
        // 0 and same value with different representations
        double b1 = SimplexNoise.noise(0, 0);
        double b2 = SimplexNoise.noise(0.000, -0.00);
        // big values
        double c1 = SimplexNoise.noise(-12321, 32147);
        double c2 = SimplexNoise.noise(-12321, 32147);

        assertEquals(a1, a2);
        assertEquals(b1, b2);
        assertEquals(c1, c2);
    }

    // a very slight difference in the input should result in a different output
    @Test
    void smallInputChanges() {
        // just one coordinate is different
        double a1 = SimplexNoise.noise(2, 4);
        double a2 = SimplexNoise.noise(2.1, 4);
        // both are different but very small change
        double b1 = SimplexNoise.noise(0, 0);
        double b2 = SimplexNoise.noise(0.0001, 0.000001);
        // big values
        double c1 = SimplexNoise.noise(-12322, 32147);
        double c2 = SimplexNoise.noise(-12321, 32148);

        assertNotEquals(a1, a2);
        assertNotEquals(b1, b2);
        assertNotEquals(c1, c2);
    }

    // output should be a random but just smoothly different
    // we test that comparing each value with the last value created in a sequence
    @Test
    void smoothRandom() {
        double actNoise;
        // positive and small values
        double prevNoise = SimplexNoise.noise(2, 2);
        for (double x = 2.1; x < 4; x += 0.1) {
            actNoise = SimplexNoise.noise(x, x);
            assertTrue(Math.abs(actNoise - prevNoise) < 0.5);
        }

        // negative and big values
        prevNoise = SimplexNoise.noise(0, 0);
        for (double x = -100; x > -1000; x -= 100) {
            actNoise = SimplexNoise.noise(x, x);
            assertTrue(Math.abs(actNoise - prevNoise) < 0.5);
        }

        // very small values should be even smoother + different values for x and y
        prevNoise = SimplexNoise.noise(0, 0);
        for (double x = -1; x > 1; x += 0.01) {
            for (double y = 1; y > -1; y -= 0.01) {
                actNoise = SimplexNoise.noise(x, y);
                assertTrue(Math.abs(actNoise - prevNoise) < 0.1);
            }
        }
    }
}
