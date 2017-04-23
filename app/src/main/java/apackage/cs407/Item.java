package apackage.cs407;

import android.graphics.Bitmap;

/**
 * Created by SlabberZ on 4/18/2017.
 */

public class Item {
    private String name;
    private int numUses;
    private String description;
    private boolean pickup;
    private int maxUses;
    private int score;
    private int pic;

    public Item(String name, int numUses, String description, boolean pickup, int score, int pic) {
        this.name = name;
        this.numUses = numUses;
        this.maxUses = numUses;
        this.description = description;
        this.pickup = pickup;
        this.score = score;
        this.pic = pic;
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
