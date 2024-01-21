package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Cylinder class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class CylinderTest {

    /**
     * Test method for {@link geometries.Cylinder#getNormal(primitives.Point)}.
     */
    @Test
    void getNormal() {
        Point center = new Point(0, 0, 0);
        Vector direction = new Vector(1, 0, 0);
        Cylinder cylinder = new Cylinder(1, new Ray(center, new Vector(1, 0, 0)), 5);

        // ============ Equivalence Partitions Tests ==============
        //TC01: Test the side of the cylinder.
        assertEquals(new Vector(0, 0, 1),
                cylinder.getNormal(new Point(4, 0, 1)),
                "TC01 Failed: side of the cylinder");
        //TC02: Test the bottom base.
        assertEquals(direction,
                cylinder.getNormal(new Point(0, 0.2, 0.2)),
                "TC02 Failed: bottom base of the cylinder");
        //TC03: Test the top base.
        assertEquals(direction,
                cylinder.getNormal(new Point(5, 0.2, 0.2)),
                "TC03 Failed: top base of the cylinder");

        // =============== Boundary Values Tests ==================
        //TC04: Test the center of the bottom base.
        assertEquals(direction,
                cylinder.getNormal(center),
                "TC04 failed: Center of the bottom base");
        //TC05: Test the Center of the top base.
        assertEquals(direction,
                cylinder.getNormal(new Point(5, 0, 0)),
                "TC05 failed: Center of the op base");
    }
}

