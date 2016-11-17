package project.hci.hciproject;
import java.util.ArrayList;
/**
 * Created by nicholassaunderson on 17/11/2016.
 */

public class Item {
    private String itemName;
    private boolean itemAvailable;

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

    public static ArrayList<Item> createContactsList(int numItems) {
        ArrayList<Item> items = new ArrayList<Item>();

        for (int i = 1; i <= numItems; i++) {
            items.add(new Item("Item " + ++lastItemId, i <= numItems / 2));
        }

        return items;
    }
}