package apackage.cs407;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Room4 extends AppCompatActivity {

    private FloatingActionButton currItem4;
    private boolean meatTaken = false;
    private boolean keyTaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room4);

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room4.this, MainActivity.class));
            }
        });
        ((GlobalApp) getApplication()).setViewItem(null);

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        if(!meatTaken || !keyTaken) {
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals("Meat")) {
                    meatTaken = true;
                }
                if(items.get(i).getName().equals("Lockbox Key")) {
                    keyTaken = true;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton4);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(Room4.this, Inventory.class));
            }


        });

        currItem4 = (FloatingActionButton) findViewById(R.id.currentItem4);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem4.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem4.setImageResource(item.getPic());
        }

        currItem4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                if(curr != null) {
                    Toast.makeText(Room4.this, curr.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room4.this, Room2.class));
            }});

        ImageButton fridgeButton = (ImageButton) findViewById(R.id.Fridge);
        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item Fridge;
                if(!meatTaken) {
                    Fridge = new Item("Fridge", 100, "", false, 0, R.mipmap.fridge, "A fridge, there`s some meat inside.");
                    Fridge.addAction(new Take(new Item("Meat", 5, "Meat", true, 0, R.mipmap.meat, "")));
                } else {
                    Fridge = new Item("Fridge", 100, "", false, 0, R.mipmap.fridge, "A fridge, there's nothing important in here.");
                }
                ((GlobalApp) getApplication()).setViewItem(Fridge);

                startActivity(new Intent(Room4.this, ItemView.class));
            }});

        ImageButton tableButton = (ImageButton) findViewById(R.id.Table);
        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item table;
                if(!keyTaken) {
                    table = new Item("Table", 100, "", false, 0, R.mipmap.table, "There's something under the table");
                    table.addAction(new Take(new Item("Lockbox Key", 5, "Can open a lockbox somewhere", true, 0, R.mipmap.key, "")));
                } else {
                    table = new Item("Table", 100, "", false, 0, R.mipmap.table, "A bare table, better look somewhere else");
                }
                ((GlobalApp) getApplication()).setViewItem(table);
                startActivity(new Intent(Room4.this, ItemView.class));

            }});
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem4.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem4.setImageResource(item.getPic());
        }

        ArrayList<Item> check = ((GlobalApp) this.getApplication()).getInventory();

        if(!meatTaken || !keyTaken) {
            for(int i = 0; i < check.size(); i++) {
                if(check.get(i).getName().equals("Meat")) {
                    meatTaken = true;
                }
                if(check.get(i).getName().equals("Lockbox Key")) {
                    keyTaken = true;
                }
            }
        }
    }
}
