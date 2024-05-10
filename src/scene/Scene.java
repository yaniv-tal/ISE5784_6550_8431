package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import primitives.Point;
import geometries.Intersectable;
/**
 * The class implements a scene.
 * @author Yaniv and Ahuvya.
 */
public class Scene {
    //Scene's name
    public String name;
    //background Color
    public Color background = Color.BLACK;
    //ambient Light
    public AmbientLight ambientLight = AmbientLight.NONE;
    //list for the geometries objects
    public Geometries geometries = new Geometries();
    //list for the Light Sources
    public List<LightSource> lights = new LinkedList<>();
    public int kSize;
    /**
     * setter for the field 'lights'.
     * @param lights represents the getting list to set.
     * @return the scene.
     */
    public Scene setLights(List<LightSource> lights) {
        this.lights = lights;
        return this;
    }

    /**
     * Constructor for Scene.
     * @param name for the name of the scene.
     */
    public Scene(String name) {
        this.name = name;
    }

    /**
     * setter for the background.
     * @param background for the background of the scene.
     * @return the scene.
     */
    public Scene setBackground(Color background) {
        this.background = background;
        return this;
    }

    /**
     * setter for the ambient light.
     * @param ambientLight for the ambient light of the scene.
     * @return the scene.
     */
    public Scene setAmbientLight(AmbientLight ambientLight) {
        this.ambientLight = ambientLight;
        return this;
    }
    //חלק שלישי מכאן
    public Scene setKMeans(int kSize) {
        // Set the size of k for KMeans clustering
        this.kSize = kSize;
        return this; // Return the Scene object
    }
    public static class KMeans {
        public List<List<Integer>> cluster(List<Point> points, int k) {
            List<List<Integer>> clusters = new ArrayList<>();
            // Step 1: Initialize centroids randomly
            List<Point> centroids = initializeCentroids(points, k);
            // Iterate until convergence
            boolean converged = false;
            while (!converged) {
                // Step 2: Assign each point to the nearest centroid
                List<List<Integer>> newClusters = assignPointsToCentroids(points, centroids);
                // Step 3: Update centroids based on the mean of the points in each cluster
                List<Point> newCentroids = updateCentroids(points, newClusters);
                // Step 4: Check for convergence
                converged = centroids.equals(newCentroids);
                centroids = newCentroids;
                clusters = newClusters;
            }
            return clusters;
        }
        private List<Point> initializeCentroids(List<Point> points, int k) {
            // Randomly select k points as initial centroids
            List<Point> centroids = new ArrayList<>();
            Random rand = new Random();
            for (int i = 0; i < k; i++) {
                int index = rand.nextInt(points.size());
                centroids.add(points.get(index));
            }
            return centroids;
        }
        private List<List<Integer>> assignPointsToCentroids(List<Point> points, List<Point> centroids) {
            // Assign each point to the nearest centroid
            List<List<Integer>> clusters = new ArrayList<>();
            for (int i = 0; i < centroids.size(); i++) {
                clusters.add(new ArrayList<>());
            }
            for (int i = 0; i < points.size(); i++) {
                Point point = points.get(i);
                double minDistance = Double.POSITIVE_INFINITY;
                int clusterIndex = -1;
                for (int j = 0; j < centroids.size(); j++) {
                    double distance = point.distance(centroids.get(j));
                    if (distance < minDistance) {
                        minDistance = distance;
                        clusterIndex = j;
                    }
                }
                clusters.get(clusterIndex).add(i);
            }
            return clusters;
        }
        private List<Point> updateCentroids(List<Point> points, List<List<Integer>> clusters) {
            // Update centroids based on the mean of the points in each cluster
            List<Point> centroids = new ArrayList<>();
            for (List<Integer> cluster : clusters) {
                if (cluster.isEmpty()) {
                    // If a cluster is empty, keep the centroid unchanged
                    centroids.add(new Point(0, 0, 0)); // Add dummy point, not used in distance calculation
                    continue;
                }
                double sumX = 0, sumY = 0, sumZ = 0;
                for (int index : cluster) {
                    Point point = points.get(index);
                    sumX += point.getX();
                    sumY += point.getY();
                    sumZ += point.getZ();
                }
                double meanX = sumX / cluster.size();
                double meanY = sumY / cluster.size();
                double meanZ = sumZ / cluster.size();
                centroids.add(new Point(meanX, meanY, meanZ));
            }
            return centroids;
        }
    }
    /**
     * Sets the geometries of the scene.
     *
     * @param geometries The geometries to be set.
     * @return The Scene object with the updated geometries.
     */
    public Scene setGeometries(Geometries geometries) {
        List<Intersectable> objects = geometries.getGeometries();
        // Calculate centers of objects
        List<Point> centers = new ArrayList<>();
        for (Intersectable i : objects) {
            List<Point> bvhPoints= i.findBVHPoints();
            Point avg = new Point((bvhPoints.get(0).getX() + bvhPoints.get(1).getX() ) / 2, (bvhPoints.get(0).getY()  + bvhPoints.get(1).getY() ) / 2, (bvhPoints.get(0).getZ()  + bvhPoints.get(1).getZ() ) / 2);
            centers.add(avg);
        }
        KMeans kMeans = new KMeans();
        List<List<Integer>> clusters = kMeans.cluster(centers, kSize);
        // Build hierarchy based on clusters
        Geometries hierarchies = new Geometries();
        for (List<Integer> cluster : clusters) {
            Geometries clusterObjects = new Geometries();
            for (int index : cluster) {
                clusterObjects.add(objects.get(index));
            }
            if (clusterObjects.getGeometries().size() == 1) {
                hierarchies.add(new Geometries(clusterObjects.getGeometries().get(0)));
            } else {
                hierarchies.add(new Geometries(clusterObjects.getGeometries().toArray(new Intersectable[0])));
            }
        }
        this.geometries = hierarchies;
        return this; // Return the Scene object
    }

}
