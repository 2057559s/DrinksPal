package project.hci.hciproject.util;

import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.realm.Drink;
import project.hci.hciproject.realm.DrinkType;

/**
 * @author jake degiovanni
 */

public class PopulateData {

    public static void populateBarsAndDrink() {
        final List<Bar> bars = new ArrayList<>();
        final List<Drink> drinks = new ArrayList<>();
        final List<DrinkType> type = new ArrayList<>();

        Realm realm = Realm.getDefaultInstance();
        
        DrinkType drinkType = new DrinkType("Beer", 0);
        type.add(drinkType);
        drinkType = new DrinkType("Spirit", 1);
        type.add(drinkType);
        drinkType = new DrinkType("Whiskey", 2);
        type.add(drinkType);
        drinkType = new DrinkType("Wine", 3);
        type.add(drinkType);
        
        Bar new_bar = new Bar("Curlers Rest", 55.875384, -4.293272);
        Drink drink = new Drink(0, "Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Heineken", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4, "Stella Artois", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(5, "Budweiser", 3.50, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(6, "Absolut with coke", 3.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(7, "Jameson Irish Whiskey", 4.00, new_bar,type.get(2));
        drinks.add(drink);
        drink = new Drink(8, "Crown Royal", 4.50, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(9, "Woodford Reserve", 5.00, new_bar, type.get(2) );
        drinks.add(drink);
        drink = new Drink(10, "Knob Creek", 4.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("Vodka Wodka", 55.874850, -4.292956);
        drink = new Drink(0, "Tennents", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Carling", 3.50, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Heineken", 4.00, new_bar, type.get(0));
        bars.add(new_bar);
        drink = new Drink(3, "Smirnoff with coke", 1.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(8, "Woodford Reserve", 5.00, new_bar, type.get(2) );
        drinks.add(drink);
        
        new_bar = new Bar("Brel", 55.874704, -4.293089);
        bars.add(new_bar);
        drink = new Drink(0 ,"Corona", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1 ,"Blue", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2 ,"Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3 ,"Knob Creek", 3.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4 ,"Crown Royal", 3.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("Bar Soba Byre's Road", 55.873606, -4.295536);
        bars.add(new_bar);
        drink = new Drink(0, "Jameson Irish Whiskey", 6.00, new_bar,type.get(2));
        drinks.add(drink);
        drink = new Drink(1, "Crown Royal", 7.00 , new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(2, "Carling", 2.00, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Coors Light", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Budweiser", 3.00, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(5, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("Bar Gumbo", 55.871993, -4.297893);
        bars.add(new_bar);
        drink = new Drink(0, "Carling", 2.00, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Budweiser", 3.50, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Bud Light", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4 ,"Crown Royal", 4.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(5 ,"Cloudy Bay Shardonnay", 3.00, new_bar, type.get(3));
        drinks.add(drink);


        new_bar = new Bar("Ubiquitous Chip", 55.875038, -4.293001);
        bars.add(new_bar);
        drink = new Drink(0 ,"Blue", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1 ,"Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2 ,"Cloudy Bay Shardonnay", 5.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(3, "Bud Light", 2.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 1.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("Tennent's Bar", 55.874363, -4.295178);
        bars.add(new_bar);
        drink = new Drink(0, "Heineken", 2.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Busch Lager", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Keiths", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("Record Factory", 55.871043, -4.299111);
        bars.add(new_bar);
        drink = new Drink(0, "Faustino 1", 2.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(1, "Tennents", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Carling", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Busch Lager", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Grey Goose with coke", 4.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Crown Loyal", 6.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("Grosvenor Cafe", 55.874766, -4.293256);
        bars.add(new_bar);
        drink = new Drink(0, "Stella Artois", 4.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Busch Lager", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Tennents", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Knob Creek", 2.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Grey Goose with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("The Lane", 55.874639, -4.293144);
        bars.add(new_bar);
        drink = new Drink(0 ,"Coor Lights", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1 ,"Bud Light", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2 ,"Cloudy Bay Shardonnay", 6.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(3, "Tennents", 2.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("Jinty McGuinty's Irish Bar", 55.874802, -4.292731);
        bars.add(new_bar);
        drink = new Drink(0 ,"Blue", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1 ,"Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2 ,"Segla", 5.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(3, "Bud Light", 4.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 1.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Crown Loyal", 4.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("The Aragon", 55.873254, -4.296433);
        bars.add(new_bar);
        drink = new Drink(0, "Stella Artois", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Crown Royal", 2.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("Coopers", 55.875633, -4.282541);
        bars.add(new_bar);
        new_bar = new Bar("Bank Street Bar Kitchen", 55.873236, -4.284290);
        bars.add(new_bar);
        drink = new Drink(0, "Budweiser", 2.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Heineken", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Grey Goose with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Knob Creek", 4.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("The Belle", 55.876685, -4.285806);
        bars.add(new_bar);
        drink = new Drink(0, "Tennents", 2.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Heineken", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Keiths", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Jameson Irish Whiskey", 6.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 1.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(5, "Grey Goose with coke", 3.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Knob Creek", 4.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("Hillhead Bookclub", 55.877022, -4.290731);
        bars.add(new_bar); // 16
        drink = new Drink(0, "Heineken", 3.00, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Coors Light", 3.50, new_bar,type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Bud Light", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4 ,"Knob Creek", 4.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(5 ,"Cloudy Bay Shardonnay", 3.00, new_bar, type.get(3));
        drinks.add(drink);


        new_bar = new Bar("Oran Mor", 55.877714, -4.289744);
        bars.add(new_bar);
        drink = new Drink(0, "Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Bud Light", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Heineken", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Smirnoff with coke", 1.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(4, "Stella Artois", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(5, "Budweiser", 3.50, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(5, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Jameson Irish Whiskey", 7.00, new_bar,type.get(2));
        drinks.add(drink);
        drink = new Drink(7, "Crown Royal", 3.50, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(8, "Woodford Reserve", 4.00, new_bar, type.get(2) );
        drinks.add(drink);
        drink = new Drink(9, "Knob Creek", 6.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(9, "Grey Goose with coke", 4.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(10, "Cloudy Bay Shardonnay", 6.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(11, "Coors Light", 4.50, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(12, "Keiths", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(13 ,"Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);


        new_bar = new Bar("The Sparkle Horse", 55.871587, -4.300365);
        bars.add(new_bar);
        drink = new Drink(0, "Busch Lager", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Bud Light", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Tennents", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Crown Royal", 2.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Absolut with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Grey Goose with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);


        new_bar = new Bar("The Lismore", 55.870971, -4.301568);
        bars.add(new_bar);
        drink = new Drink(0, "Carling", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1, "Corona", 4.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2, "Heineken", 2.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(3, "Knob Creek", 2.00, new_bar, type.get(2));
        drinks.add(drink);
        drink = new Drink(4, "Smirnoff with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(5, "Grey Goose with coke", 2.00, new_bar, type.get(1));
        drinks.add(drink);
        drink = new Drink(6, "Crown Royal", 7.00, new_bar, type.get(2));
        drinks.add(drink);


        new_bar = new Bar("Three Judges", 55.870375, -4.299449);
        bars.add(new_bar);
        drink = new Drink(0 ,"Coors Light", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(1 ,"Carling", 3.00, new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(2 ,"Cloudy Bay Shardonnay", 5.00, new_bar, type.get(3));
        drinks.add(drink);
        drink = new Drink(3, "Bud Light", 3.00,new_bar, type.get(0));
        drinks.add(drink);
        drink = new Drink(4, "Grey Goose with coke", 3.00, new_bar, type.get(1));
        drinks.add(drink);

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.copyToRealmOrUpdate(bars);
                realm.copyToRealmOrUpdate(drinks);
                realm.copyToRealmOrUpdate(type);
            }
        });

        realm.close();
    }
}
