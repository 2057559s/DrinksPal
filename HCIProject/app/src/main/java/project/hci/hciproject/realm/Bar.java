package project.hci.hciproject.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author jake degiovanni
 */

public class Bar extends RealmObject {
    @PrimaryKey private String bar_name;
    private double latitude;
    private double longitude;

    public Bar() {
    }

    public Bar(String bar_name, double latitude, double longitude) {
        this.bar_name = bar_name;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getBar_name() {
        return bar_name;
    }

    public void setBar_name(String bar_name) {
        this.bar_name = bar_name;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
