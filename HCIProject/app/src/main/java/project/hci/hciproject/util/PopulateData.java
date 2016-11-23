package project.hci.hciproject.util;

import java.util.ArrayList;
import java.util.List;

import project.hci.hciproject.realm.Bar;

/**
 * @author jake degiovanni
 */

public class PopulateData {

    public static List<Bar> populateBarsAndDrink() {
        List<Bar> bars = new ArrayList<>();

        Bar new_bar = new Bar("Curlers Rest", 55.875384, -4.293272);
        bars.add(new_bar);
        new_bar = new Bar("Vodka Wodka", 55.874850, -4.292956);
        bars.add(new_bar);
        new_bar = new Bar("Brel", 55.874704, -4.293089);
        bars.add(new_bar);
        new_bar = new Bar("Bar Soba Byre's Road", 55.873606, -4.295536);
        bars.add(new_bar);
        new_bar = new Bar("Bar Gumbo", 55.871993, -4.297893);
        bars.add(new_bar);
        new_bar = new Bar("Ubiquitous Chip", 55.875038, -4.293001);
        bars.add(new_bar);
        new_bar = new Bar("Tennent's Bar", 55.874363, -4.295178);
        bars.add(new_bar);
        new_bar = new Bar("Record Factory", 55.871043, -4.299111);
        bars.add(new_bar);
        new_bar = new Bar("Grosvenor Cafe", 55.874766, -4.293256);
        bars.add(new_bar);
        new_bar = new Bar("The Lane", 55.874639, -4.293144);
        bars.add(new_bar);
        new_bar = new Bar("Jinty McGuinty's Irish Bar", 55.874802, -4.292731);
        bars.add(new_bar); // 11
        new_bar = new Bar("The Aragon", 55.873254, -4.296433);
        bars.add(new_bar);
        new_bar = new Bar("Coopers", 55.875633, -4.282541);
        bars.add(new_bar);
        new_bar = new Bar("Bank Street Bar Kitchen", 55.873236, -4.284290);
        bars.add(new_bar);
        new_bar = new Bar("The Belle", 55.876685, -4.285806);
        bars.add(new_bar);
        new_bar = new Bar("Hillhead Bookclub", 55.877022, -4.290731);
        bars.add(new_bar); // 16
        new_bar = new Bar("Oran Mor", 55.877714, -4.289744);
        bars.add(new_bar);
        new_bar = new Bar("The Sparkle Horse", 55.871587, -4.300365);
        bars.add(new_bar);
        new_bar = new Bar("The Lismore", 55.870971, -4.301568);
        bars.add(new_bar);
        new_bar = new Bar("Three Judges", 55.870375, -4.299449);
        bars.add(new_bar);

        return bars;
    }
}
