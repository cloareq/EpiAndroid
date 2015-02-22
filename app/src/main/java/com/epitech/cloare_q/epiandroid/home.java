package com.epitech.cloare_q.epiandroid;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import beans.HomeBeans;
import service.api;

public class home extends Activity {
    private String[] menuList;
    private DrawerLayout mDrawerLayout;
    private ListView mDrawerList;
    final String EXTRA_TOKEN = "user_token";
    private String token = null;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        menuList = getResources().getStringArray(R.array.menu_array);
        mDrawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        mDrawerList = (ListView) findViewById(R.id.left_drawer);

        // Set the adapter for the list view
        mDrawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, menuList));
        // Set the list's click listener
        mDrawerList.setOnItemClickListener(new DrawerItemClickListener());
        // take token
        Intent intent = getIntent();
        String tmpToken = intent.getStringExtra(EXTRA_TOKEN);
        try {

            JSONObject json = new JSONObject(tmpToken);
            token = json.getString("token");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        // Home fragment
        Fragment fragment = new fragments.FragHome();
        Bundle args = new Bundle();
        args.putString("token", token);
        fragment.setArguments(args);
            FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    //LES FRAGMENTS




    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        Bundle args = new Bundle();
        args.putString("token", token);
        switch (position) {
            case 0:
                fragment = new fragments.FragHome();
                fragment.setArguments(args);
                break;
            case 1:
                fragment = new fragments.FragPlanning();
                fragment.setArguments(args);
                break;
            case 2:
                fragment = new fragments.FragToken();
                fragment.setArguments(args);
                break;
            case 3:
                fragment = new fragments.FragGrades();
                fragment.setArguments(args);
                break;
            case 4:
                fragment = new fragments.FragModules();
                fragment.setArguments(args);
                break;
            case 5:
                fragment = new fragments.FragProjects();
                fragment.setArguments(args);
                break;
          }

        // Insert the fragment by replacing any existing fragment
            if (fragment != null) {
                FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .replace(R.id.content_frame, fragment)
                        .commit();
            } else {
                // error in creating fragment
                Log.e("Home Activity", "Error in creating fragment");
            }

        // Highlight the selected item, update the title, and close the drawer
        mDrawerList.setItemChecked(position, true);
        setTitle(menuList[position]);
        mDrawerLayout.closeDrawer(mDrawerList);
    }
}