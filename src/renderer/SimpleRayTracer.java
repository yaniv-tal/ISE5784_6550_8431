package renderer;

import geometries.Intersectable.GeoPoint;
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
    @Override
    public Color traceRay (Ray ray) {
        List<GeoPoint> sceneIntersection = scene.geometries.findGeoIntersections(ray);
        if (sceneIntersection == null) {
            return scene.background;
        }
        return calcColor(ray.findClosestGeoPoint(sceneIntersection));
    }

    /**
     * Calculating the color of a point
     * @param gp
     * @return color
     */
    private Color calcColor(GeoPoint gp) {
        return scene.ambientLight.getIntensity().add(gp.geometry.getEmission());
    }

}

