package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        DBManager dbManager = new DBManager(this, null, null, 1);
        ((GlobalApp) getApplication()).setDB(dbManager);
        final ArrayList<Item> items = new ArrayList<>();
        final Item wrench = new Item("Wrench", 10, "This can be used to whack things or on bolts.", true, 50, R.mipmap.wrench2, "", 1);
        Item hammer = new Item("Hammer", 5, "This can also whack things, especially nails or zombies", true, 25, R.mipmap.hammer, "", 1);
        Item soda = new Item("Soda", 1, "I'm thirsty", true, 100, R.mipmap.soda, "", 1);

        items.add(wrench);
        items.add(hammer);
        items.add(soda);

        Button startButton = (Button)findViewById(R.id.startButton);

        startButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);
                ((GlobalApp) getApplication()).setItem(wrench);
                ((GlobalApp) getApplication()).setNumActions(0);

                startActivity(new Intent(MainActivity.this, Room1.class));
            }
        });

        //Button optionsButton = (Button)findViewById(R.id.optionsButton);

        /*optionsButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Room1.class));
            }
        });*/

        Button leaderboardButton = (Button)findViewById(R.id.leaderboardButton);

        leaderboardButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LeaderboardActivity.class));
            }
        });


    }


}
