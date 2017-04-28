package apackage.cs407;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class ItemView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_view);

        ArrayList<Action> actions = (ArrayList<Action>) GlobalApp.getViewItem().actions;


        ListAdapter actAdapter = new ActionAdapter(this, actions);
        ListView itemActionListView = (ListView) findViewById(R.id.itemActionListView);
        itemActionListView.setAdapter(actAdapter);

        itemActionListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Action action = (Action) parent.getItemAtPosition(position);

                        action.executeAction(ItemView.this);

                        

                        finish();

                    }
                }
        );
    }
}
