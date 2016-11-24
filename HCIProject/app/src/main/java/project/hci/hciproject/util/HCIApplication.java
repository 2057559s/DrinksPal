package project.hci.hciproject.util;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

/**
 * Created by jake on 11/23/16.
 */

public class HCIApplication extends Application {

    private Realm realm;

    @Override
    public void onCreate() {
        super.onCreate();
        Realm.init(this);

        RealmConfiguration configuration = new RealmConfiguration.Builder()
                .deleteRealmIfMigrationNeeded()
                .name("hci.realm")
                .build();

        Realm.setDefaultConfiguration(configuration);

        realm = Realm.getDefaultInstance();

        realm.executeTransaction(new Realm.Transaction() {
            @Override
            public void execute(Realm realm) {
                realm.deleteAll();
            }
        });

        PopulateData.populateBarsAndDrink();

    }

    @Override
    public void onTerminate() {
        super.onTerminate();
        realm.close();
    }
}
