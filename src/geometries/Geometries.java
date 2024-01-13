package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

/**
 * The class implements an item list of geometries.
 * @author Yaniv and Ahuvya.
 */
public class Geometries implements Intersectable {
    final private List<Intersectable> geometriesList = new LinkedList<Intersectable>();

    /**
     * Default constructor that creates an empty list of geometries.
     */
    public Geometries() {}

    /**
     * constructor. get a list of geometries and add them to geometries list.
     * @param geometries the given list of geometries.
     */
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }

    /**
     * add a list of geometries to the list.
     * @param geometries the given list of geometries.
     */
    public void add(Intersectable... geometries) {
        geometriesList.addAll(List.of(geometries));
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        //Initialize variables to null.
        List<Point> intersections = null;

        // loop goes over the geometries and add the intersections into itemList.
        for (var item : geometriesList) {
            var itemList = item.findIntersections(ray);
            if (itemList != null) {
                //Create the list for the first item that has intersection points.
                if (intersections == null)
                    intersections = new LinkedList<Point>();
                intersections.addAll(itemList);
            }
        }
        return intersections;
    }
}
