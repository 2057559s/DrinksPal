package project.hci.hciproject.util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jake on 11/23/16.
 */

public class HCIApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("hci.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);
    }
}
