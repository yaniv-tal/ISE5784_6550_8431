package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class SpotLight extends PointLight {
    private Vector direction;

    public SpotLight(Color intensity, Point position, Vector direction) {
        super(intensity, position);
        this.direction = direction.normalize();
    }

    @Override
    public SpotLight setKC(double kC) {
        return (SpotLight) super.setKC(kC);
    }
    @Override
    public SpotLight setKL(double kL) {
        return (SpotLight) super.setKL(kL);
    }

    @Override
    public SpotLight setKQ(double kQ) {
        return (SpotLight) super.setKQ(kQ);
    }

    @Override
    public Color getIntensity(Point p) {
        return super.getIntensity(p).scale(Math.max(0, direction.dotProduct(getL(p))));
    }

    @Override
    public Vector getL(Point p) {
        return direction.normalize();
    }


}
