package scene;

import geometries.Geometries;
import lighting.AmbientLight;
import lighting.LightSource;
import primitives.Color;

import java.util.LinkedList;
import java.util.List;

/**
 * The class implements a scene.
 * @author Yaniv and Ahuvya.
 */
public class Scene {
    //Scene's name
    public String name;
    //background Color
    public Color background = Color.BLACK;
    public AmbientLight ambientLight = AmbientLight.NONE;
    public Geometries geometries = new Geometries();
    public List<LightSource> lights = new LinkedList<>();

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

}
