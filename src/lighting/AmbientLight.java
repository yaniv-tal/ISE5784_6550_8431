package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The class implements ambient lighting.
 * @author Yaniv and Ahuvya.
 */
public class AmbientLight {
    /**
     * Constructor for ambient lighting.
     * @param IA for light intensity.
     * @param KA for attenuation coefficient.
     */
    public AmbientLight(Color IA, Double3 KA) {
        intensity = IA.scale(KA);
    }

    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);
    final private Color intensity;

    /**
     * Constructor for ambient lighting.
     * @param IA for light intensity.
     * @param KA for attenuation coefficient.
     */
    public AmbientLight(Color IA, Double KA) {
        intensity = IA.scale(KA);
    }

    /**
     * getter for intensity.
     * @return the intensity.
     */
    public Color getIntensity(){
        return intensity;
    }
}
