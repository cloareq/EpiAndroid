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

public class ProjectsBeans {
    private List<Map<String, String>> listProjects = new ArrayList<>();
    private String token;
    private String ret;

    public ProjectsBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/projects?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            requestProjects();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
    }

    public void requestProjects(){
        try {
            JSONArray jarray = new JSONArray(ret);
            for (int i = 0;i<jarray.length();i++){
                JSONObject obj;
                try {
                    obj = jarray.getJSONObject(i);
                    Map<String, String> map = new HashMap<String, String>();
                    map.put("codemodule", obj.get("codemodule").toString().replaceAll("\\<.*?>", ""));
                    map.put("project", obj.get("project").toString().replaceAll("\\<.*?>", ""));
                    map.put("acti_title", obj.get("acti_title").toString().replaceAll("\\<.*?>", ""));
                    map.put("title_module", obj.get("title_module").toString().replaceAll("\\<.*?>", ""));
                    map.put("end_acti", obj.get("end_acti").toString().replaceAll("\\<.*?>", ""));
                    listProjects.add(map);
                }
                catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, String>> getListProjects() {return listProjects;}

}
