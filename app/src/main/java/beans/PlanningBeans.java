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

public class PlanningBeans {
    private String token;
    private String ret;
    private List<Map<String, String>> listPlanning = new ArrayList<>();

    public PlanningBeans(String t){
        token = t;
    }

    public void requestPlanning(String start, String end) {

        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/planning?token="
                + token + "&start=" + start + "&end=" + end;
        request.execute(url);
        try {
            ret = request.get();
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }

        JSONArray array = null;
        try {
            array = new JSONArray(ret);
        } catch (JSONException e) {
            listPlanning = null;
            e.printStackTrace();
            return ;
        }
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj;
            try {
                obj = array.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                if (!obj.get("module_registered").toString().equals("false")){
                    map.put("start", obj.get("start").toString().replaceAll("\\<.*?>", ""));
                    map.put("end", obj.get("end").toString().replaceAll("\\<.*?>", ""));
                    map.put("titlemodule", obj.get("titlemodule").toString().replaceAll("\\<.*?>", ""));
                    map.put("acti_title", obj.get("acti_title").toString().replaceAll("\\<.*?>", ""));
                    listPlanning.add(map);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Map<String, String>> getListPlanning() {
        return listPlanning;
    }

}
