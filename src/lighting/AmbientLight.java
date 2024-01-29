package lighting;

import primitives.Color;
import primitives.Double3;

/**
 * The class implements ambient lighting.
 * @author Yaniv and Ahuvya.
 */
public class AmbientLight extends Light{
    /**
     * Constructor for ambient lighting.
     * @param IA for light intensity.
     * @param KA for attenuation coefficient.
     */
    public AmbientLight(Color IA, Double3 KA) {
        super(IA.scale(KA));
    }

    public static AmbientLight NONE = new AmbientLight(Color.BLACK, 0d);

    /**
     * Constructor for ambient lighting.
     * @param IA for light intensity.
     * @param KA for attenuation coefficient.
     */
    public AmbientLight(Color IA, Double KA) {
        super(IA.scale(KA));
    }

}
