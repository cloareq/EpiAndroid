package com.epitech.cloare_q.epiandroid;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.concurrent.ExecutionException;

import service.api;


public class MainActivity extends Activity {
    final String EXTRA_TOKEN = "user_token";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loginUser(View view) throws ExecutionException, InterruptedException {
        api request = new api();
        String login = ((EditText)findViewById(R.id.loginLogin)).getText().toString();
        String password = ((EditText)findViewById(R.id.loginPassword)).getText().toString();

        if (login.equals("") || password.equals("")) {
            Toast.makeText(MainActivity.this,
                    R.string.email_or_password_empty,
                    Toast.LENGTH_SHORT).show();
            return;
        }
        request.execute("login", login, "password", password, "login", "post");
        String result = request.get();

        if (result.contains("error")) {
            Toast.makeText(MainActivity.this,
                    R.string.error_login,
                    Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(MainActivity.this, home.class);
        intent.putExtra(EXTRA_TOKEN, result);
        startActivity(intent);

    }
}
