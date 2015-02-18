package com.epitech.cloare_q.epiandroid;

import android.content.Intent;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;


import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

/**
 *
 * Home Screen Activity
 */
public class home extends Activity {
    final String EXTRA_TOKEN = "user_token";
    private String token = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home);

        Intent intent = getIntent();
        TextView tokenDisplay = (TextView) findViewById(R.id.showToken);

        if (intent != null) {
            token = intent.getStringExtra(EXTRA_TOKEN);
            tokenDisplay.setText(token);
        }
    }
}