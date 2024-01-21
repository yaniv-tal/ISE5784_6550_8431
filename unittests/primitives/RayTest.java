package primitives;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class RayTest {

    /**
     * Unit tests for primitives.Ray class
     * @author Yaniv Tal and Ahuvya Betzalel
     */
    @Test
    void getPoint() {
        Ray ray = new Ray(new Point(1,0,0),new Vector(1,2,2));

        // ============ Equivalence Partitions Tests ==============
        //TC01: The distance is positive.
        assertEquals(new Point(2,2,2),ray.getPoint(3));
        //TC02: The distance is negative.
        assertEquals(new Point(0,-2,-2),ray.getPoint(-3));

        // =============== Boundary Values Tests ==================
        //TC11: The distance is zero.
        assertEquals(ray.getHead(),ray.getPoint(0));
    }

    @Test
    void findClosestPoint() {
        Ray ray = new Ray(new Point(1,1,1),new Vector(0,0,1));
        final Point p401 = new Point(4, 0, 1);
        final Point p999 = new Point(9, 9, 9);
        final Point p110 = new Point(1, 1, 0);
        // ============ Equivalence Partitions Tests ==============
        //TC01: A point in the middle of the list is the one closest to the beginning of the ray
        assertEquals(p110,ray.findClosestPoint(List.of(p999, p110, p401)),"ERROR: does not return the correct point");
        // =============== Boundary Values Tests ==================
        //TC11: empty list
        assertEquals(p110,ray.findClosestPoint(List.of(p110, p999,  p401)),"ERROR: does not return the correct point");
        //TC12: The first point is closest to the beginning of the ray
        assertEquals(p110,ray.findClosestPoint(List.of( p999,  p401, p110)),"ERROR: does not return the correct point");
        //TC13: The last point is closest to the beginning of the ray
        assertNull(ray.findClosestPoint(null), "ERROR: There is no point to return");
    }
}