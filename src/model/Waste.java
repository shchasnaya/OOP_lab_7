package model;

public class Waste  implements IWeight {
    private float weight;

    public Waste(float weight) throws Exception {
        super();
        setWeight(weight);
    }

    @Override
    public float weight() {
        return weight;
    }

    @Override
    public String toString() {
        return "Waste:  weight - " + weight + "kg";
    }

    public void setWeight(float weight) throws Exception {
        if(weight < 20 || weight > 100)
            throw new Exception(weight +
                    " is not correct weight.\n"
                    + "Must be from 20 to 100kg.");
        this.weight = weight;
    }
}
