import data_structures.Item;
import data_structures.Knapsack;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class KnapsackMain {

    private ArrayList <Knapsack> knapSackArray = new ArrayList<>();
    private ArrayList <Item> itemArray = new ArrayList<>();
    private final Random randomizer = new Random();

    public KnapsackMain(int m, int n) {

        for (int i = 0; i < m; i++) {
            knapSackArray.add(new Knapsack(10));
        }

        for (int i = 0; i < n; i++) {
            itemArray.add(new Item(randomizer.nextInt(1,6),randomizer.nextInt(1,10)));
        }

    }


    public static void main(String[] args) {

        String data = "2\n10";
        System.setIn(new ByteArrayInputStream(data.getBytes(StandardCharsets.UTF_8)));
        Scanner scanner = new Scanner(data);

        int m = scanner.nextInt();
        int n = scanner.nextInt();

        KnapsackMain main = new KnapsackMain(m,n);

        main.fillKnapSacks(main.knapSackArray, main.itemArray);

    }


    public void printLeftOverItems () {
        for (Item item : itemArray) {
            System.out.println("Item leftover value = " + item.getValue() + ", Item leftover weight = " + item.getWeight());
        }
    }




    public void fillKnapSacks (ArrayList <Knapsack> knapSackArray,ArrayList <Item> itemArray) {

        for (int i = 0; i < knapSackArray.size(); i++) {

            for (int j = 0; j < itemArray.size(); j++) {

                if (knapSackArray.get(i).itemFits(itemArray.get(j))) {

                    knapSackArray.get(i).insert(itemArray.get(j));
                    itemArray.get(j).setLocationInKnapSack(j);
                    itemArray.remove(j);
                    j = j-1;
                }
            }

            knapSackArray.get(i).printContents();

            System.out.println("Bag number " + (i+1) );
            System.out.println(knapSackArray.get(i).getCurrentWeight() + " = FINAL WEIGHT");
            System.out.println(knapSackArray.get(i).getTotalValue() + " = FINAL VALUE" + "\n");

        }

        printLeftOverItems();

    }
}
