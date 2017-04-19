package apackage.cs407;

// Written by Dylan Jahnke

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

class CustomAdapter extends ArrayAdapter<Item> {

    CustomAdapter(Context context, ArrayList<Item> items) {
        super(context, R.layout.custom_row, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inventoryInflater = LayoutInflater.from(getContext());
        View customView = convertView;

        if ( customView == null) {
            customView = inventoryInflater.inflate(R.layout.custom_row, parent, false);
        }

        String name = getItem(position).getName();
        String uses = getItem(position).getNumUses() + "";
        String descrip = getItem(position).getDescription();

        TextView nameText = (TextView) customView.findViewById(R.id.itemname);
        TextView usesText = (TextView) customView.findViewById(R.id.remainUses);
        TextView descripText = (TextView) customView.findViewById(R.id.actualDescription);
        ImageView currImage = (ImageView) customView.findViewById(R.id.inventoryImage);

        nameText.setText(name);
        usesText.setText(uses);
        descripText.setText(descrip);
        currImage.setImageResource(R.mipmap.ic_launcher);
        return customView;
    }
}
