package me.uteacher.www.herocat.model.application;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashMap;

import me.uteacher.www.herocat.model.BaseModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class ApplicationModel extends BaseModel implements IApplicationModel {

    private ApplicationBean applicationBean;

    // categories 存放当前app中的 内容种类，由于 applicationBean 中的数据是用于网络传输的，因此其中的
    // categories 存放的是 String 类型的，需要将其中的内容进一步的提取出来
    private LinkedHashMap<String, String> categories = new LinkedHashMap<>();
    //
    private HashMap<String, HashMap<String, String>> infoMap = new HashMap<>();

    public ApplicationModel(ApplicationBean applicationBean) {
        this.applicationBean = applicationBean;

        addCategoriesInfoFromJson(applicationBean.getCategories());
        addShareInfoFromJson(applicationBean.getShare());
    }

    public ApplicationModel(Parcel parcel) {
        applicationBean = new ApplicationBean();
        applicationBean.setCategories(parcel.readString());
        applicationBean.setVersionNewFeature(parcel.readString());
        applicationBean.setCurrentVersion(parcel.readInt());
        applicationBean.setDownloadUrl(parcel.readString());
        applicationBean.setShare(parcel.readString());
        applicationBean.setObjectId(parcel.readString());
        applicationBean.setVersionString(parcel.readString());

        addCategoriesInfoFromJson(applicationBean.getCategories());
        addShareInfoFromJson(applicationBean.getShare());
    }

    public ApplicationModel(String json) {
        applicationBean = JSON.parseObject(json, ApplicationBean.class);

        addCategoriesInfoFromJson(applicationBean.getCategories());
        addShareInfoFromJson(applicationBean.getShare());
    }

    @Override
    public ApplicationBean getApplicationBean() {
        return applicationBean;
    }

    @Override
    public LinkedHashMap<String, String> getCategories() {
        return categories;
    }

    @Override
    public void addCategory(String key, String value) {
        categories.put(key, value);
    }

    @Override
    public HashMap<String, String> getShareInfo(String key) {
        return infoMap.get(key);
    }

    @Override
    public void addShareInfo(String key, HashMap<String, String> info) {
        infoMap.put(key, info);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(applicationBean.getCategories());
        dest.writeString(applicationBean.getVersionNewFeature());
        dest.writeInt(applicationBean.getCurrentVersion());
        dest.writeString(applicationBean.getDownloadUrl());
        dest.writeString(applicationBean.getShare());
        dest.writeString(applicationBean.getObjectId());
        dest.writeString(applicationBean.getVersionString());
    }

    public static final Parcelable.Creator<ApplicationModel> CREATOR = new Creator<ApplicationModel>() {
        @Override
        public ApplicationModel createFromParcel(Parcel source) {
            return new ApplicationModel(source);
        }

        @Override
        public ApplicationModel[] newArray(int size) {
            return new ApplicationModel[size];
        }
    };

    /**
     * 从 JSON 字符串中提取 类别 信息，并保存到 LinkedHashMap<String, String> categories
     *
     * @param json ApplicationBean 中的 categories （ JSONArray 格式） 字符串
     */
    private void addCategoriesInfoFromJson(String json) {
        try {
            JSONArray jsonArray = new JSONArray(json);

            JSONObject jsonObject;
            String key;
            for (int i = 0; i < jsonArray.length(); i++) {
                jsonObject = jsonArray.getJSONObject(i);
                key = jsonObject.keys().next();
                addCategory(key, jsonObject.getString(key));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    /**
     * 从 JSON 字符串中提取 分享 信息，并保存到 HashMap<String, HashMap<String, String>> infoMap
     *
     * @param json ApplicationBean 中的 share （ JSONObject 格式） 字符串
     */
    private void addShareInfoFromJson(String json) {
        try {
            JSONObject shareObject = new JSONObject(json);

            for (Iterator<String> iterator1 = shareObject.keys(); iterator1.hasNext(); ) {
                String shareKey = iterator1.next();

                HashMap<String, String> infoMap = new HashMap<>();
                try {
                    JSONObject infoObject = shareObject.getJSONObject(shareKey);
                    for (Iterator<String> iterator2 = infoObject.keys(); iterator2.hasNext(); ) {
                        String infoKey = iterator2.next();
                        infoMap.put(infoKey, infoObject.getString(infoKey));
                    }
                    addShareInfo(shareKey, infoMap);
                } catch (JSONException e1) {
                    e1.printStackTrace();
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }
}
