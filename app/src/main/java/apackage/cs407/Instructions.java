package apackage.cs407;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

public class Instructions extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_instructions);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        String text = "\n1. You are trapped inside of a locked house and must try to find a way to escape.\n\n" +
                "2. Clicking on items and objects will present you with a few selectable options.\n\n" +
                "3. Clicking on the inventory button (the one with the backpack) in the bottom right will bring up your current inventory." +
                " Selecting an item in here will mark this as your currently held item.\n\n" +
                "4. Your currently held item will be shown above your inventory button. Clicking on this will tell you which item it is.\n\n" +
                "5. Many of the tasks in game can not be performed unless you are holding a specific item. If you don't have the correct item," +
                " you must go and find it!\n\n" +
                "6. Every action you perform subtracts from your final score, so be wary of how many actions you are using.\n\n" +
                "7. Some items in game are decoys! They have no purpose but to lower your final score. Watch out!\n\n" +
                "8. Each room has a Quit button. This can be used to exit the game to the main menu at any point.\n\n";

        TextView instructionsText = (TextView) findViewById(R.id.instructionsText);
        instructionsText.setText(text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.mipmap.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(Instructions.this, MainActivity.class));
            }
        });
    }
}
