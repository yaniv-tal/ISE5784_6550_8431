package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;

/**
 * interface of geometric shapes.
 * @author Yaniv and Ahuvya.
 */
public interface Intersectable {
    /**
     * find intersections points.
     * @param ray
     * returns a list of intersection points between the ray and the geometry.
     */
    List<Point> findIntsersections(Ray ray);


}
