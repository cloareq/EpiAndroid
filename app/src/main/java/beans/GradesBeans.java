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

public class GradesBeans {
    private String token;
    private String ret;
    List<Map<String,String>> grades = new ArrayList<>();

    public GradesBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/marks?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            ret = ret.replace("notes", "\"notes\"");
            requestTitle();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void requestTitle() {
        try {
            JSONObject json = new JSONObject(ret);
            JSONArray array = json.getJSONArray("notes");
            parseGrades(array);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }
    public void parseGrades(JSONArray array) {
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj;
            try {
                obj = array.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                map.put("final_note", obj.get("final_note").toString());
                map.put("title", obj.get("title").toString());
                map.put("scolaryear", obj.get("scolaryear").toString());
                map.put("comment", obj.get("comment").toString());
                grades.add(map);

            } catch (JSONException e ) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String,String>> getGrades() {
        return grades;}
}
