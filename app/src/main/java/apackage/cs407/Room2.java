package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Room2 extends AppCompatActivity {

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

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room2.this, Room1.class));
            }});


    }
}
