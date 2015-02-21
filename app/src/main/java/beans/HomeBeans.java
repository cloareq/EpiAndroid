package beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import service.api;

public class HomeBeans {
    private String token;
    private String logTime;
    private String picture;
    private String name;
    private api request;
    private String ret;

    public HomeBeans(String t) {
        token = t;
        request = new api();
        request.execute("token", token, "infos", "post");
        try {
            ret = request.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        requestLogTime();
        requestPicture();
    }

    public void requestLogTime() {
        try {
            JSONObject json = new JSONObject(ret);
            JSONObject current = json.getJSONObject("current");
            logTime = current.getString("active_log");
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void requestPicture() {
        try {
            JSONObject json = new JSONObject(ret);
            JSONObject infos = json.getJSONObject("infos");
            picture = "https://cdn.local.epitech.eu/userprofil/" + infos.getString("picture");
            name = infos.getString("title");
            System.out.println("picture = " + picture  + "name = " + name);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setToken(String t) {
        token = t;
    }

    public String getToken(){
        return token;
    }

    public String getLogTime() { return logTime;}

    public String getPicture(){
        return picture;
    }

    public String getName () {return name;}
}
