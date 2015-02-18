package com.epitech.cloare_q.epiandroid;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;

import org.apache.http.HttpResponse;

import java.util.concurrent.ExecutionException;

import service.api;


public class MainActivity extends ActionBarActivity {
    private String login = null;
    private String password = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
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

    public void loginUser(View view) throws ExecutionException, InterruptedException {
        api request = new api();
        String login = ((EditText)findViewById(R.id.loginLogin)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString();
        request.execute(login, password);
        String result = request.get();
        System.out.println(result);
    }
}