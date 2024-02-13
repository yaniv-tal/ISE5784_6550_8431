package geometries;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import primitives.Point;
import primitives.Ray;
import primitives.Vector;

import java.util.List;

/**
 * Testing Polygons
 * @author Dan
 */
public class PolygonTests {
   /**
    * Delta value for accuracy when comparing the numbers of type 'double' in
    * assertEquals
    */
   private final double DELTA = 0.000001;

   /** Test method for {@link geometries.Polygon#Polygon(primitives.Point...)}. */
   @Test
   public void testConstructor() {
      // ============ Equivalence Partitions Tests ==============

      // TC01: Correct concave quadrangular with vertices in correct order
      assertDoesNotThrow(() -> new Polygon(new Point(0, 0, 1),
                                           new Point(1, 0, 0),
                                           new Point(0, 1, 0),
                                           new Point(-1, 1, 1)),
                         "Failed constructing a correct polygon");

      // TC02: Wrong vertices order
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(0, 1, 0), new Point(1, 0, 0), new Point(-1, 1, 1)), //
                   "Constructed a polygon with wrong order of vertices");

      // TC03: Not in the same plane
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 2, 2)), //
                   "Constructed a polygon with vertices that are not in the same plane");

      // TC04: Concave quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0.5, 0.25, 0.5)), //
                   "Constructed a concave polygon");

      // =============== Boundary Values Tests ==================

      // TC10: Vertex on a side of a quadrangular
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0),
                                     new Point(0, 0.5, 0.5)),
                   "Constructed a polygon with vertix on a side");

      // TC11: Last point = first point
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 0, 1)),
                   "Constructed a polygon with vertice on a side");

      // TC12: Co-located points
      assertThrows(IllegalArgumentException.class, //
                   () -> new Polygon(new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(0, 1, 0)),
                   "Constructed a polygon with vertice on a side");

   }

   /** Test method for {@link geometries.Polygon#getNormal(primitives.Point)}. */
   @Test
   public void testGetNormal() {
      // ============ Equivalence Partitions Tests ==============
      // TC01: There is a simple single test here - using a quad
      Point[] pts =
         { new Point(0, 0, 1), new Point(1, 0, 0), new Point(0, 1, 0), new Point(-1, 1, 1) };
      Polygon pol = new Polygon(pts);
      // ensure there are no exceptions
      assertDoesNotThrow(() -> pol.getNormal(new Point(0, 0, 1)), "");
      // generate the test result
      Vector result = pol.getNormal(new Point(0, 0, 1));
      // ensure |result| = 1
      assertEquals(1, result.length(), DELTA, "Polygon's normal is not a unit vector");
      // ensure the result is orthogonal to all the edges
      for (int i = 0; i < 3; ++i)
         assertEquals(0d, result.dotProduct(pts[i].subtract(pts[i == 0 ? 3 : i - 1])), DELTA,
                      "Polygon's normal is not orthogonal to one of the edges");
   }


   /**
    * Test method for {@link Intersectable#findGeoIntersections(Ray)}.
    */
   @Test
   void findGeoIntersections() {
      final Point p300 = new Point(3, 0, 0);
      final Point p030 = new Point(0, 3, 0);
      final Point p000 = new Point(0, 0, 0);
      final Point p101 = new Point(1, 0, 1);
      final Point p301 = new Point(3, 0, 1);
      final Point p401 = new Point(4, 0, 1);
      final Point pm1m11 = new Point(-1, -1, 1);
      final Point p999 = new Point(9, 9, 9);
      final Vector v00m1 = new Vector(0, 0, -1);
      final Point p111 = new Point(1, 1, 1);
      final Point p110 = new Point(1, 1, 0);

      //The polygon that was tested
      Polygon polygon = new Polygon(p300, p030, p000);
      final var exp1 = List.of(new Intersectable.GeoPoint(polygon,p110));

      // ============ Equivalence Partitions Tests ==============
      // TC01: The intersection point with the "contained" plane is outside the polygon - "Against" one of the sides
      assertNull(polygon.findGeoIntersections(new Ray(p999, v00m1)), "Ray's line out of polygon");
      // TC02: The intersection point with the "contained" plane is outside the polygon - "Against" one of the vertices
      assertNull(polygon.findGeoIntersections(new Ray(pm1m11, v00m1)), "Ray's line out of polygon");
      // TC03: The intersection point with the "contained" plane is inside the polygon
      final var result1 = polygon.findGeoIntersections(new Ray(p111, v00m1));
      assertEquals(1, result1.size(), "Wrong number of points");
      assertEquals(exp1, result1, "Ray crosses Plane");

      // =============== Boundary Values Tests ==================
      // TC11: The intersection point with the "contained" plane is on one of the sides
      assertNull(polygon.findGeoIntersections(new Ray(p101, v00m1)), "Ray's line out of polygon");
      // TC12: The intersection point with the "contained" plane is on one of the vertices
      assertNull(polygon.findGeoIntersections(new Ray(p301, v00m1)), "Ray's line out of polygon");
      // TC13: The intersection point with the "contained" plane is on the continuation of one of the sides
      assertNull(polygon.findGeoIntersections(new Ray(p401, v00m1)), "Ray's line out of polygon");
   }


   /**
    * Test method for
    * {@link geometries.Polygon#findGeoIntersections(Ray, double)}.
    */
   @Test
   void testDistanceIntersections() {
      Polygon testPolygon = new Polygon(new Point(1, 1, 0), new Point(1, -1, 0), new Point(-1, -1, 0), new Point(-1, 1, 0));
      // ============ Equivalence Partitions Tests ==============
      // EP02: there are intersections farther than max distance
      assertNull(testPolygon.findGeoIntersections(new Ray(new Point(0, 0, -10), Vector.Z), 9), "farther than maxDistance");
      // =============== Boundary Values Tests ==================
      // BV01: there is intersections on max distance
      assertEquals(1, testPolygon.findGeoIntersections(new Ray(new Point(0, 0, -10), Vector.Z), 10).size(), "at max distance");
   }
}
