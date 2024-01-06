package primitives;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for primitives.Point class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class PointTest {
    Point  p1         = new Point(1, 2, 3);
    Point  p2         = new Point(2, 4, 6);
    Point  p3         = new Point(2, 4, 5);
    Vector v1         = new Vector(1, 2, 3);
    Vector v1Opposite = new Vector(-1, -2, -3);
    Vector v2         = new Vector(-2, -4, -6);
    Vector v3         = new Vector(0, 3, -2);
    Vector v4         = new Vector(1, 2, 2);

    /**
     * Test method for {@link Point#Point(double, double, double)} and for {@link Point#Point(Double3)}
     */
    @Test
    void testConstructor() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test construct a Point.
        assertDoesNotThrow(() -> new Point(1, 1, 1), "Failed constructing a Point");
        assertDoesNotThrow(() -> new Point(new Double3(1, 1, 1)),"Failed constructing a Point");
    }

    /**
     * Test method for {@link primitives.Point#subtract(primitives.Point)}.
     */
    @Test
    void testSubtract() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test deduction of points.
        assertEquals(v1,p2.subtract(p1),"ERROR: (point2 - point1) does not work correctly");
        assertThrows(IllegalArgumentException.class, ()-> p1.subtract(p1),"ERROR: (point - itself) does not throw an exception");
    }
    /**
     * Test method for {@link primitives.Point#add(primitives.Vector)}.
     */
    @Test
    void testAdd() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test add vector to point.
        assertEquals(p2, p1.add(v1) ,"ERROR: (point + vector) = other point does not work correctly");
        assertEquals(Point.ZERO,p1.add(v1Opposite),"ERROR: (point + vector) = center of coordinates does not work correctly");
    }
    /**
     * Test method for {@link primitives.Point#distanceSquared(primitives.Point)}.
     */
    @Test
    void testDistanceSquared() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test the squared distance to itself equals to zero.
        assertEquals(0, p1.distanceSquared(p1), "ERROR: point squared distance to itself is not zero");
        //TC02: test the squared distance between points.
        assertEquals(0,p1.distanceSquared(p3) - 9,"ERROR: squared distance between points is wrong");
        assertEquals(0,p3.distanceSquared(p1) - 9,"ERROR: squared distance between points is wrong");
    }
    /**
     * Test method for {@link primitives.Point#distance(primitives.Point)}.
     */
    @Test
    void testDistance() {
        // ============ Equivalence Partitions Tests ==============
        //TC01: test the distance to itself equals to zero.
        assertEquals(0,p1.distance(p1),"ERROR: point distance to itself is not zero");
        //TC02: test the distance between points.
        assertEquals(0,p1.distance(p3) - 3,"ERROR: distance between points to itself is wrong");
        assertEquals(0,p3.distance(p1) - 3,"ERROR: distance between points to itself is wrong");
    }
}