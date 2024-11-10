package io.github.revNrun.revNrun.controllers.vectorController;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    @Test
    void getX() {
        Vector2 a = new Vector2(3, 4);
        Vector2 b = new Vector2(0, 0);
        Vector2 c = new Vector2(-1, 0);
        Vector2 d = new Vector2(0, -1);
        Vector2 e = new Vector2(-20, -30);
        Vector2 f = new Vector2(e);
        Vector2 g = new Vector2();

        g.setX(2);

        assertEquals(3, a.getX());
        assertEquals(0, b.getX());
        assertEquals(-1, c.getX());
        assertEquals(0, d.getX());
        assertEquals(-20, e.getX());
        assertEquals(-20, f.getX());
        assertEquals(2, g.getX());
    }

    @Test
    void getY() {
        Vector2 a = new Vector2(3, 4);
        Vector2 b = new Vector2(0, 0);
        Vector2 c = new Vector2(-1, 0);
        Vector2 d = new Vector2(0, -1);
        Vector2 e = new Vector2(-20, -30);
        Vector2 f = new Vector2(e);
        Vector2 g = new Vector2();

        g.setY(4);

        assertEquals(4, a.getY());
        assertEquals(0, b.getY());
        assertEquals(0, c.getY());
        assertEquals(-1, d.getY());
        assertEquals(-30, e.getY());
        assertEquals(-30, f.getY());
        assertEquals(4, g.getY());
    }

    @Test
    public void testDistance(){
        Vector2 a = new Vector2(0, 0);
        Vector2 b = new Vector2(3, 4);
        Vector2 c = new Vector2(-8, -5);

        // Positive values
        assertEquals(a.distance(b), 5.0);
        // Same values
        assertEquals(b.distance(b), 0.0);
        // Negative values
        assertEquals(a.distance(c), 14.21);
    }
}
