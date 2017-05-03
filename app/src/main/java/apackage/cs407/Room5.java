package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class Room5 extends AppCompatActivity {

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

        Button Return = (Button) findViewById(R.id.Return);
        Return.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Room5.this, Room3.class));
            }});

        final ImageButton ladderButton = (ImageButton) findViewById(R.id.Ladder);
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
}
