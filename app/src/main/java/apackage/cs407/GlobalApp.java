package apackage.cs407;

import android.app.Application;

import java.util.ArrayList;

/**
 * Created by SlabberZ on 4/22/2017.
 */

public class GlobalApp extends Application {
    private static ArrayList<Item> currInventory;
    private Item currItem;
    private DBManager dbman;
    private static Item viewItem = null;
    private static int numActions;

    public static ArrayList<Item> getInventory() {
        return currInventory;
    }

    public static void setInventory(ArrayList<Item> inv) {
        currInventory = inv;
    }

    public Item getItem() {
        return currItem;
    }

    public void setItem(Item item) {
        this.currItem = item;
    }

    public static Item getViewItem() {return viewItem;}

    public void setViewItem(Item item) {this.viewItem = item; }

    public DBManager getDB() {
        return dbman;
    }

    public void setDB(DBManager dbman) {
        this.dbman = dbman;
    }

    public static int getNumActions() {
        return numActions;
    }

    public static void setNumActions(int num) {
        numActions = numActions + num;
    }

    public static void resetNumActions() {
        numActions = 0;
    }
}
