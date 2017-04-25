package apackage.cs407;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultsName extends AppCompatActivity {

    EditText nameText;
    TextView scoreView;
    DBManager dbManager;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_name);
        nameText = (EditText) findViewById(R.id.nameText);
        scoreView = (TextView) findViewById(R.id.scoreView);
        dbManager = new DBManager(this, null, null, 1);
        score = 1;//calculateScore();
        scoreView.setText(score + "");

        Button enterNameButton = (Button)findViewById(R.id.enterNameButton);

        enterNameButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Scores newScore = new Scores(0, score, nameText.getText().toString());
                dbManager.addScore(newScore);
                startActivity(new Intent(ResultsName.this, MainActivity.class));
            }
        });

    }
/*
    public int calculateScore() {
        return 0;
    }*/
}
