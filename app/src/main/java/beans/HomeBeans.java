package beans;

import android.graphics.Bitmap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import service.ImgDownloader;
import service.api;

public class HomeBeans {
    private String token;
    private String logTime;
    private String picture;
    private String name;
    private String ret;

    private Bitmap image;

    public HomeBeans(String t) {

        token = t;
        api request = new api();
        request.execute("token", token, "infos", "post");
        try {
            ret = request.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        requestLogTime();
        requestPicture();
        requestBitmap();
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
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void requestBitmap() {
        ImgDownloader ppicture;
        ppicture = new ImgDownloader();
        ppicture.execute(picture);
        try {
            image = ppicture.get();

        } catch (InterruptedException | ExecutionException e) {
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

    public Bitmap getImage() {return image;}
}
