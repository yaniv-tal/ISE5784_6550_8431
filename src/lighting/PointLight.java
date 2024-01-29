package lighting;

import primitives.Color;
import primitives.Point;
import primitives.Vector;

public class PointLight extends Light implements LightSource {
    private Point position;
    private double kC = 1;
    private double kl = 0;
    private double kQ = 0;

    public PointLight(Color intensity, Point position) {
        super(intensity);
        this.position = position;
    }

    public PointLight setkC(double kC) {
        this.kC = kC;
        return this;
    }

    public PointLight setkl(double kl) {
        this.kl = kl;
        return this;
    }

    public PointLight setkQ(double kQ) {
        this.kQ = kQ;
        return this;
    }

    @Override
    public Color getIntensity(Point p) {
        return intensity.scale(1 / (kC + kl * p.distance(position) + kQ * p.distanceSquared(position)));
    }

    @Override
    public Vector getL(Point p) {
        return p.subtract(position).normalize();
    }
}
