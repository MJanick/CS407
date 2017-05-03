package apackage.cs407;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

public class Room5 extends AppCompatActivity {

    private FloatingActionButton currItem5;
    private boolean ladderTaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room5);

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new MainActivity(), StartActivity.class));
            }
        });
        ((GlobalApp) getApplication()).setViewItem(null);

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        if(!ladderTaken) {
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals("Ladder")) {
                    ladderTaken = true;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton2);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(Room5.this, Inventory.class));
            }


        });

        currItem5 = (FloatingActionButton) findViewById(R.id.currentItem5);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem5.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem5.setImageResource(item.getPic());
        }

        currItem5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                if(curr != null) {
                    Toast.makeText(Room5.this, curr.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room5.this, Room3.class));
            }});

        final ImageButton ladderButton = (ImageButton) findViewById(R.id.Ladder);
        if(ladderTaken) {
            ladderButton.setVisibility(View.GONE);
        }
        ladderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item ladder = new Item ("Ladder", 1, "A small ladder", true, 0, R.mipmap.ladder, "A small ladder");
                ((GlobalApp) getApplication()).setViewItem(ladder);
                ladder.addAction(new Take(ladderButton));

                startActivity(new Intent(Room5.this, ItemView.class));
            }
        });
    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem5.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem5.setImageResource(item.getPic());
        }

        ArrayList<Item> check = ((GlobalApp) this.getApplication()).getInventory();

        if(!ladderTaken) {
            for(int i = 0; i < check.size(); i++) {
                if(check.get(i).getName().equals("Ladder")) {
                    ladderTaken = true;
                }
            }
        }
    }
}
