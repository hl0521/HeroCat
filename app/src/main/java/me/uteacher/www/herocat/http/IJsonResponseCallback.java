package me.uteacher.www.herocat.http;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by HL0521 on 2016/1/25.
 */
public interface IJsonResponseCallback {

    public void onSuccess(JSONObject jsonObject);

    public void onFailure(String error);

}
