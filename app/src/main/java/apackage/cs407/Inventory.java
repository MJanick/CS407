package apackage.cs407;

//import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.AdapterView;
//import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Inventory extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventory);

        //ArrayList<Item> items = new ArrayList<>();
        ArrayList<Item> items = ((GlobalApp) this.getApplication()).getInventory();
        //String[] items = new String[3];
        //ArrayList<Integer> pics = new ArrayList<>();
        //int[] pictures = {R.mipmap.wrench2, R.mipmap.hammer, R.mipmap.soda};
        //Bitmap picwrench = BitmapFactory.decodeResource(getResources(), R.mipmap.wrench2);
        //Bitmap pichammer = BitmapFactory.decodeResource(getResources(), R.mipmap.hammer);
        //Bitmap picsoda = BitmapFactory.decodeResource(getResources(), R.mipmap.soda);

        //Item wrench = new Item("Wrench", 10, "This can be used to whack things or on bolts.", true, 50, R.mipmap.wrench2);
        //Item hammer = new Item("Hammer", 5, "This can also whack things, especially nails or zombies", true, 25, R.mipmap.hammer);
        //Item soda = new Item("Soda", 1, "I'm thirsty", true, 100, R.mipmap.soda);

        //items.add(wrench);
        //items.add(hammer);
        //items.add(soda);

        ListAdapter inventoryAdapter = new CustomAdapter(this, items);
        ListView inventoryListView = (ListView) findViewById(R.id.inventoryListView);
        inventoryListView.setAdapter(inventoryAdapter);

        inventoryListView.setOnItemClickListener(
                new AdapterView.OnItemClickListener(){
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        //TODO: On click go back to game activity and return clicked item as current item
                        Item item = (Item) parent.getItemAtPosition(position);
                        ((GlobalApp) getApplication()).setItem(item);

                        finish();
                        //String itemName = item.getName();
                        //Toast.makeText(Inventory.this, itemName, Toast.LENGTH_LONG).show();
                    }
                }
        );
    }
}
