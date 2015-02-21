package beans;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.lang.Object;

import service.ApiGet;

public class GradesBeans {
    private String token;
    private String ret;
    List<Map<String,String>> grades;

    public GradesBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/marks?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            ret = ret.replace("notes", "\"notes\"");
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        requestTitle();
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

    public void parseGrades(JSONArray array){
        List<Map<String,String>> listGrades = new ArrayList<Map<String,String>>();
        for (int i = 0; i<array.length(); i++) {
            try {
                String grades = array.getString(i);
                grades = grades.replace("{", "");
                Map<String, String> map = new HashMap<String, String>();
                for (final String entry : grades.split(",")) {
                    final String[] parts = entry.split(":");
                    assert (parts.length == 2) : "Invalid entry: " + entry;
                    try{
                    map.put(parts[0].replace("\"", ""), String.valueOf(parts[1]));}
                    catch (ArrayIndexOutOfBoundsException e){}
                }
                listGrades.add(map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        grades = listGrades;
    }

    public List<Map<String,String>> getGrades() {
        return grades;}
}
