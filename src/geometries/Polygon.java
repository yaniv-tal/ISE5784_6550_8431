package geometries;

import java.util.List;

import static primitives.Util.alignZero;
import static primitives.Util.isZero;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

/**
 * Polygon class represents two-dimensional polygon in 3D Cartesian coordinate
 * system
 * @author Dan
 */
public class Polygon extends Geometry {
   /** List of polygon's vertices */
   protected final List<Point> vertices;
   /** Associated plane in which the polygon lays */
   protected final Plane       plane;
   /** The size of the polygon - the amount of the vertices in the polygon */
   private final int           size;

   /**
    * Polygon constructor based on vertices list. The list must be ordered by edge
    * path. The polygon must be convex.
    * @param  vertices                 list of vertices according to their order by
    *                                  edge path
    * @throws IllegalArgumentException in any case of illegal combination of
    *                                  vertices:
    *                                  <ul>
    *                                  <li>Less than 3 vertices</li>
    *                                  <li>Consequent vertices are in the same
    *                                  point
    *                                  <li>The vertices are not in the same
    *                                  plane</li>
    *                                  <li>The order of vertices is not according
    *                                  to edge path</li>
    *                                  <li>Three consequent vertices lay in the
    *                                  same line (180&#176; angle between two
    *                                  consequent edges)
    *                                  <li>The polygon is concave (not convex)</li>
    *                                  </ul>
    */
   public Polygon(Point... vertices) {
      if (vertices.length < 3)
         throw new IllegalArgumentException("A polygon can't have less than 3 vertices");
      this.vertices = List.of(vertices);
      size          = vertices.length;

      // Generate the plane according to the first three vertices and associate the
      // polygon with this plane.
      // The plane holds the invariant normal (orthogonal unit) vector to the polygon
      plane         = new Plane(vertices[0], vertices[1], vertices[2]);
      if (size == 3) return; // no need for more tests for a Triangle

      Vector  n        = plane.getNormal();
      // Subtracting any subsequent points will throw an IllegalArgumentException
      // because of Zero primitives.Vector if they are in the same point
      Vector  edge1    = vertices[vertices.length - 1].subtract(vertices[vertices.length - 2]);
      Vector  edge2    = vertices[0].subtract(vertices[vertices.length - 1]);

      // Cross Product of any subsequent edges will throw an IllegalArgumentException
      // because of Zero primitives.Vector if they connect three vertices that lay in the same
      // line.
      // Generate the direction of the polygon according to the angle between last and
      // first edge being less than 180 deg. It is hold by the sign of its dot product
      // with the normal. If all the rest consequent edges will generate the same sign
      // - the polygon is convex ("kamur" in Hebrew).
      boolean positive = edge1.crossProduct(edge2).dotProduct(n) > 0;
      for (var i = 1; i < vertices.length; ++i) {
         // Test that the point is in the same plane as calculated originally
         if (!isZero(vertices[i].subtract(vertices[0]).dotProduct(n)))
            throw new IllegalArgumentException("All vertices of a polygon must lay in the same plane");
         // Test the consequent edges have
         edge1 = edge2;
         edge2 = vertices[i].subtract(vertices[i - 1]);
         if (positive != (edge1.crossProduct(edge2).dotProduct(n) > 0))
            throw new IllegalArgumentException("All vertices must be ordered and the polygon must be convex");
      }
   }

   @Override
   public Vector getNormal(Point point) { return plane.getNormal(); }

   @Override
   public List<GeoPoint> findGeoIntersectionsHelper(Ray ray,double maxDistance) {
      List<GeoPoint> intersection = plane.findGeoIntersections(ray, maxDistance);

      // In case there is no intersection point with the plane of the polygon.
      if (intersection == null)
         return null;

      //check that intersection point is closer to ray origin than
      // max distance parameter
      double distance = intersection.getFirst().point.distance(ray.getHead());
      if(alignZero(distance-maxDistance)>0)
         return null;

      //Checks if the intersection point is inside the polygon.
      Point head = ray.getHead();
      Vector direction = ray.getDirection();
      Vector v1 = vertices.get(0).subtract(head);
      Vector v2 = vertices.get(1).subtract(head);
      double sign = alignZero(direction.dotProduct(v1.crossProduct(v2)));
      if (isZero(sign))
         return null;

      // Boolean variable for checking that all the signs are the same in the calculations for each successive pair of vertices.
      boolean checkSameSign = (sign > 0);
      // Go through each successive pair of vertices in the polygon and check if the ray intersects with the polygon.
      for (int i = vertices.size() - 1; i > 0; --i) {
         v2 = v1;
         v1 = vertices.get(i).subtract(head);
         sign = alignZero(direction.dotProduct(v1.crossProduct(v2)));

         // If the constructed vectors are orthogonal, or the sign isn't the same, the ray does not intersect polygon.
         if (isZero(sign) || checkSameSign != (sign > 0))
            return null;
      }

      // The ray intersects the polygon. returns the intersection point.
      return List.of(new GeoPoint(this,intersection.getFirst().point));
   }

   @Override
   public List<Point> findBVHPoints() {
      if (vertices == null)
         return null;
      double minX = Double.POSITIVE_INFINITY;
      double minY = Double.POSITIVE_INFINITY;
      double minZ = Double.POSITIVE_INFINITY;
      double maxX = Double.NEGATIVE_INFINITY;
      double maxY = Double.NEGATIVE_INFINITY;
      double maxZ = Double.NEGATIVE_INFINITY;
      for (Point v : vertices) {
         minX = Math.min(minX, v.getX());
         minY = Math.min(minY, v.getY());
         minZ = Math.min(minZ, v.getZ());
         maxX = Math.max(maxX, v.getX());
         maxY = Math.max(maxY, v.getY());
         maxZ = Math.max(maxZ, v.getZ());
      }
      return List.of(new Point(minX,minY,minZ),new Point(maxX,maxY,maxZ));
   }
}
