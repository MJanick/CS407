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
    private boolean fishTaken = false;
    private boolean keyTaken = false;
    private boolean magnetTaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room3.this, MainActivity.class));
            }
        });

        ((GlobalApp) getApplication()).setViewItem(null);

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        if(!fishTaken || !keyTaken || !magnetTaken) {
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals("Fish")) {
                    fishTaken = true;
                }
                if(items.get(i).getName().equals("Storeroom Key")) {
                    keyTaken = true;
                }
                if(items.get(i).getName().equals("Magnet")) {
                    magnetTaken = true;
                }
            }
        }


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



        final ImageButton case1Button = (ImageButton) findViewById(R.id.Case1);
        case1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item case1;
                if(!fishTaken) {
                    case1 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A glass case. If only you could break it open.");
                } else {
                    case1 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A broken glass case.");
                }
                ((GlobalApp) getApplication()).setViewItem(case1);
                if (curr != null && curr.getName().equals("Hammer") && !fishTaken) {
                    case1.addAction(new Take(new Item("Fish", 5, "Ugh. It's a bit smelly.", true, 0, R.mipmap.fish, "")));
                }
                startActivity(new Intent(Room3.this, ItemView.class));
            }});

        final ImageButton case2Button = (ImageButton) findViewById(R.id.Case2);
        case2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item case2;
                if(!keyTaken) {
                    case2 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A glass case. If only you could break it open.");
                } else {
                    case2 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A broken glass case.");
                }
                ((GlobalApp) getApplication()).setViewItem(case2);
                if (curr != null && curr.getName().equals("Hammer") && !keyTaken) {
                    case2.addAction(new Take(new Item("Storeroom Key", 5, "The key to the storeroom", true, 0, R.mipmap.key, "")));
                }
                startActivity(new Intent(Room3.this, ItemView.class));
            }});

        final ImageButton case3Button = (ImageButton) findViewById(R.id.Case3);

        case3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item case3;
                if(!magnetTaken) {
                    case3 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A glass case. If only you could break it open.");
                } else {
                    case3 = new Item("Case", 100, "", false, 0, R.mipmap.glasscase, "A broken glass case.");
                }
                ((GlobalApp) getApplication()).setViewItem(case3);
                if (curr != null && curr.getName().equals("Hammer") && !magnetTaken) {
                    case3.addAction(new Take(new Item("Magnet", 5, "I sense a bit of attraction.", true, 0, R.mipmap.magnet, "")));
                }
                startActivity(new Intent(Room3.this, ItemView.class));
            }});




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

        ArrayList<Item> check = ((GlobalApp) this.getApplication()).getInventory();

        if(!fishTaken || !keyTaken || !magnetTaken) {
            for(int i = 0; i < check.size(); i++) {
                if(check.get(i).getName().equals("Fish")) {
                    fishTaken = true;
                }
                if(check.get(i).getName().equals("Storeroom Key")) {
                    keyTaken = true;
                }
                if(check.get(i).getName().equals("Magnet")) {
                    magnetTaken = true;
                }
            }
        }
    }
}
