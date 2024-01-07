package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Triangle class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class TriangleTest {

    /**
     * Test method for {@link geometries.Triangle#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        // Creating a triangle with vertices (1,0,0),(0,1,0),(0,0,1)
        Triangle triangle = new Triangle(new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1));

        // Getting the normal at the given point
        Vector normal = triangle.getNormal(new Point(0.5, 0.5, 0));

        assertEquals(IllegalArgumentException.class,
                () -> (new Vector(1, 1, 1)).crossProduct(normal),
                "ERROR: triangle doesn't return normal in the correct direction.");

        // Checking if the normal is normalized
        assertEquals(1, triangle.getNormal().length(), "ERROR: plane normalized function does not work correctly");    }
}