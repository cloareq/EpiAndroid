package service;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.util.ArrayList;
import java.util.List;

public class api extends AsyncTask<String, Integer, String>
{

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }

    @Override
    protected void onProgressUpdate(Integer... values){
        super.onProgressUpdate(values);

    }

    @Override
    protected String doInBackground(String... arg) {

        HttpClient httpclient = new DefaultHttpClient();
        HttpPost httppost = new HttpPost("http://epitech-api.herokuapp.com/login");

        List<BasicNameValuePair> connection = new ArrayList<>();
        connection.add(new BasicNameValuePair("login", arg[0]));
        connection.add(new BasicNameValuePair("password", arg[1]));
        try {
            httppost.setEntity(new UrlEncodedFormEntity(connection));
            HttpResponse res = httpclient.execute(httppost);
            String response =  EntityUtils.toString(res.getEntity());
            return response;
        }
        catch (Exception e) {System.out.println(e);}
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}