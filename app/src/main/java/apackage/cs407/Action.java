package apackage.cs407;

import android.content.Context;
import android.content.Intent;
import android.provider.Settings;
import android.support.design.widget.FloatingActionButton;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Sachal on 4/24/2017.
 */

abstract class Action {
    public abstract String getName();
    public abstract void executeAction(Context context);
}

class Examine extends Action {
    protected String name;
    protected int time;
    protected int cost;

    Examine(int cost) {
        name = "Examine";
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void executeAction(Context context) {
        GlobalApp.setNumActions(cost);
        Toast.makeText(context, GlobalApp.getViewItem().examineResult, Toast.LENGTH_LONG).show();
    }
}

class Enter extends Action {
    protected String name;
    protected int time;
    protected boolean unlocked;
    protected Intent destination;
    protected int cost;

    Enter() {
        name = "Enter room";
    }

    Enter(boolean status, Intent dest, int cost) {
        this.name = "Enter room";
        this.unlocked = status;
        this.destination = dest;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public void executeAction(Context context) {
        if (unlocked) {
            GlobalApp.setNumActions(cost);
            context.startActivity(this.destination);
        }
        else Toast.makeText(context, "Cannot enter with current held item.", Toast.LENGTH_LONG).show();
    }
}

//For any non-examine action which only returns a text message but has a custom name (custom actions with actual functionality will each need their own class)
class TextOnly extends Action {
    protected String name;
    protected int time;
    private String result;

    public TextOnly() {
        this.name = "N/A";
        this.result = "Error";
    }

    TextOnly(String name, String result){
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void executeAction(Context context) {
        Toast.makeText(context, this.result, Toast.LENGTH_LONG).show();
    }
}

class Take extends Action {
    protected String name;
    protected int time;
    protected ImageButton btn;
    protected Item alt;
    protected int cost;

    public Take() {
        this.name = "Take " + GlobalApp.getViewItem().getName();
    }

    public Take(ImageButton btn, int cost) {
        this.name = "Take " + GlobalApp.getViewItem().getName();
        this.btn = btn;
        this.alt = null;
        this.cost = cost;
    }

    public Take(Item item, int cost) {
        this.name = "Take " + item.getName();
        this.btn = null;
        this.alt = item;
        this.cost = cost;
    }

    public String getName() {return name;}

    public void executeAction (Context context) {
        Item toTake = (alt == null) ? GlobalApp.getViewItem() : alt;
        if (! toTake.getPickup()) Toast.makeText(context, "Cannot currently take item", Toast.LENGTH_LONG).show();
        else {
            ArrayList<Item> items = GlobalApp.getInventory();
            items.add(toTake);
            GlobalApp.setNumActions(cost);
            Toast.makeText(context, "Took " + toTake.getName(), Toast.LENGTH_LONG).show();
            if (btn != null) btn.setVisibility(View.GONE);
        }

    }
}