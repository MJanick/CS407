package apackage.cs407;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class LeaderboardActivity extends AppCompatActivity {

    DBManager dbManager;
    ArrayList<Scores> allScores;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        Toolbar toolbar = (Toolbar) findViewById(R.id.optionsToolbar);
        setSupportActionBar(toolbar);

        dbManager = new DBManager(this, null, null, 1);
        allScores = dbManager.databaseScores();

        Collections.sort(allScores, new Comparator<Scores>() {
            public int compare(Scores score1, Scores score2)
            {
                int result =  Integer.valueOf(score2.get_score()).compareTo(score1.get_score());
                if (result == 0) {
                    result = Integer.valueOf(score1.get_id()).compareTo(score2.get_id());
                }
                return result;
            }
        });

        setTitle("Leaderboard");
        String text = "Attempt | User | Score\n\n";

        for(int i = 0; i < allScores.size(); i++) {
            text += allScores.get(i).get_id() + " | " + allScores.get(i).get_name() + " | " + allScores.get(i).get_score() + "\n";
        }

        TextView leaderboardText = (TextView) findViewById(R.id.leaderboardText);
        leaderboardText.setText(text);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setImageResource(R.mipmap.back);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LeaderboardActivity.this, MainActivity.class));
            }
        });
    }
}
