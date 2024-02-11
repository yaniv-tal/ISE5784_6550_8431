package renderer;

import geometries.Intersectable.GeoPoint;
import lighting.LightSource;
import primitives.*;
import scene.Scene;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

/**
 * class for ray intersection color calculations.
 * @author Yaniv and Ahuvya.
 */
public class SimpleRayTracer extends RayTracerBase {
    private static final Double3 INITIAL_K = Double3.ONE;

    /**
     * copy constructor. uses the father constructor
     * @param scene
     */
    public SimpleRayTracer(Scene scene) {
        super(scene);
    }

    /**
     *
     */
    private static final int MAX_CALC_COLOR_LEVEL = 10;
    private static final double MIN_CALC_COLOR_K = 0.001;


    /**
     * Shading test method
     * @param geoPoint - the point and the object
     * @param l - light Direction
     * @param n - normal
     * @return Is there shading - "true" or "false"
     */
    private Double3 transparency(GeoPoint geoPoint, LightSource lightSource, Vector l, Vector n) {
        Vector lightDirection = l.scale(-1); // from point to light source
        Ray lightRay = new Ray(geoPoint.point, lightDirection, n);
        double lightD = lightSource.getDistance(geoPoint.point);
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(lightRay, lightD);
        if (intersections == null)
            return Double3.ONE;
        Double3 ktr = Double3.ONE;
        for (GeoPoint gp : intersections) {
            ktr = ktr.product(gp.geometry.getMaterial().kT);
            if (ktr.equals(Double3.ZERO))
                break;
        }
        return ktr;
    }


    /**
     * ray intersection color calculations.
     * @param ray
     * @return the color of the intersection point.
     */
    @Override
    public Color traceRay(Ray ray) {
        GeoPoint closestPoint = findClosestIntersection(ray);
        return closestPoint == null ? scene.background : calcColor(closestPoint, ray);
    }

    /**
     * Calculating the color of a point
     * @param geoPoint
     * @param ray
     * @return color
     */
    private Color calcColor(GeoPoint geoPoint, Ray ray) {
        return calcColor(geoPoint, ray, MAX_CALC_COLOR_LEVEL, INITIAL_K).add(scene.ambientLight.getIntensity());
    }

    /**
     * Calculating the color of a point
     * @param intersection
     * @param ray
     * @return color
     */
    private Color calcColor(GeoPoint intersection, Ray ray, int level, Double3 k) {
        Color color = calcLocalEffects(intersection, ray, k);
        return 1 == level ? color : color.add(calcGlobalEffects(intersection, ray, level, k));
    }

    private Color calcGlobalEffects(GeoPoint geoPoint, Ray ray, int level, Double3 k) {
        Vector normal = geoPoint.geometry.getNormal(geoPoint.point);
        Material material = geoPoint.geometry.getMaterial();
        return calcGlobalEffect(constructRefractedRay(geoPoint, ray.getDirection(), normal), material.kT, level, k)
                .add(calcGlobalEffect(constructReflectedRay(geoPoint, ray.getDirection(), normal), material.kR, level, k));
    }

    private Ray constructReflectedRay(GeoPoint gp, Vector rayDirection, Vector normal) {
        double nv = alignZero(normal.dotProduct(rayDirection));
        if(isZero(nv))
            return null;
        return new Ray(gp.point, rayDirection.subtract(normal.scale(2* nv)), normal);
    }

    private Ray constructRefractedRay(GeoPoint gp, Vector rayDirection, Vector normal) {
        return new Ray(gp.point,rayDirection,normal);
    }


    private Color calcGlobalEffect(Ray ray, Double3 kx, int level, Double3 k) {
        Double3 kkx = kx.product(k);
        if (kkx.lowerThan(MIN_CALC_COLOR_K))
            return Color.BLACK;
        GeoPoint closestIntersection = findClosestIntersection(ray);
        return (closestIntersection == null ? scene.background : calcColor(closestIntersection, ray, level - 1, kkx))
                .scale(kx);
    }

    /**
     * A method that will help for the color calculation
     * @param geoPoint
     * @param ray
     * @return The effect of light
     */
    private Color calcLocalEffects(GeoPoint geoPoint, Ray ray, Double3 k) {
        Color color = geoPoint.geometry.getEmission();
        Vector n = geoPoint.geometry.getNormal(geoPoint.point);
        Vector v = ray.getDirection();
        double nv = alignZero(n.dotProduct(v));
        if (nv == 0)
            return color;
        Material material = geoPoint.geometry.getMaterial();
        for (LightSource lightSource : scene.lights) {
            Vector l = lightSource.getL(geoPoint.point);
            double nl = alignZero(n.dotProduct(l));
            if (nl * nv > 0) { // sign(nl) == sing(nv)
                Double3 ktr = transparency(geoPoint, lightSource, l, n);
                if (ktr.product(k).greaterThan(MIN_CALC_COLOR_K)) {
                    Color iL = lightSource.getIntensity(geoPoint.point).scale(ktr);
                    color = color.add(iL.scale(calcDiffusive(material, nl).add(calcSpecular(material, n, l, nl, v))));
                }
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
    private Double3 calcDiffusive(Material material, double nl){
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

    /**
     *
     * @param ray
     * @return
     */
    private GeoPoint findClosestIntersection(Ray ray){
        // check if ray constructed through the pixel intersects any of geometries
        List<GeoPoint> intersections = scene.geometries.findGeoIntersections(ray);

        // return closest point if list is not empty
        return intersections == null ? null : ray.findClosestGeoPoint(intersections);
    }
}

