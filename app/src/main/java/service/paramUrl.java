package service;

import org.apache.http.message.BasicNameValuePair;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class paramUrl {
    private List<BasicNameValuePair> param = null;
    private String requestName = null;
    private String requestType = null;

    public void setParam(String... arg) {
        int i = 0;
        param = new ArrayList<>();
        while (i < (arg.length - 3))
        {
            param.add(new BasicNameValuePair(arg[i], arg[i+1]));
            i += 2;
        }
    }

    public List<BasicNameValuePair> getParam() {
        return param;
    }

    public void setRequestName(String req) {
        requestName = req;
    }

    public String getRequestName(){
        return requestName;
    }

    public void setRequestType(String req) {
        requestType = req;
    }

    public String getRequestType() {
        return requestType;
    }
}
