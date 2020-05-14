package model;

//Цей клас реалізує сутность «брус».

public class Timber extends AbstractForm {
    private float length;
    private float width;
    private float height;

    public Timber(Wood wood, float length, float height, float width) throws Exception {
        super(wood);
        setLength(length);
        setHeight(height);
        setWidth(width);
    }

    public float getLength() {
        return length;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float volume() {
        return length * height * width;
    }

    @Override
    public String toString() {
        return "Timber:  " +
                "wood - " + wood +
                ", weight - " + weight() +
                "cm2, volume - " + volume() + "cm3";
    }

    public void setLength(float length) throws Exception {
        if(length < 20 || length > 800)
            throw new Exception(length +
                    " is not correct weight.\n"
                    + "Must be from 20 to 800cm.");
        this.length = length;
    }

    public void setWidth(float width) throws Exception {
        if(width < 20 || width > 800)
            throw new Exception(width +
                    " is not correct weight.\n"
                    + "Must be from 20 to 800cm.");
        this.width = width;
    }

    public void setHeight(float height) throws Exception {
        if(height < 20 || height > 800)
            throw new Exception(height +
                    " is not correct weight.\n"
                    + "Must be from 20 to 800cm.");
        this.height = height;
    }
}
