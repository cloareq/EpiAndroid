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

        // Test lib gson recup du temps de log
        Intent intent = getIntent();
        token = intent.getStringExtra(EXTRA_TOKEN);
        System.out.println(token);
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView parent, View view, int position, long id) {
            selectItem(position);
        }
        Gson gson = new Gson();
    }

    //LES FRAGMENTS

    public static class FragHome extends Fragment {

        public FragHome() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_home, container, false);
        }
    }

    public static class FragPlanning extends Fragment {

        public FragPlanning() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_planning, container, false);
        }
    }

    public static class FragToken extends Fragment {

        public FragToken() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_token, container, false);
        }
    }

    public static class FragGrades extends Fragment {

        public FragGrades() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {

            return inflater.inflate(R.layout.fragment_grades, container, false);
        }
    }



    /** Swaps fragments in the main content view */
    private void selectItem(int position) {
        // Create a new fragment and specify the planet to show based on position
        Fragment fragment = null;
        switch (position) {
            case 0:
                fragment = new FragHome();
                break;
            case 1:
                fragment = new FragPlanning();
                break;
            case 2:
                fragment = new FragToken();
                break;
            case 3 :
                fragment = new FragGrades();
                break;
        }
        //Fragment fragment = new EpiFrag();
        //Bundle args = new Bundle();
        //args.putInt(EpiFrag.ARG_MENU_NUMBER, position);
        //fragment.setArguments(args);

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