package primitives;

import org.junit.jupiter.api.Test;

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
}