package apackage.cs407;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class Room2 extends AppCompatActivity {

    private FloatingActionButton currItem2;
    private boolean keyTaken = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room2);

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new MainActivity(), StartActivity.class));
            }
        });
        ((GlobalApp) getApplication()).setViewItem(null);

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        if(!keyTaken) {
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals("Exit Key")) {
                    keyTaken = true;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(Room2.this, Inventory.class));
            }


        });

        currItem2 = (FloatingActionButton) findViewById(R.id.currentItem2);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem2.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem2.setImageResource(item.getPic());
        }

        ImageButton Door3 = (ImageButton) findViewById(R.id.Door3);
        Door3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item door3 = new Item("Door", 100, "", false, 0, R.mipmap.door, "An unlocked door.");
                ((GlobalApp) getApplication()).setViewItem(door3);
                door3.addAction(new Enter(true, new Intent(Room2.this, Room3.class)));
                startActivity(new Intent(Room2.this, ItemView.class));
            }});

        ImageButton door4 = (ImageButton) findViewById(R.id.Door4);
        door4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item exit = new Item("Door", 100, "", false, 0, R.mipmap.door, "An unlocked door. The room it leads to is too dark to enter without a light source.");
                ((GlobalApp) getApplication()).setViewItem(exit);
                exit.addAction(new Enter((curr != null && curr.getName().equals("Candle")), new Intent(Room2.this, Room4.class)));

                startActivity(new Intent(Room2.this, ItemView.class));
            }});

        ImageButton dog = (ImageButton) findViewById(R.id.Dog);
        dog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item guarddog = new Item("Guard Dog", 100, "", false, 0, R.mipmap.dog, "This dog is guarding something. He looks hungry");
                ((GlobalApp) getApplication()).setViewItem(guarddog);
                if (curr != null && curr.getName().equals("Meat") && !keyTaken) {
                    guarddog.addAction(new Take(new Item("Exit Key", 7, "This can be used to escape", true, 0, R.mipmap.key, "This can be used to escape")));
                }
                startActivity(new Intent(Room2.this, ItemView.class));
            }});

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room2.this, Room1.class));
            }});


    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        ArrayList<Item> check = ((GlobalApp) this.getApplication()).getInventory();
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem2.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem2.setImageResource(item.getPic());
        }
        if(!keyTaken) {
            for(int i = 0; i < check.size(); i++) {
                if(check.get(i).getName().equals("Exit Key")) {
                    keyTaken = true;
                }
            }
        }
    }
}
