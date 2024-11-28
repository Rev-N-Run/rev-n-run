package io.github.revNrun.revNrun.model.track;

import io.github.revNrun.revNrun.model.vector.Vector2;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BorderGeneratorTest {
    @Test
    void testGenerateLeftBorder_validInput() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        assertNotNull(leftBorder, "Left border should not be null.");
        assertEquals(points.size(), leftBorder.size(), "Left border should have the same number of points as input.");
    }

    @Test
    void testGenerateRightBorder_validInput() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, width);

        // Assert
        assertNotNull(rightBorder, "Right border should not be null.");
        assertEquals(points.size(), rightBorder.size(), "Right border should have the same number of points as input.");
    }

    @Test
    void testGenerateLeftBorder_emptyPoints() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        float width = 1.0f;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            BorderGenerator.generateLeftBorder(points, width)
        );
        assertEquals("At least two points are required to calculate a border.", exception.getMessage());
    }

    @Test
    void testGenerateRightBorder_singlePoint() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        float width = 1.0f;

        // Act & Assert
        Exception exception = assertThrows(IllegalArgumentException.class, () ->
            BorderGenerator.generateRightBorder(points, width)
        );
        assertEquals("At least two points are required to calculate a border.", exception.getMessage());
    }

    @Test
    void testGenerateBorder_tangentCalculation() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 1.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        // Tangent vectors and offsets should produce expected results
        assertNotNull(leftBorder);
        assertEquals(3, leftBorder.size());
        // Check specific coordinates (example values, adjust based on exact behavior)
        assertEquals(-0.3536f, leftBorder.get(0).getX(), 1e-4);
        assertEquals(0.3536f, leftBorder.get(0).getY(), 1e-4);
    }

    @Test
    void testGenerateBorder_handlesWideWidth() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 10.0f;

        // Act
        List<Vector2> leftBorder = BorderGenerator.generateLeftBorder(points, width);

        // Assert
        assertNotNull(leftBorder);
        assertEquals(3, leftBorder.size());
        assertTrue(leftBorder.get(0).getX() < points.get(0).getX(), "Left border should shift left for wide width.");
    }

    @Test
    void testGenerateBorder_handlesNarrowWidth() {
        // Arrange
        List<Vector2> points = new ArrayList<>();
        points.add(new Vector2(0, 0));
        points.add(new Vector2(1, 1));
        points.add(new Vector2(2, 0));
        float width = 0.1f;

        // Act
        List<Vector2> rightBorder = BorderGenerator.generateRightBorder(points, width);

        // Assert
        assertNotNull(rightBorder);
        assertEquals(3, rightBorder.size());
        assertTrue(rightBorder.get(0).getX() > points.get(0).getX(), "Right border should shift right for narrow width.");
    }
}
