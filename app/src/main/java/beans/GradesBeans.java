package beans;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import service.ApiGet;
import service.api;

public class GradesBeans {
    private String token;
    private String ret;
    private String title;
    private String finalGrade;
    private String date;

    public GradesBeans(String t) {
        token = t;
        ApiGet request = new ApiGet();
        String url = "http://epitech-api.herokuapp.com/marks?token=" + token;
        request.execute(url);
        try {
            ret = request.get();
            System.out.println("REEEEEEET" + ret);
        } catch (InterruptedException | ExecutionException e) {
            e.printStackTrace();
        }
        requestTitle();
    }

    public void requestTitle() {
        try {
            JSONObject json = new JSONObject(ret);
            JSONObject notes = json.getJSONObject("notes");
            finalGrade = notes.getString("final_note");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        System.out.println("note = " + finalGrade);
    }
}
