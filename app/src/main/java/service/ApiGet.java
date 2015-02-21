package service;


import android.os.AsyncTask;
import android.widget.TextView;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class ApiGet extends AsyncTask<String, Void, String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected String doInBackground(String... url) {
        HttpClient httpclient;
        HttpGet request;
        HttpResponse response = null;
        String result = " ";
        try {
            httpclient = new DefaultHttpClient();
            request = new HttpGet(url[0]);
            response = httpclient.execute(request);
        }
        catch (Exception e) {
            result = "error";
        }

        try {
            BufferedReader rd = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
            String line = "";
            while ((line = rd.readLine()) != null)
            {
                result = result + line ;
            }
        } catch (Exception e) {
            result = "error";
        }
        return result;
    }

    protected void onPostExecute(String result) {
    }
}