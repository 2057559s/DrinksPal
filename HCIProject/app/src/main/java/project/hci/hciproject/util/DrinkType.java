package project.hci.hciproject.util;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import io.realm.annotations.RealmClass;
import project.hci.hciproject.realm.Drink;

/**
 * Created by thevj on 27/11/16.
 */

public class DrinkType extends RealmObject {
    @PrimaryKey private String type;
    private long drinkid;

    public DrinkType() {

    }

    public DrinkType(String type, long drinkid) {
        this.type = type;
        this.drinkid = drinkid;
    }

    public String getDrinkType() {
        return type;
    }

    public void setDrinkType(String type) {
        this.type = type;
    }

    public Long getDrink() {return drinkid;}

    public void setDrink(Long drinkid){this.drinkid = drinkid;}

}