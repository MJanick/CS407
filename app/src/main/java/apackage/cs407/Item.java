package apackage.cs407;

import android.graphics.Bitmap;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by SlabberZ on 4/18/2017.
 */

public class Item {
    private String name;
    private int numUses;
    private String description;
    private boolean pickup;
    protected List<Action> actions;
    protected String examineResult;
    private int maxUses;
    private int score;
    private int pic;

    public Item(String name, int numUses, String description, boolean pickup, int score, int pic, String examineResult, int cost) {
        this.name = name;
        this.numUses = numUses;
        this.maxUses = numUses;
        this.description = description;
        this.pickup = pickup;
        this.score = score;
        this.pic = pic;
        this.examineResult = examineResult;
        this.actions = new ArrayList<Action>();
        actions.add(new Examine(cost));
    }

    public void addAction(Action act) {
        this.actions.add(act);
    }

    public void setActions(List<Action> acts) {
        this.actions = acts;
    }

    public void setExamineResult(String newResult) {
        this.examineResult = newResult;
    }

    public String getName() {
        return name;
    }

    public int getNumUses() {
        return numUses;
    }

    public String getDescription() {
        return description;
    }

    public int getScore() {
        return score;
    }

    public boolean getPickup() {
        return pickup;
    }

    public int getPic() {
        return pic;
    }

    public boolean useItem() {
        if(numUses > 0) {
            numUses--;
            return true;
        } else {
            return false;
        }
    }

    public int useRepair() {
        numUses = maxUses;
        return numUses;
    }
}
