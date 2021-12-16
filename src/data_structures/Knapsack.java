package data_structures;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Knapsack {

    private ArrayList <Item> bag = new ArrayList<>();
    private int currentWeight;
    private int maximumWeight;
    private double totalValue;


    public void printContents () {
        for (Item item : bag) {
            System.out.println("Item value = " + item.getValue() + " Item weight = " + item.getWeight() + "");
        }

        System.out.println();
    }

    public ArrayList<Item> getBag() {
        return bag;
    }

    public void setBag(ArrayList<Item> bag) {
        this.bag = bag;
    }

    public int getCurrentWeight() {
        return currentWeight;
    }

    public Knapsack(int maximumWeight) {

        this.maximumWeight = maximumWeight;
    }

    public void insert (Item item) {
        bag.add(item);
        currentWeight += item.getWeight();
        totalValue += item.getValue();
    }

    public void emptyBag () {
        bag.clear();
        currentWeight = 0;
        totalValue = 0;
    }

    public void sortBag () {
        bag.sort(Comparator.comparing(Item::getItemEvaluation));
    }

    public boolean itemFits(Item item) {
        return item.getWeight() <= (maximumWeight - getCurrentWeight());
    }

    public void remove (Item item) {
        bag.remove(item);
        currentWeight -= item.getWeight();
        totalValue -= item.getValue();
    }


    public int getMaximumWeight() {
        return maximumWeight;
    }

    public void setMaximumWeight(int maximumWeight) {
        this.maximumWeight = maximumWeight;
    }

    public double getTotalValue() {
        return totalValue;
    }

    public void setTotalValue(double totalValue) {
        this.totalValue = totalValue;
    }

}
