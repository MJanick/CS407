package apackage.cs407;
import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Room1 extends AppCompatActivity {

    private FloatingActionButton currItem;
    private boolean knifeTaken = false;
    private boolean matchesTaken = false;
    private boolean candleTaken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room1);
        ((GlobalApp) getApplication()).setViewItem(null); //TODO Make sure this is at the top of each activity so that no weird leftovers happen from past item views

        final ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        if(!knifeTaken || !matchesTaken || !candleTaken) {
            for(int i = 0; i < items.size(); i++) {
                if(items.get(i).getName().equals("Knife")) {
                    knifeTaken = true;
                }
                if(items.get(i).getName().equals("Matches")) {
                    matchesTaken = true;
                }
                if(items.get(i).getName().equals("Candle")) {
                    candleTaken = true;
                }
            }
        }

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(Room1.this, Inventory.class));
            }


        });

        //((GlobalApp) this.getApplication()).setInventory(items);

        currItem = (FloatingActionButton) findViewById(R.id.currentItem);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem.setImageResource(item.getPic());
        }


        currItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                if(curr != null) {
                    Toast.makeText(Room1.this, curr.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        ImageButton exitButton = (ImageButton) findViewById(R.id.Exit);
        exitButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item exit = new Item("Exit", 100, "", false, 0, R.mipmap.door, "A locked exit to the house.");
                ((GlobalApp) getApplication()).setViewItem(exit);
                exit.addAction(new Enter((curr != null && curr.getName().equals("Exit Key")), new Intent(Room1.this, ResultsName.class)));
                if (curr != null && curr.getName().equals("Wrench")) exit.addAction(new TextOnly("Smack door with wrench", "Didn't do anything."));

                startActivity(new Intent(Room1.this, ItemView.class));
            }});

        ImageButton door2Button = (ImageButton) findViewById(R.id.Door2);
        door2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item door2 = new Item("Door", 100, "", false, 0, R.mipmap.door, "An unlocked door.");
                ((GlobalApp) getApplication()).setViewItem(door2);
                door2.addAction(new Enter(true, new Intent(Room1.this, Room2.class)));
                startActivity(new Intent(Room1.this, ItemView.class));
            }});
        ImageButton shelfButton = (ImageButton) findViewById(R.id.Shelf);
        shelfButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item shelf = new Item("Shelf", 100, "", false, 0, R.mipmap.shelf, "A shelf. There's something on the top but it's too high to reach without a ladder.");
                ((GlobalApp) getApplication()).setViewItem(shelf);
                if (curr != null && curr.getName().equals("Ladder") && !matchesTaken) {
                    shelf.addAction(new Take(new Item("Matches", 5, "A box of matches", true, 0, R.mipmap.matches, "")));
                }
                startActivity(new Intent(Room1.this, ItemView.class));
            }});
        final ImageButton candleButton = (ImageButton) findViewById(R.id.Candle);
        if(candleTaken) {
            candleButton.setVisibility(View.GONE);
        }
        candleButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item candle = new Item("Candle", 100, "A lit candle", (curr != null && curr.getName().equals("Matches")), 0, R.mipmap.candle, "An unlit candle. Not worth picking up unless you're carrying matches.");
                ((GlobalApp) getApplication()).setViewItem(candle);
                candle.addAction(new Take(candleButton));

                startActivity(new Intent(Room1.this, ItemView.class));
            }
        });
        final ImageButton lockboxButton = (ImageButton) findViewById(R.id.Box);
        lockboxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item lockbox = new Item("Lockbox", 100, "A locked box", false, 0, R.mipmap.lockedbox, "A lockbox. What's inside?");
                ((GlobalApp) getApplication()).setViewItem(lockbox);
                if (curr != null && curr.getName().equals("Lockbox Key") && !knifeTaken)
                    lockbox.addAction(new Take(new Item("Knife", 7, "A well-sharpened knife", true, 0, R.mipmap.knife, ""))); //TODO Need to make sure this doesn't happen if they've already taken the knife
                startActivity(new Intent(Room1.this, ItemView.class));
            }
        });

        Button resetButton = (Button)findViewById(R.id.reset);

        resetButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(new MainActivity(), StartActivity.class));
            }
        });




    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        ArrayList<Item> check = ((GlobalApp) this.getApplication()).getInventory();
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem.setImageResource(item.getPic());
        }

        if(!knifeTaken || !matchesTaken || !candleTaken) {
            for(int i = 0; i < check.size(); i++) {
                if(check.get(i).getName().equals("Knife")) {
                    knifeTaken = true;
                }
                if(check.get(i).getName().equals("Matches")) {
                    matchesTaken = true;
                }
                if(check.get(i).getName().equals("Candle")) {
                    candleTaken = true;
                }
            }
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
