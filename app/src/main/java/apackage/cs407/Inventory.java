package apackage.cs407;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class Inventory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();

        ListAdapter inventoryAdapter = new CustomAdapter(this, items);
        ListView inventoryListView = (ListView) findViewById(R.id.inventoryListView);
        inventoryListView.setAdapter(inventoryAdapter);

        inventoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        Item item = (Item) parent.getItemAtPosition(position);
                        ((GlobalApp) getApplication()).setItem(item);
                        finish();
                    }
                }
        );
    }
}
