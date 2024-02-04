package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.List;
import java.util.Objects;

/**
 * class of geometric shapes.
 * @author Yaniv and Ahuvya.
 */
public abstract class Intersectable {

    /**
     * At this stage: calls the "findGeoIntersectionsHelper" method
     * @param ray
     * @return Answer of findGeoIntersectionsHelper
     */
    public final List<GeoPoint> findGeoIntersections(Ray ray){
        return findGeoIntersectionsHelper(ray);
    }

    /**
     * find intersections points.
     * @param ray
     * @return list of intersection points between the ray and the geometry.
     */
    public final List<Point> findIntersections(Ray ray) {
        var geoList = findGeoIntersections(ray);
        return geoList == null ? null : geoList.stream().map(gp -> gp.point).toList();
    }
    /**
     * find intersections points.
     * @param ray
     * returns a list of intersection "geopoints" between the ray and the geometry.
     */
    protected abstract List<GeoPoint> findGeoIntersectionsHelper(Ray ray);

    /**
     * A class of point and geometric object
     * @author Yaniv and Ahuvya.
     */
    public static class GeoPoint {
        /**
         * Geometric object
         */
        public Geometry geometry;
        /**
         * point (on the geometry)
         */
        public Point point;

        /**
         * constructor
         * @param geometry
         * @param point
         */
        public GeoPoint(Geometry geometry, Point point) {
            this.geometry = geometry;
            this.point = point;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (!(o instanceof GeoPoint geoPoint)) return false;
            return Objects.equals(geometry, geoPoint.geometry) && Objects.equals(point, geoPoint.point);
        }

        @Override
        public String toString() {
            return "GeoPoint{" +
                    "geometry=" + geometry +
                    ", point=" + point +
                    '}';
        }
    }
}
