package io.github.revNrun.revNrun.model.vector;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Vector2Test {
    private static final float EPSILON = 0.0001f;

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
        assertEquals(a.distance(b), 5.0f);
        // Same values
        assertEquals(b.distance(b), 0.0f);
        // Negative values
        assertEquals(b.distance(c), 14.2126f, EPSILON);
    }

    @Test
    public void testAngleBetween_PositiveXAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(1, 0);
        float angle = vector.angleBetween(other);
        assertEquals(0.0, angle, EPSILON, "Angle with positive X axis should be 0");
    }

    @Test
    public void testAngleBetween_PositiveYAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 2, angle, EPSILON, "Angle with positive Y axis should be PI/2");
    }

    @Test
    public void testAngleBetween_NegativeXAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(-1, 0);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI, angle, EPSILON, "Angle with negative X axis should be PI");
    }

    @Test
    public void testAngleBetween_NegativeYAxis() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, -1);
        float angle = vector.angleBetween(other);
        assertEquals(-Math.PI / 2, angle, EPSILON, "Angle with negative Y axis should be -PI/2");
    }

    @Test
    public void testAngleBetween_SamePoint() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        // This test should verify if the method properly handles identical points
        // It could throw an exception or return 0, depending on desired implementation
        assertEquals(0.0, angle, EPSILON, "Angle between identical points should be 0");
    }

    @Test
    public void testAngleBetween_ArbitraryPoints() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(4, 5);
        float angle = vector.angleBetween(other);
        // The correct calculation should be:
        float expectedAngle = (float) Math.atan2(5 - 1, 4 - 1);
        assertEquals(expectedAngle, angle, EPSILON,
            "Angle between points (1,1) and (4,5) should be " + expectedAngle);
    }

    // Additional recommended tests
    @Test
    public void testAngleBetween_DiagonalQuadrant1() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 4, angle, EPSILON,
            "45-degree angle in first quadrant should be PI/4");
    }

    @Test
    public void testAngleBetween_NonZeroOrigin() {
        Vector2 vector = new Vector2(2, 2);
        Vector2 other = new Vector2(4, 4);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI / 4, angle, EPSILON,
            "Angle should be the same regardless of vector origin");
    }

    // Boundary output values tests
    @Test
    public void testAngleBetween_NearPi() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(-1, 0.0001f);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI - EPSILON, angle, EPSILON,
            "Angle should be very close to but less than PI");
    }

    @Test
    public void testAngleBetween_NearMinusPiOver2() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0.0001f, -1);
        float angle = vector.angleBetween(other);
        assertEquals(-Math.PI/2 + EPSILON, angle, EPSILON,
            "Angle should be very close to but greater than -PI/2");
    }

    // Input boundary values tests
    @Test
    public void testAngleBetween_VeryLargeValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MAX_VALUE, Float.MAX_VALUE);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be PI/4 even with very large values");
    }

    @Test
    public void testAngleBetween_VerySmallValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MIN_VALUE, Float.MIN_VALUE);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be PI/4 even with very small values");
    }

    @Test
    public void testAngleBetween_InfiniteValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.POSITIVE_INFINITY, Float.POSITIVE_INFINITY);
        assertThrows(IllegalArgumentException.class, () -> vector.angleBetween(other),
            "Method should throw IllegalArgumentException for infinite values");
    }

    @Test
    public void testAngleBetween_NaNValues() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.NaN, 1);
        assertThrows(IllegalArgumentException.class, () -> vector.angleBetween(other),
            "Method should throw IllegalArgumentException for NaN values");
    }

    // Special cases tests
    @Test
    public void testAngleBetween_ZeroVector() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(0, 0);
        assertThrows(IllegalArgumentException.class, () -> vector.angleBetween(other),
            "Method should throw IllegalArgumentException for zero vector");
    }

    @Test
    public void testAngleBetween_VeryClosePoints() {
        Vector2 vector = new Vector2(1, 1);
        Vector2 other = new Vector2(1 + EPSILON, 1 + EPSILON);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be stable for very close points");
    }

    @Test
    public void testAngleBetween_VeryDistantPoints() {
        Vector2 vector = new Vector2(-1000000, -1000000);
        Vector2 other = new Vector2(1000000, 1000000);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be stable for very distant points");
    }

    @Test
    public void testAngleBetween_ExtremelySmallDistance() {
        Vector2 vector = new Vector2(0, 0);
        Vector2 other = new Vector2(Float.MIN_NORMAL, 0);
        float angle = vector.angleBetween(other);
        assertEquals(0, angle, EPSILON,
            "Angle should be stable for extremely small distances");
    }

    @Test
    public void testAngleBetween_OppositeQuadrants() {
        Vector2 vector = new Vector2(-1, -1);
        Vector2 other = new Vector2(1, 1);
        float angle = vector.angleBetween(other);
        assertEquals(Math.PI/4, angle, EPSILON,
            "Angle should be consistent when points are in opposite quadrants");
    }
}
