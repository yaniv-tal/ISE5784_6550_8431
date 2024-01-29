package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction;
    }

    public SpotLight setkC(double kC) {
        return (SpotLight) super.setkC(kC);
    }

    public SpotLight setkl(double kl) {
        return (SpotLight) super.setkl(kl);
    }

    public SpotLight setkQ(double kQ) {
        return (SpotLight) super.setkQ(kQ);
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity().scale(Math.max(0,direction.dotProduct(getL(p))));
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }


}
