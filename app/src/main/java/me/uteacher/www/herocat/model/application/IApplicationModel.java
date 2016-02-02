package me.uteacher.www.herocat.model.application;

import android.os.Parcelable;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;

/**
 * Created by HL0521 on 2016/1/21.
 */
public interface IApplicationModel extends Parcelable {

    public ApplicationBean getApplicationBean();

    public LinkedHashMap<String, String> getCategories();

    public void addCategory(String key, String value);

    public HashMap<String, String> getShareInfo(String key);

    public void addShareInfo(String key, HashMap<String, String> info);

}
