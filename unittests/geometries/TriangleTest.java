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
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the triangle.
        // Creating a triangle with vertices (1,0,0),(0,1,0),(0,0,0)
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0),new Point(1, 0, 0));
        // Getting the normal at the given point
        Vector normal = new Vector(0, 0, 1);
        assertEquals(normal, triangle.getNormal(new Point(0.1,0.1,0)), "ERROR: normal to plane does not work correctly");
        assertEquals(0, new Vector(1, 0, 0).dotProduct(normal), "ERROR: The vector is not orthogonal to the vector in the triangle");
        assertEquals(1, normal.length(), "ERROR: the normal is not a unit vector");
    }
}
