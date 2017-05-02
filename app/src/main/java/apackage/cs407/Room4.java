package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Button;
import android.widget.ImageButton;

public class Room4 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room4);
        ((GlobalApp) getApplication()).setViewItem(null);

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item door2 = new Item("Door", 100, "", false, 0, R.mipmap.door, "An unlocked door.");
                ((GlobalApp) getApplication()).setViewItem(door2);
                door2.addAction(new Enter(true, new Intent(Room4.this, Room2.class)));
                startActivity(new Intent(Room4.this, ItemView.class));
            }});

        ImageButton fridgeButton = (ImageButton) findViewById(R.id.Fridge);
        fridgeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item Fridge = new Item("Fridge", 100, "", false, 0, R.mipmap.fridge, "A fridge, there`s some meat inside.");
                ((GlobalApp) getApplication()).setViewItem(Fridge);
                Fridge.addAction(new Take(new Item("Meat", 5, "Meat", true, 0, R.mipmap.meat, "")));
                startActivity(new Intent(Room4.this, ItemView.class));
            }});

        ImageButton tableButton = (ImageButton) findViewById(R.id.Table);
        tableButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Item table = new Item("Table", 100, "", false, 0, R.mipmap.table, "a bare table, better look somewhere else");
                ((GlobalApp) getApplication()).setViewItem(table);
                startActivity(new Intent(Room4.this, ItemView.class));

            }});
    }
}
