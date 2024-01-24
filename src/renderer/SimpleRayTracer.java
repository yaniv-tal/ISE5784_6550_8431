package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;
/**
 * class for ray intersection color calculations.
 * @author Yaniv and Ahuvya.
 */
public class SimpleRayTracer extends RayTracerBase{
    /**
     * copy constructor. uses the father constructor
     * @param scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     * ray intersection color calculations.
     * @param ray
     * @return the color of the intersection point.
     */
    public Color traceRay (Ray ray) {
        List<Point> sceneIntersection = scene.geometries.findIntersections(ray);
        if (sceneIntersection == null) {
            return scene.background;
        }
        return calcColor(ray.findClosestPoint(sceneIntersection));
    }

    /**
     * Calculating the color of a point
     * @param Point
     * @return color
     */
    private Color calcColor(Point Point) {
        return scene.ambientLight.getIntensity();
    }
}

