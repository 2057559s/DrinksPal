package project.hci.hciproject.realm;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

/**
 * @author jake degiovanni
 */

public class Bar extends RealmObject {
    @PrimaryKey private String bar_name;
    private long latitude;
    private long longitude;

    public Bar() {
    }

    public Bar(String bar_name, long latitude, long longitude) {
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

    public long getLatitude() {
        return latitude;
    }

    public void setLatitude(long latitude) {
        this.latitude = latitude;
    }

    public long getLongitude() {
        return longitude;
    }

    public void setLongitude(long longitude) {
        this.longitude = longitude;
    }
}
