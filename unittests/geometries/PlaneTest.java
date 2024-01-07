package geometries;

import org.junit.jupiter.api.Test;
import primitives.Double3;
import primitives.Point;
import primitives.Vector;

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

    @Test
    void testFindIntsersections() {
    }
}