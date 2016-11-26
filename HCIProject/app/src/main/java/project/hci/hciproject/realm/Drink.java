package project.hci.hciproject.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;

/**
 * @author jake degiovanni
 */

public class Drink extends RealmObject {
    @PrimaryKey private long id;
    private String drink_name;
    private double price;
    private Bar bar;
    private String type;

    public Drink() {
    }

    public Drink(long id, String drink_name, double price, Bar bar, String type) {
        this.id = id;
        this.drink_name = drink_name;
        this.price = price;
        this.bar = bar;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getDrink_name() {
        return drink_name;
    }

    public void setDrink_name(String drink_name) {
        this.drink_name = drink_name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Bar getBar() {
        return bar;
    }

    public void setBar(Bar bar) {
        this.bar = bar;
    }

    public String getType(){return type;}

    public void setType(){this.type = type;}

}
