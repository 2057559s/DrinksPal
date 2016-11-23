package project.hci.hciproject;
import java.util.ArrayList;
/**
 * Created by nicholassaunderson on 17/11/2016.
 */

public class Item {
    private String itemName;
    private boolean itemAvailable;

    ArrayList<String> bars;
    ArrayList<String> beers;

    public Item(String name, boolean available) {
        itemName = name;
        itemAvailable= available;
    }


    public String getName() {
        return itemName;
    }

    public boolean isAvailable() {
        return itemAvailable;
    }

    private static int lastItemId = 0;

    public static ArrayList<String> createContactsList(int numItems) {
        ArrayList<String> bars = new ArrayList<String>();

        for (int i = 1; i <= numItems; i++) {

            bars.add("Bar: ");
        }

        return bars;
    }
}