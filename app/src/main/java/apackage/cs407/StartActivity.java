package apackage.cs407;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StartActivity extends AppCompatActivity {

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;
    private int first = 0;
    private FloatingActionButton currItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        Toolbar toolbar = (Toolbar) findViewById(R.id.optionsToolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        ((GlobalApp) getApplication()).setViewItem(null); //TODO Make sure this is at the top of each activity so that no weird leftovers happen from past item views


        final ArrayList<Item> items = new ArrayList<>();

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.inventoryButton);

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((GlobalApp) getApplication()).setInventory(items);

                startActivity(new Intent(StartActivity.this, Inventory.class));
            }


        });

        Item wrench = new Item("Wrench", 10, "This can be used to whack things or on bolts.", true, 50, R.mipmap.wrench2, "");
        Item hammer = new Item("Hammer", 5, "This can also whack things, especially nails or zombies", true, 25, R.mipmap.hammer, "");
        Item soda = new Item("Soda", 1, "I'm thirsty", true, 100, R.mipmap.soda, "");

        items.add(wrench);
        items.add(hammer);
        items.add(soda);

        ((GlobalApp) this.getApplication()).setInventory(items);

        currItem = (FloatingActionButton) findViewById(R.id.currentItem);
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem.setImageResource(R.mipmap.ic_launcher);
        }


        currItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(StartActivity.this, ResultsName.class));

                Item curr = ((GlobalApp) getApplication()).getItem();
                if(first == 0) {
                    Item pencil = new Item("Pencil", 25, "Draw with it", true, 1, R.mipmap.pencil, "");
                    items.add(pencil);
                    first++;
                }
                if(curr != null) {
                    Toast.makeText(StartActivity.this, curr.getName(), Toast.LENGTH_LONG).show();
                }
            }
        });

        FloatingActionButton doorButton = (FloatingActionButton) findViewById(R.id.door);
        doorButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Item curr = ((GlobalApp) getApplication()).getItem();
                Item door = new Item("Door", 100, "", false, 0, R.mipmap.ic_launcher, "A locked Door to another room.");
                door.addAction(new Enter((curr != null && curr.getName().equals("Key")), new Intent(StartActivity.this, LeaderboardActivity.class)));
                if (curr != null && curr.getName().equals("Wrench")) door.addAction(new TextOnly("Smack Door with wrench", "Didn't do anything."));
                ((GlobalApp) getApplication()).setViewItem(door);

                startActivity(new Intent(StartActivity.this, ItemView.class));
            }});

        final FloatingActionButton sampleKeyButton = (FloatingActionButton) findViewById(R.id.sampleKey);
        sampleKeyButton.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Item key = new Item("Key", 100, "A key to some Door", true, 0, R.mipmap.ic_launcher, "This is a key, despite appearances.");
                    ((GlobalApp) getApplication()).setViewItem(key);
                    key.addAction(new Take(sampleKeyButton));

                    startActivity(new Intent(StartActivity.this, ItemView.class));
                }
            });





    }

    @Override
    protected void onRestart() {
        super.onRestart();

        //Do your code here
        Item item = ((GlobalApp) this.getApplication()).getItem();
        if(item == null) {
            currItem.setImageResource(R.mipmap.ic_launcher);
        } else {
            currItem.setImageResource(item.getPic());
        }
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_start, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment() {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_start, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter {

        public SectionsPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount() {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            switch (position) {
                case 0:
                    return "SECTION 1";
                case 1:
                    return "SECTION 2";
                case 2:
                    return "SECTION 3";
            }
            return null;
        }
    }
}
