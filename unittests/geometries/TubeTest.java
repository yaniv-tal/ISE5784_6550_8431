package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Unit tests for geometries.Tube class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class TubeTest {
    /**
     * Test method for {@link geometries.Tube#getNormal(primitives.Point)}.
     */
    @Test
    void testGetNormal() {
        // ============ Equivalence Partitions Tests ==============
        // TC01: Test the normal of the side of the tube.

        //Creating a tube with radius 1 and a ray starting from (-1, 0, 0) in the direction (1,0,0).
        Tube tube = new Tube(1,new Ray(new Point(-1,0,0),new Vector(1,0,0)));

        //The expected normal for the point (0,0,1) on the tube.
        Vector expected = new Vector(0,0,1);

        assertEquals(expected,tube.getNormal(new Point(0,0,1)),"ERROR: incorrect normal for point on tube.");

        // =============== Boundary Values Tests ==================
        // TC02: Test the normal in case that the point is in front of the head of the ray.

        // Creating another tube with radius 1 and a ray starting from (0,0,0) in the direction (1,0,0)
        Tube tube1 = new Tube(1, new Ray(new Point(0, 0, 0), new Vector(1, 0,0 )));

        // The expected normal for the point (0,0,1) on the tube in this case.
        Vector expected2 = new Vector(0, 0, 1);

        // Checking if the actual normal matches the expected normal.
        assertEquals(expected2, tube1.getNormal(new Point(0, 0, 1)), "ERROR: incorrect normal for point on tube in the case that point is in front of the origin of the defining ray.");
    }

    @Test
    void testFindIntsersections() {
    }
}