package service;

import android.os.AsyncTask;

import org.apache.commons.logging.Log;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;

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
        paramUrl param = new paramUrl();

        param.setParam(arg);
        param.setRequestName(arg[arg.length - 2]);
        param.setRequestType(arg[arg.length - 1]);
        HttpClient httpclient = new DefaultHttpClient();
        String url = "http://epitech-api.herokuapp.com/" + param.getRequestName();

        if (param.getRequestType().equals("post")) {
            HttpPost httppost = new HttpPost(url);
            try {
                httppost.setEntity(new UrlEncodedFormEntity(param.getParam()));
                HttpResponse res = httpclient.execute
                        (httppost);
                return EntityUtils.toString(res.getEntity());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    @Override
    protected void onPostExecute(String result) {

    }
}