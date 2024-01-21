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
        final Point p002 = new Point(0, 0, 2);
        final Point p030 = new Point(0, 3, 0);
        final Point p000 = new Point(0, 0, 0);
        final Point p101 = new Point(1, 0, 1);
        final Point p301 = new Point(3, 0, 1);
        final Point p401 = new Point(4, 0, 1);
        final Point pm1m11 = new Point(-1, -1, 1);
        final Point p999 = new Point(9, 9, 9);
        final Vector v00m1 = new Vector(0, 0, -1);
        final Point p111 = new Point(1, 1, 1);
        final Point p110 = new Point(1, 1, 0);
        // ============ Equivalence Partitions Tests ==============
        //TC01:
        assertEquals(p110,ray.findClosestPoint(List.of(p999, p110, p401)),"");
        // =============== Boundary Values Tests ==================
        //TC11:
        assertEquals(p110,ray.findClosestPoint(List.of()),"");


    }
}