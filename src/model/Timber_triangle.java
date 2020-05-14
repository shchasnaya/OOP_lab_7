package model;

public class Timber_triangle extends AbstractForm {
    private float height;
    private float side;

    public float getHeight() {
        return height;
    }

    public float getSide() {
        return side;
    }

    public Timber_triangle(Wood wood, float height, float side) {
        super(wood);
        this.height = height;
        this.side = side;
    }

    @Override
    public float volume() {
        return (float) ((Math.sqrt(3)/4)*side*side*height);
    }

    @Override
    public String toString() {
        return "Timber_triangle{" +
                "height=" + height +
                ", side=" + side +
                ", volume" + volume() +
                '}';
    }
}
