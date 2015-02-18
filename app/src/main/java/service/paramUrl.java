package service;

import org.apache.http.message.BasicNameValuePair;

import java.util.List;

public class paramUrl {
    private List<BasicNameValuePair> param = null;
    private String requestName = null;
    private String requestType = null;

    private void setParam(String... arg) {
        int i = 0;
        while (i < (arg.length - 2))
        {
            param.add(new BasicNameValuePair(arg[i], arg[i+1]));
            i += 2;
        }
    }

    private List<BasicNameValuePair> getParam() {
        return param;
    }

    private void setRequestName(String req) {
        requestName = req;
    }

    private String getRequestName(){
        return requestName;
    }

    private void setRequestType(String req) {
        requestType = req;
    }

    private String getRequestType() {
        return requestType;
    }
}
