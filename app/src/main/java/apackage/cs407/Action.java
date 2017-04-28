package apackage.cs407;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

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

    Examine() {
        name = "Examine";
    }

    public String getName() {
        return name;
    }

    public void executeAction(Context context) {
        Toast.makeText(context, GlobalApp.getViewItem().examineResult, Toast.LENGTH_LONG).show();
    }
}

class Enter extends Action {
    protected String name;
    protected int time;
    protected boolean locked;
    protected Intent destination;

    Enter() {
        name = "Enter room";
    }

    Enter(boolean status, Intent dest) {
        this.name = "Enter room";
        this.locked = status;
        this.destination = dest;
    }

    public String getName() {
        return name;
    }

    public void executeAction(Context context) {
        if (! locked) context.startActivity(this.destination);
        else Toast.makeText(context, "The door is locked.", Toast.LENGTH_LONG).show();
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