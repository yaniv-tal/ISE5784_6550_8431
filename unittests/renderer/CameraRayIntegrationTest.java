package renderer;

import geometries.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;
import primitives.Point;
import primitives.Vector;

import java.util.List;

/**
 * Integration tests between creating rays from a camera and calculating sections of a ray with geometric bodies
 */
public class CameraRayIntegrationTest {
    private final Camera.Builder cameraBuilder = Camera.getBuilder()
            .setDirection(new Vector(0, 0, -1), new Vector(0, -1, 0))
            .setVpSize(3, 3)
            .setVpDistance(1);

    private int calculateIntersections(Geometry geometry, Camera camera) {
        int sumOfIntersections = 0;
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++) {
                var intersections = geometry.findIntersections(camera.constructRay(3, 3, j, i));
                if (intersections != null)
                    sumOfIntersections += intersections.size();
            }
        return sumOfIntersections;
    }

    private final Camera camera1 = cameraBuilder.setLocation(Point.ZERO).build();
    private final Camera camera2 = cameraBuilder.setLocation(new Point(0, 0, 0.5)).build();

    /**
     *Integration tests between creating rays from a camera and calculating sections of a ray with a sphere
     */
    @Test
    void testSphere() {
        //TC01: 2 intersections.
        Sphere sphere = new Sphere(1, new Point(0, 0, -3));
        assertEquals(2, calculateIntersections(sphere, camera1), "ERROR: Wrong number of intersections.");
        //TC02: 18 intersections.
        sphere = new Sphere(2.5, new Point(0, 0, -2.5));
        assertEquals(18, calculateIntersections(sphere, camera2), "ERROR: Wrong number of intersections.");
        //TC03: 10 intersections.
        sphere = new Sphere(2, new Point(0, 0, -2));
        assertEquals(10, calculateIntersections(sphere, camera2), "ERROR: Wrong number of intersections.");
        //TC04: 9 intersections.
        sphere = new Sphere(4, new Point(0, 0, -1.5));
        assertEquals(9, calculateIntersections(sphere, camera1), "ERROR: Wrong number of intersections.");
        //TC05: no intersections.
        sphere = new Sphere(0.5, new Point(0, 0, 1));
        assertEquals(0, calculateIntersections(sphere, camera1), "ERROR: Wrong number of intersections.");
    }

    /**
     * Integration tests between creating rays from a camera and calculating sections of a ray with a plane
     */
    @Test
    void testPlane() {
        //TC01: 9 intersections.
        Plane plane = new Plane(new Point(0, 0, -2.5), new Vector(0, 0, 1));
        assertEquals(9, calculateIntersections(plane, camera1), "ERROR: Wrong number of intersections.");
        //TC02: 9 intersections.
        plane = new Plane(new Point(0, 0, -2), new Vector(0, -1, 3));
        assertEquals(9, calculateIntersections(plane, camera1), "ERROR: Wrong number of intersections.");
        //TC03: 6 intersections.
        plane = new Plane(new Point(0, 0, -2.5), new Vector(0, -1, 1));
        assertEquals(6, calculateIntersections(plane, camera1), "ERROR: Wrong number of intersections.");
    }

    /**
     * Integration tests between creating rays from a camera and calculating sections of a ray with a triangle
     */
    @Test
    void testTriangle() {
        //TC01: One intersection.
        Triangle triangle = new Triangle(new Point(0, 1, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(1, calculateIntersections(triangle, camera1), "ERROR: Wrong number of intersections.");
        //TC02: 2 intersections.
        triangle = new Triangle(new Point(0, 20, -2), new Point(1, -1, -2), new Point(-1, -1, -2));
        assertEquals(2, calculateIntersections(triangle, camera1), "ERROR: Wrong number of intersections.");
    }
}
