package data_structures;

import java.util.Comparator;

public class Item {

    private int weight;
    private int value;
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
        return itemEvaluation;
    }

    public void setItemEvaluation(double itemEvaluation) {
        this.itemEvaluation = itemEvaluation;
    }


}
