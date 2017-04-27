package apackage.cs407;

import android.widget.Toast;

/**
 * Created by Sachal on 4/24/2017.
 */

abstract class Action {
    public abstract String getName();
    public abstract void executeAction();
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

    public void executeAction() {
        //Should cause the app to display the "examineResult" field from the viewItem from GlobalApp
    }
}

class Enter extends Action {
    protected String name;
    protected int time;

    Enter() {
        name = "Enter room";
    }

    public String getName() {
        return name;
    }

    public void executeAction() {
        //Should cause the app to viewchange to the appropriate room (need to figure out how to parametrize that)
        //or else display a message informing the player that the door is locked. Need to figure out some of how to handle this
        //in here or else how to handle it elsewhere.

        //Might just have stuff telling the action status handled when the action is added to the list and modify when status changes.
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

    public void executeAction() {
        //Display result
    }
}