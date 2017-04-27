package apackage.cs407;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Sachal on 4/27/2017.
 */

class ActionAdapter extends ArrayAdapter<Action> {
    ActionAdapter(Context context, ArrayList<Action> actions) {
        super(context, R.layout.action_row, actions);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inventoryInflater = LayoutInflater.from(getContext());
        View customView = convertView;

        if ( customView == null) {
            customView = inventoryInflater.inflate(R.layout.action_row, parent, false);
        }

        String name = getItem(position).getName();

        TextView nameText = (TextView) customView.findViewById(R.id.actionname);

        nameText.setText(name);
        return customView;
    }
}
