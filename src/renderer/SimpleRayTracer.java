package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;

/**
 * class for ray intersection color calculations.
 * @author Yaniv and Ahuvya.
 */
public class SimpleRayTracer extends RayTracerBase {
    /**
     * copy constructor. uses the father constructor
     *
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
    public Color traceRay(Ray ray) {
        List<GeoPoint> sceneIntersection = scene.geometries.findGeoIntersections(ray);
        if (sceneIntersection == null) {
            return scene.background;
        }
        return calcColor(ray.findClosestGeoPoint(sceneIntersection), ray);
    }

    /**
     * Calculating the color of a point
     * @param intersection
     * @param ray
     * @return color
     */
    private Color calcColor(GeoPoint intersection, Ray ray) {
        return scene.ambientLight.getIntensity()
                .add(calcLocalEffects(intersection, ray));
    }

    /**
     * A method that will help for the color calculation
     * @param gp
     * @param ray
     * @return The effect of light
     */
    private Color calcLocalEffects(GeoPoint gp, Ray ray) {
        Color color = gp.geometry.getEmission();
        Vector n = gp.geometry.getNormal(gp.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = gp.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(gp.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Color iL = lightSource.getIntensity(gp.point);
                color = color.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
            }
        }
        return color;
    }

    /**
     * auxiliary method
     * @param material
     * @param nl
     * @return Calculation according to the Fong model
     */
    private Double3 calcDiffusive(Material material, double nl) {
        return material.kD.scale(nl < 0 ? -nl : nl);
    }

    /**
     * auxiliary method
     * @param material
     * @param n
     * @param l
     * @param nl
     * @param v
     * @return Calculation according to the Fong model
     */
    private Double3 calcSpecular(Material material, Vector n, Vector l, double nl, Vector v) {
        Vector r = l.subtract(n.scale(2 * nl));
        double minusVr = -r.dotProduct(v);
        return alignZero(minusVr) > 0 ? material.kS.scale(Math.pow(minusVr, material.nShininess)) : Double3.ZERO;
    }

}

