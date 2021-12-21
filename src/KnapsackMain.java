import data_structures.Item;
import data_structures.Knapsack;

import java.io.ByteArrayInputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class KnapsackMain {



    public static void main(String[] args) {

        String data = "5\n20";
        System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));

        Scanner scanner = new Scanner(data);

        int m = scanner.nextInt();
        int n = scanner.nextInt();



        ArrayList <Knapsack> knapSackArray = new ArrayList<>();
        ArrayList <Item> itemArray = new ArrayList<>();

        populateKnapsacks(itemArray,knapSackArray,m,n);




        ArrayList <Knapsack> greedyResult = greedyBagFill(knapSackArray, itemArray);

            localOptimaSearch(greedyResult,itemArray);


    }


    public static void populateKnapsacks(ArrayList<Item> itemArray, ArrayList<Knapsack> knapSackArray, int m, int n) {

        Random randomizer = new Random();


        for (int i = 0; i < m; i++) {
            knapSackArray.add(new Knapsack(randomizer.nextInt(7,16)));
        }

        for (int i = 0; i < n; i++) {
            itemArray.add(new Item(randomizer.nextInt(1,9),randomizer.nextInt(1,6)));
        }
    }

    /**
     * Print items in unused bag
     * @param itemArray
     */
    public static void printLeftOverItems (ArrayList <Item> itemArray) {
        for (Item item : itemArray) {
            System.out.println("Item leftover value = " + item.getValue() + ", Item leftover weight = " + item.getWeight());
        }
    }

    public static void sortItems(ArrayList<Item> itemArray) {
        itemArray.sort(Comparator.comparing(Item::getItemEvaluation).reversed());
    }

    public static ArrayList <Knapsack> greedyBagFill(ArrayList<Knapsack> knapSackArray, ArrayList<Item> itemArray) {

        sortItems(itemArray);

        for (Knapsack knapsack : knapSackArray) {

            for (int j = 0; j < itemArray.size(); j++) {

                if (knapsack.itemFits(itemArray.get(j))) {

                    knapsack.insert(itemArray.get(j));
                    itemArray.get(j).setLocationInKnapSack(j);
                    itemArray.remove(j);
                    j = j - 1;
                }
            }

        }
        return knapSackArray;

    }

    public static void localOptimaSearch(ArrayList<Knapsack> greedyResultArray, ArrayList <Item> itemArrayList) {

        int greedyTotalValue = 0;
        ArrayList <Knapsack> localSearchResult = new ArrayList<>();

        System.out.println("Initial result of greedy:" + "\n");
        printKnapsackContents(greedyResultArray);


        for (Knapsack knapsack : greedyResultArray) {
            greedyTotalValue += knapsack.getTotalValue();
        }
            if (spaceAvailable(greedyResultArray) && itemArrayList.size()!=0) {

                for (int i = 0; i < greedyResultArray.size(); i++) {

                     localSearchResult = moveBagContents(greedyResultArray,itemArrayList);

                    if (greedyTotalValue < valueOfAllKnapsacks(localSearchResult)) {
                        System.err.println("Better solution found using local search.");
                        break;
                    }

                }

            }

        System.out.println("Final result of local search:" + "\n");
        printKnapsackContents(localSearchResult);
        System.out.println("GREEDY VALUE = " + greedyTotalValue +", LOCAL OPTIMA VALUE = " + valueOfAllKnapsacks(localSearchResult) + "\n");
        printLeftOverItems(itemArrayList);

    }

    public static void printKnapsackContents(ArrayList <Knapsack> knapsacks) {

        for (int i = 0; i < knapsacks.size(); i++) {
            System.out.println("Bag " + (i + 1) + " weight = " + knapsacks.get(i).getCurrentWeight() + "/" + knapsacks.get(i).getMaximumWeight());
            System.out.println("Bag " + (i + 1) + " value  = " + knapsacks.get(i).getTotalValue() + "\n");
        }

    }

    private static int valueOfAllKnapsacks(ArrayList<Knapsack> knapsacks) {
        int totalValue = 0;
        for(Knapsack sack : knapsacks) totalValue += sack.getTotalValue();
        return totalValue;
    }

    public static ArrayList <Knapsack> moveBagContents(ArrayList <Knapsack> knapsacks, ArrayList <Item> itemArray) {

        ArrayList <Knapsack> arrayCopy = new ArrayList<>(knapsacks);


        for (int j = 0 ; j < arrayCopy.size(); j++) {

            Knapsack sack = arrayCopy.get(j);


            if (sack.leftOverWeight() > 0) {

                int adjacentKnapsackIndex = ((j+1) % arrayCopy.size());

                while (j != adjacentKnapsackIndex ) {

                    Knapsack adjacentKnapsack = arrayCopy.get(adjacentKnapsackIndex);
                    Item adjacentSackHeaviestItem = adjacentKnapsack.returnHeaviest(sack.leftOverWeight());

                    if (adjacentSackHeaviestItem.getWeight() != 0) {

                        adjacentKnapsack.remove(adjacentSackHeaviestItem);

                        sack.insert(adjacentSackHeaviestItem);

                        fillWithLeftOverItems(adjacentKnapsack,itemArray);

                    }

                    adjacentKnapsackIndex = ((adjacentKnapsackIndex+1) % arrayCopy.size());


                }
            }
        }

        return arrayCopy;
    }

    private static void fillWithLeftOverItems(Knapsack adjacentKnapsack, ArrayList <Item> itemArray) {

        for (Item item : itemArray ) {
            if (item.getWeight() <= adjacentKnapsack.leftOverWeight()) {
                adjacentKnapsack.insert(item);
            }
        }

    }


    public static boolean spaceAvailable(ArrayList<Knapsack> knapSackArray) {
        for (Knapsack knapsack : knapSackArray) {
            if (!knapsack.isFull()) {
                return true;
            }
        }
        return false;
    }

}
