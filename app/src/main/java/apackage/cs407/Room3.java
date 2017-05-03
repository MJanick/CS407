package apackage.cs407;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Room3 extends AppCompatActivity {

    private FloatingActionButton currItem3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new MainActivity(), StartActivity.class));
            }
        });

        ((GlobalApp) getApplication()).setViewItem(null);

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton3);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(Room3.this, Inventory.class));
            }


        });

        currItem3 = (FloatingActionButton) findViewById(R.id.currentItem3);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem3.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem3.setImageResource(item.getPic());
        }

        currItem3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                if(curr != null) {
                    Toast.makeText(Room3.this, curr.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton door5 = (ImageButton) findViewById(R.id.Door);
        door5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item door5 = new Item("Door", 100, "", false, 0, R.mipmap.door, "A locked door to a storeroom.");
                ((GlobalApp) getApplication()).setViewItem(door5);
                door5.addAction(new Enter((curr != null && curr.getName().equals("Storeroom Key")), new Intent(Room3.this, Room5.class)));

                startActivity(new Intent(Room3.this, ItemView.class));
            }});

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room3.this, Room2.class));
            }});
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem3.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem3.setImageResource(item.getPic());
        }
    }
}
