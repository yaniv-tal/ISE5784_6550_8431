package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class PlaneTest {
    /**
     * Test method for {@link Plane#Plane(Point, Point, Point)} and for {@link Plane#Plane(Point,Vector)}
     */
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        // TC01: The first and second points are same.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0 ,0),new Point (1,0,0), new Point (1,7,9)),
                "ERROR: constructor does not throw error in illegal definition- 2 correlating points.");
        // TC02: The points are on the same line.
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1 ,1),new Point (2,2,2), new Point (3,3,3)),
                "ERROR: constructor does not throw error in illegal definition - all points on same line.");

    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal to the plane.
        Plane plane = new Plane(new Point(0,0,0),new Point(0,1,0),new Point(1,0,0));
        assertEquals(new Vector(0,0,1),plane.getNormal(),"ERROR: normal to plane does not work correctly");
        assertEquals(1, plane.getNormal().length(), "ERROR: plane normalized function does not work correctly");
    }
    /**
     * Test method for {@link geometries.Plane#findIntersections(primitives.Ray)}.
     */
    @Test
    void testFindIntersections() {
        final Point p01 = new Point(-1, 0, 0);
        final Point p02 = new Point(1, 0, 0);
        final Point p03 = new Point(0, 1, 0);
        final Point p04 = new Point(0, 0, 1);
        final Point p05 = new Point(-1, -1, -1);
        Plane plane = new Plane(p02,p03,p04);
        final Vector v01 = new Vector(-1, 0, 0);
        final Vector v02 = new Vector(1, 0, -1);
        final Vector v03 = new Vector(1, 1, 1);
        final Point gp1 = new Point(1, 1, 1);
        final var exp1 = List.of(gp1);

        assertNull(plane.findIntersections(new Ray(p01, v01)), "Ray's line out of Plane");
        assertNull(plane.findIntersections(new Ray(p02, v01)), "Ray's line out of Plane");
        assertNull(plane.findIntersections(new Ray(p02, v02)), "Ray's line does not intersect with the plane");
        assertNull(plane.findIntersections(new Ray(p01, v02)), "Ray's line out of Plane");
        final var result1 = plane.findIntersections(new Ray(p05, v03));//.stream().sorted(Comparator.comparingDouble(p) -> p.distance(p01))).toList();
        assertEquals(1, result1.size(), "Wrong number of points");
        assertEquals(exp1, result1, "Ray crosses Plane");
    }
}