package geometries;

import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import static org.junit.jupiter.api.Assertions.*;
/**
 * Unit tests for geometries.Plane class
 * @author Yaniv Tal and Ahuvya Betzalel
 */
class PlaneTest {
    @Test
    void testConstructor(){
        // =============== Boundary Values Tests ==================
        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 0 ,0),new Point (1,0,0), new Point (1,3,3)),
                "ERROR: constructor does not throw error in illegal definition- 2 correlating points.");

        assertThrows(IllegalArgumentException.class,
                () -> new Plane(new Point(1, 1 ,1),new Point (2,2,2), new Point (0,0,0)),
                "ERROR: constructor does not throw error in illegal definition - all points on same line.");
    }

    /**
     * Test method for {@link geometries.Plane#getNormal(primitives.Point)}.
     */
    @Test
    void GetNormal() {
        // =============== Boundary Values Tests ==================
        Plane plane = new Plane(new Point(0,0,0),new Point(0,1,0),new Point(1,0,0));
        assertEquals(new Vector(0,0,1),plane.getNormal(),"ERROR:get normal does not work correctly");
        assertEquals(1, plane.getNormal().length(), "ERROR: normalized function does not work correctly");
    }
}