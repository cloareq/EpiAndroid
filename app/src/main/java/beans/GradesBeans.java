package beans;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.concurrent.ExecutionException;

import service.api;

/**
 * Created by Dodo on 20/02/2015.
 */
public class GradesBeans {
    private String token;
    private api request;
    private String ret;
    private String title;
    private String finalGrade;
    private String date;

    public GradesBeans(String t) {
        token = t;
        request = new api();
        request.execute("token", token, "marks", "get");
        try {
            ret = request.get();
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
