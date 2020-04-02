package com.paste.cloud.compoent;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

public class JSONResult {

    public static final JSONObject success_result = JSONResult.getResult(Constants.successCode, Constants.successDesc);
    public static final JSONObject inner_error = JSONResult.getResult(Constants.innerErrorCode, Constants.inner_error);

    public static JSONObject getResult(int code,String msg){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("code",code);
            jsonObject.put("msg",msg);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return jsonObject;
    }



}
