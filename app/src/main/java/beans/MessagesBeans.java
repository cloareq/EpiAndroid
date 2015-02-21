package beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import service.ApiGet;


public class MessagesBeans {
    private String token;
    private String ret;
    private List<Map<String, String>> listMsg = new ArrayList<Map<String, String>>();

    public MessagesBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/messages?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            requestMessages();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void requestMessages() {
        JSONArray array = null;
        try {
            array = new JSONArray(ret);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj = null;
            try {
                obj = array.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("title", obj.get("title").toString());
                map.put("content", obj.get("content").toString());
                map.put("date", obj.get("date").toString());
                listMsg.add(map);
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, String>> getListMsg() {
        System.out.println("list ---->" + listMsg);
        return listMsg;}
}