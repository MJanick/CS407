package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Room5 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room5);
        ((GlobalApp) getApplication()).setViewItem(null);

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item door2 = new Item("Door", 100, "", false, 0, R.mipmap.door, "An unlocked door.");
                ((GlobalApp) getApplication()).setViewItem(door2);
                door2.addAction(new Enter(true, new Intent(Room5.this, Room3.class)));
                startActivity(new Intent(Room5.this, ItemView.class));
            }});

        final ImageButton ladderButton = (ImageButton) findViewById(R.id.Ladder);
        ladderButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item ladder = new Item ("Ladder", 1, "A small ladder", true, 0, R.mipmap.ladder, "");
                ((GlobalApp) getApplication()).setViewItem(ladder);
                ladder.addAction(new Take(ladderButton));

                startActivity(new Intent(Room5.this, ItemView.class));
            }
        });
    }
}
