package project.hci.hciproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

import io.realm.Realm;
import io.realm.RealmResults;
import project.hci.hciproject.realm.Drink;


public class DrinkActivity extends AppCompatActivity {


    ArrayList<Drink> items;
    RecyclerView rvContacts;
    Realm realm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_drink);
        realm = Realm.getDefaultInstance();
        items = new ArrayList<>();
        // ...
        // Lookup the recyclerview in activity layout
        rvContacts = (RecyclerView) findViewById(R.id.rvItems);

        // Initialize contacts
        RealmResults<Drink> results = realm.where(Drink.class).findAll();

        for (Drink drink : results) {
            items.add(drink);
        }
        //items = realm.where(Bar.class).findAll();
        // Create adapter passing in the sample user data
        DrinkAdapter adapter = new DrinkAdapter(this, items);
        // Attach the adapter to the recyclerview to populate items
        rvContacts.setAdapter(adapter);
        // Set layout manager to position the items
        rvContacts.setLayoutManager(new LinearLayoutManager(this));
        // That's all!
    }

    @Override
    protected void onStop() {
        super.onStop();
        realm.close();
    }
}

