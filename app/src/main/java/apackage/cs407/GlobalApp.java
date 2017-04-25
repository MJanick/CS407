package apackage.cs407;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by SlabberZ on 4/22/2017.
 */

public class GlobalApp extends Application {
    private ArrayList<Item> currInventory;
    private Item currItem;
    private DBManager dbman;

    public ArrayList<Item> getInventory() {
        return currInventory;
    }

    public void setInventory(ArrayList<Item> inv) {
        this.currInventory = inv;
    }

    public Item getItem() {
        return currItem;
    }

    public void setItem(Item item) {
        this.currItem = item;
    }

    public DBManager getDB() {
        return dbman;
    }

    public void setDB(DBManager dbman) {
        this.dbman = dbman;
    }
}
