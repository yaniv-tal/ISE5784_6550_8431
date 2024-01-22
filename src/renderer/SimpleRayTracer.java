package renderer;

import primitives.Color;
import primitives.Point;
import primitives.Ray;
import scene.Scene;

import java.util.List;

public class SimpleRayTracer extends RayTracerBase{
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }
    public Color traceRay (Ray ray) {
        List<Point> sceneIntersection = scene.geometries.findIntersections(ray);
        if (sceneIntersection == null) {
            return scene.background;
        }
        return calcColor(ray.findClosestPoint(sceneIntersection));
    }

    private Color calcColor(Point closestPoint) {
        return  scene.background;
    }
}

