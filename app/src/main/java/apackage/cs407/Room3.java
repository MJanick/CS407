package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Room3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room3);

        ((GlobalApp) getApplication()).setViewItem(null);

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
}
