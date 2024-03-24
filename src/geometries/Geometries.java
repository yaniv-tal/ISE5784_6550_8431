package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The class implements an item list of geometries.
 * @author Yaniv and Ahuvya.
 */
public class Geometries extends Intersectable {
    final private List<Intersectable> geometriesList = new LinkedList<Intersectable>();

    /**
     * Default constructor that creates an empty list of geometries.
     */
    public Geometries() {
    }

    /**
     * constructor. get a list of geometries and add them to geometries list.
     *
     * @param geometries the given list of geometries.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add a list of geometries to the list.
     *
     * @param geometries the given list of geometries.
     */
    public void add(Intersectable... geometries) {
        geometriesList.addAll(List.of(geometries));
    }

    @Override
    public List<GeoPoint> findGeoIntersectionsHelper(Ray ray, double maxDistance) {
        List<Point> bvhPoints = findBVHPoints();
        double[] min = {bvhPoints.get(0).getX(), bvhPoints.get(0).getY(), bvhPoints.get(0).getZ()};
        double[] max = {bvhPoints.get(1).getX(), bvhPoints.get(1).getY(), bvhPoints.get(1).getZ()};
        if (!checkBVHIntersection(min, max, ray)){
            return null;
        }
        //Initialize variables to null.
        List<GeoPoint> intersections = null;

        // loop goes over the geometries and add the intersections into itemList.
        for (var item : geometriesList) {
            var itemList = item.findGeoIntersectionsHelper(ray, maxDistance);
            if (itemList != null) {
                //Create the list for the first item that has intersection points.
                if (intersections == null)
                    intersections = new LinkedList<GeoPoint>();
                intersections.addAll(itemList);
            }
        }
        return intersections;
    }

    @Override
    public List<Point> findBVHPoints() {
        double minX = Double.POSITIVE_INFINITY;
        double minY = Double.POSITIVE_INFINITY;
        double minZ = Double.POSITIVE_INFINITY;
        double maxX = Double.NEGATIVE_INFINITY;
        double maxY = Double.NEGATIVE_INFINITY;
        double maxZ = Double.NEGATIVE_INFINITY;
        for (Intersectable i : geometriesList) {
            minX = Math.min(minX, i.findBVHPoints().get(0).getX());
            minY = Math.min(minY, i.findBVHPoints().get(0).getY());
            minZ = Math.min(minZ, i.findBVHPoints().get(0).getZ());
            maxX = Math.max(maxX, i.findBVHPoints().get(1).getX());
            maxY = Math.max(maxY, i.findBVHPoints().get(1).getY());
            maxZ = Math.max(maxZ, i.findBVHPoints().get(1).getZ());
        }
        return List.of(new Point(minX,minY,minZ),new Point(maxX,maxY,maxZ));
    }

    public boolean checkBVHIntersection(double[] min, double[] max, Ray ray) {
        double tMin = Double.NEGATIVE_INFINITY;
        double tMax = Double.POSITIVE_INFINITY;
        double[] direction ={ray.getDirection().getX(),ray.getDirection().getY(),ray.getDirection().getY()};
        double[] head = {ray.getHead().getX(),ray.getHead().getY(),ray.getHead().getY()};
        for (int i = 0; i < 3; i++) {
            double tNear = (min[i] - head[i]) / direction[i];
            double tFar = (max[i] - head[i]) / direction[i];
            if (tNear > tFar) {
                double temp = tNear;
                tNear = tFar;
                tFar = temp;
            }
            tMin = Math.max(tNear, tMin);
            tMax = Math.min(tFar, tMax);
            if (tMin > tMax) {
                return false;
            }
        }
        return true;
    }

    public List<Intersectable> getGeometries() {
        return geometriesList;
    }
}
