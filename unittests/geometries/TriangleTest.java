package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

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
        // Creating a triangle with vertices (0,0,0),(0,1,0),(1,0,0)
        Triangle triangle = new Triangle(new Point(0, 0, 0), new Point(0, 1, 0), new Point(1, 0, 0));
        // Getting the normal at the given point
        Vector normal = new Vector(0, 0, 1);
        assertEquals(normal, triangle.getNormal(new Point(0.1,0.1,0)), "ERROR: normal to plane does not work correctly");
        assertEquals(0, new Vector(1, 0, 0).dotProduct(normal), "ERROR: The vector is not orthogonal to the vector in the triangle");
        assertEquals(1, normal.length(), "ERROR: the normal is not a unit vector");
    }

    /**
     * Test method for {@link geometries.Triangle#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        final Point p300 = new Point(1, 0, 0);
        final Point p030 = new Point(0, 1, 0);
        final Point p003 = new Point(0, 0, 1);
        final Point p101 = new Point(1, 0, 1);
        final Point p301 = new Point(3, 0, 1);
        final Point p401 = new Point(4, 0, 1);
        final Point pm1m11 = new Point(-1, -1, 1);
        final Point p999 = new Point(9, 9, 9);
        final Vector v00m1 = new Vector(0, 0, -1);
        final Point p111 = new Point(1, 1, 1);
        final Point p110 = new Point(1, 1, 0);
        final var exp1 = List.of(p110);
        Triangle triangle = new Triangle(p300, p030, p003);
        // ============ Equivalence Partitions Tests ==============
        assertNull(triangle.findIntersections(new Ray(pm1m11, v00m1)), "Ray's line out of triangle");
        assertNull(triangle.findIntersections(new Ray(p999, v00m1)), "Ray's line out of triangle");
        final var result1 = triangle.findIntersections(new Ray(p111, v00m1));
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses Plane");

        // =============== Boundary Values Tests ==================
        assertNull(triangle.findIntersections(new Ray(p101, v00m1)), "Ray's line out of triangle");
        assertNull(triangle.findIntersections(new Ray(p301, v00m1)), "Ray's line out of triangle");
        assertNull(triangle.findIntersections(new Ray(p401, v00m1)), "Ray's line out of triangle");



    }
}

