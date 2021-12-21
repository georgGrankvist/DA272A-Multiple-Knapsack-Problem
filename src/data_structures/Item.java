package data_structures;

public class Item {

    private int weight;
    private double value;
    private int locationInKnapSack;
    private double itemEvaluation;

    public Item(int weight, int value) {
        this.weight = weight;
        this.value = value;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public double getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public int getLocationInKnapSack() {
        return locationInKnapSack;
    }

    public void setLocationInKnapSack(int locationInKnapSack) {
        this.locationInKnapSack = locationInKnapSack;
    }

    public double getItemEvaluation() {
       return itemEvaluation = value / weight;
    }

    public void setItemEvaluation(double itemEvaluation) {
        this.itemEvaluation = itemEvaluation;
    }
}
