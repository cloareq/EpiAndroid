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
import service.api;

public class TokenBeans {
    private String token;
    private String ret;
    private List<Map<String, String>> listToken = new ArrayList<>();

    public TokenBeans(String t) {token = t;}

    public void requestToken(String start, String end) {

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
            listToken = null;
            e.printStackTrace();
            return ;
        }
        for (int i = 0; i < array.length(); i++) {
            JSONObject obj;
            try {
                obj = array.getJSONObject(i);
                Map<String, String> map = new HashMap<String, String>();
                if (!obj.get("allow_token").toString().equals("false") && !obj.get("register_student").toString().equals("false")) {
                    map.put("scolaryear", obj.get("scolaryear").toString().replaceAll("\\<.*?>", ""));
                    map.put("codemodule", obj.get("codemodule").toString().replaceAll("\\<.*?>", ""));
                    map.put("codeinstance", obj.get("codeinstance").toString().replaceAll("\\<.*?>", ""));
                    map.put("acti_title", obj.get("acti_title").toString().replaceAll("\\<.*?>", ""));
                    map.put("codeacti", obj.get("codeacti").toString().replaceAll("\\<.*?>", ""));
                    map.put("codeevent", obj.get("codeevent").toString().replaceAll("\\<.*?>", ""));
                    listToken.add(map);
                }
            }
            catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    public void validateToken(String t, Map<String, String> event, String token_validation) {
        String year = event.get("scolaryear");
        String codemodule = event.get("codemodule");
        String codeinstance = event.get("codeinstance");
        String codeacti = event.get("codeacti");
        String codeevent = event.get("codeevent");
        String tokenvalidationcode = token_validation;

        api request = new api();
        request.execute("token", t, "scolaryear", year, "codemodule", codemodule,
                "codeinstance", codeinstance, "codeacti", codeacti, "codeevent", codeevent,
                "tokenvalidationcode", token_validation, "token", "post");
    }

    public List<Map<String, String>> getListToken() {return listToken;}


}
