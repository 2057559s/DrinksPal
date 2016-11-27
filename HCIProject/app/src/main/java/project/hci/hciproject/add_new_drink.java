package project.hci.hciproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;
import project.hci.hciproject.realm.Bar;
import project.hci.hciproject.realm.Drink;

public class add_new_drink extends AppCompatActivity {

    EditText drink_name;
    EditText sprice;
    String type;
    Spinner spinner;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_drink);
        spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.drinks_type, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        spinner.setPrompt("Select Type");
    }



  /*  public Drink getNewDrink(){
        drink_name = (EditText)findViewById(R.id.editText);
        drink_name.toString();
        sprice = (EditText)findViewById(R.id.editText3);
        double price = Float.valueOf(sprice.getText().toString());
        type = spinner.getSelectedItem().toString();
        Drink drink = new Drink(5, drink_name, price, bar, type);
        return drink;
    }*/




}
