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

public class ModulesBeans {
    private String token;
    private String ret;
    List<Map<String,String>> modules;

    public ModulesBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/modules?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            ret = ret.replace("modules", "\"modules\"");
            requestTitle();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void requestTitle() {
        try {
            JSONObject json = new JSONObject(ret);
            JSONArray array = json.getJSONArray("modules");
            parseModules(array);
        }
        catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void parseModules(JSONArray array){
        List<Map<String,String>> listModules = new ArrayList<Map<String,String>>();
        for (int i = 0; i<array.length(); i++) {
            try {
                String modules = array.getString(i);
                modules = modules.replace("{", "");
                Map<String, String> map = new HashMap<String, String>();
                for (final String entry : modules.split(",")) {
                    final String[] parts = entry.split(":");
                    assert (parts.length == 2) : "Invalid entry: " + entry;
                    try{
                        map.put(parts[0].replace("\"", ""), String.valueOf(parts[1]));}
                    catch (ArrayIndexOutOfBoundsException e){}
                }
                listModules.add(map);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        modules = listModules;
    }

    public List<Map<String,String>> getModules() {
        return modules;}
}
