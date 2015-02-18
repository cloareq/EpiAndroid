package service;

import android.os.AsyncTask;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

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
                HttpResponse res = httpclient.execute(httppost);
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