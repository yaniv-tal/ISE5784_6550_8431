package renderer;

import static java.awt.Color.*;

import geometries.Plane;
import geometries.Polygon;
import lighting.PointLight;
import org.junit.jupiter.api.Test;

import geometries.Sphere;
import geometries.Triangle;
import lighting.AmbientLight;
import lighting.SpotLight;
import primitives.*;
import renderer.*;
import scene.Scene;

/** Tests for reflection and transparency functionality, test for partial
 * shadows
 * (with transparency)
 * @author dzilb */
public class ReflectionRefractionTests {
   /** Scene for the tests */
   private final Scene          scene         = new Scene("Test scene");
   /** Camera builder for the tests with triangles */
   private final Camera.Builder cameraBuilder = Camera.getBuilder()
           .setDirection(new Vector(0,0,-1),new Vector(0,1,0))
           .setRayTracer(new SimpleRayTracer(scene));

   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheres() {
      scene.geometries.add(
              new Sphere(50d, new Point(0, 0, -50)).setEmission(new Color(BLUE))
                      .setMaterial(new Material().setKD(0.4).setKS(0.3).setNShininess(100).setKT(0.3)),
              new Sphere(25d, new Point(0, 0, -50)).setEmission(new Color(RED))
                      .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(100)));
      scene.lights.add(
              new SpotLight(new Color(1000, 600, 0), new Point(-100, -100, 500), new Vector(-1, -1, -2))
                      .setKL(0.0004).setKQ(0.0000006));

      cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
              .setVpSize(150, 150)
              .setImageWriter(new ImageWriter("refractionTwoSpheres", 500, 500))
              .setUseAntiAliasing(true,9)
              .build()
              .renderImage()
              .writeToImage();
   }




   /** Produce a picture of a sphere lighted by a spot light */
   @Test
   public void twoSpheresOnMirrors() {
      scene.geometries.add(
              new Sphere(400d, new Point(-950, -900, -1000)).setEmission(new Color(0, 50, 100))
                      .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)
                              .setKT(new Double3(0.5, 0, 0))),
              new Sphere( 200d,new Point(-950, -900, -1000)).setEmission(new Color(100, 50, 20))
                      .setMaterial(new Material().setKD(0.25).setKS(0.25).setNShininess(20)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(670, 670, 3000))
                      .setEmission(new Color(20, 20, 20))
                      .setMaterial(new Material().setKR(1d)),
              new Triangle(new Point(1500, -1500, -1500), new Point(-1500, 1500, -1500),
                      new Point(-1500, -1500, -2000))
                      .setEmission(new Color(20, 20, 20))
                      .setMaterial(new Material().setKR(new Double3(0.5, 0, 0.4))));
      scene.setAmbientLight(new AmbientLight(new Color(255, 255, 255), 0.1));
      scene.lights.add(new SpotLight(new Color(1020, 400, 400), new Point(-750, -750, -150), new Vector(-1, -1, -4))
              .setKL(0.00001).setKQ(0.000005));

      cameraBuilder.setLocation(new Point(0, 0, 10000)).setVpDistance(10000)
              .setVpSize(2500, 2500)
              .setImageWriter(new ImageWriter("reflectionTwoSpheresMirrored", 500, 500))
              .build()
              .renderImage()
              .writeToImage();
   }

   /** Produce a picture of a two triangles lighted by a spot light with a
    * partially
    * transparent Sphere producing partial shadow */
   @Test
   public void trianglesTransparentSphere() {
      scene.geometries.add(
              new Triangle(new Point(-150, -150, -115), new Point(150, -150, -135),
                      new Point(75, 75, -150))
                      .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)),
              new Triangle(new Point(-150, -150, -115), new Point(-70, 70, -140), new Point(75, 75, -150))
                      .setMaterial(new Material().setKD(0.5).setKS(0.5).setNShininess(60)),
              new Sphere( 30d,new Point(60, 50, -50)).setEmission(new Color(BLUE))
                      .setMaterial(new Material().setKD(0.2).setKS(0.2).setNShininess(30).setKT(0.6)));
      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
              new SpotLight(new Color(700, 400, 400), new Point(60, 50, 0), new Vector(0, 0, -1))
                      .setKL(4E-5).setKQ(2E-7));

      cameraBuilder.setLocation(new Point(0, 0, 1000)).setVpDistance(1000)
              .setVpSize(200, 200)
              .setImageWriter(new ImageWriter("refractionShadow", 600, 600))
              .build()
              .renderImage()
              .writeToImage();
   }

   @Test
   void impressiveImage(){
      scene.geometries.add(
              new Sphere(10, Point.ZERO)
                      .setEmission(new Color(BLUE))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Plane(new Point(0,0,-40),Vector.Z)
                      .setEmission(new Color(RED))
                      .setMaterial(new Material().setKS(0.5).setKD(0.5).setKR(1d).setNShininess(60)),

              new Polygon(new Point(15,15,30),new Point(15,-15,30),new Point(-15,-15,30),new Point(-15,15,30))
                      .setEmission(new Color(CYAN))
                      .setMaterial(new Material().setNShininess(60).setKD(0.5).setKS(0.5)),

              new Triangle(new Point(15,15,30),new Point(15,-15,30),new Point(0,0,9))
                      .setEmission(new Color(29,186,218))
                      .setMaterial(new Material().setKS(0.5).setKD(0.5).setNShininess(60).setKR(1d)),

              new Triangle(new Point(15,-15,30),new Point(-15,-15,30),new Point(0,0,9))
                      .setEmission(new Color(29,186,218))
                      .setMaterial(new Material().setKS(0.5).setKD(0.5).setNShininess(60).setKR(1d)),

              new Triangle(new Point(-15,-15,30),new Point(-15,15,30),new Point(0,0,9))
                      .setEmission(new Color(29,186,218))
                      .setMaterial(new Material().setKS(0.5).setKD(0.5).setNShininess(60).setKR(1d)),

              new Triangle(new Point(-15,15,30),new Point(15,15,30),new Point(0,0,9))
                      .setEmission(new Color(29,186,218))
                      .setMaterial(new Material().setKS(0.5).setKD(0.5).setNShininess(60).setKR(1d)),

              new Sphere(2, new Point(0,0,-30))
                      .setEmission(new Color(ORANGE))
                      .setMaterial(new Material().setNShininess(50).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(15,0,10))
                      .setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(-15,0,10))
                      .setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(0,15,10))
                      .setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(0,-15,10))
                      .setEmission(new Color(YELLOW))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),
              new Sphere(3, new Point(10.6,10.6,10))
                      .setEmission(new Color(ORANGE))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(10.6,-10.6,10))
                      .setEmission(new Color(ORANGE))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(-10.6,-10.6,10))
                      .setEmission(new Color(ORANGE))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)),

              new Sphere(3, new Point(-10.6,10.6,10))
                      .setEmission(new Color(ORANGE))
                      .setMaterial(new Material().setNShininess(60).setKT(0.6).setKS(0.5).setKD(0.5)));


      scene.setAmbientLight(new AmbientLight(new Color(WHITE), 0.15));
      scene.lights.add(
              new PointLight(new Color(BLUE),Point.ZERO)
                      .setKL(4E-5).setKQ(2E-7));
      scene.lights.add(new SpotLight(new Color(171,0,255),new Point(15,0,10),Point.ZERO.subtract(new Point(15,0,10)))
              .setKL(4E-5).setKQ(2E-7));
      scene.lights.add(new SpotLight(new Color(171,0,255),new Point(-15,0,10),Point.ZERO.subtract(new Point(-15,0,10)))
              .setKL(4E-5).setKQ(2E-7));
      Camera.Builder cameraBuilder1 = Camera.getBuilder()
              .setRayTracer(new SimpleRayTracer(scene))
              .setVpSize(900, 900);

      Camera camera = cameraBuilder1.setLocation(new Point(40, 70, 90))
              .setDirection(new Vector(-1,-1,-2),new Vector(-1,-1,1))
              .setVpDistance(965)
              .setImageWriter(new ImageWriter("geometriesImpressiveImage", 500, 500))
              .setUseAntiAliasing(true,4)
              .setUseSoftShadows(true,6,2,2)
              .setPrintInterval(0.1)
              .setThreadsCount(4)
              .build();
      camera.renderImage();
      camera.writeToImage();

   }

}