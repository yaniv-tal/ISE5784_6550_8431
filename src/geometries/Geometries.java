package geometries;

import primitives.Point;
import primitives.Ray;

import java.util.LinkedList;
import java.util.List;

public class Geometries implements Intersectable {
    final private List<Intersectable> geometriesList = new LinkedList<Intersectable>();
    public Geometries(){}
    public Geometries(Intersectable... geometries) {
        add(geometries);
    }
    public void add(Intersectable... geometries) {
        for (Intersectable intersectable : geometries)
            geometriesList.addLast(intersectable);
    }

    @Override
    public List<Point> findIntersections(Ray ray) {
        return null;
    }
}
