package me.uteacher.www.herocat.model.instagram;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import org.json.JSONArray;
import org.json.JSONException;

import java.util.Arrays;
import java.util.HashSet;

import me.uteacher.www.herocat.model.BaseModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class InstagramModel extends BaseModel implements IInstagramModel {

    private InstagramBean instagramBean;
    private HashSet<String> likedUsers = new HashSet<>();
    private HashSet<String> storedUsers = new HashSet<>();

    public InstagramModel(InstagramBean instagramBean) {
        this.instagramBean = instagramBean;

        addLikedUsers();
        addStoredUsers();
    }

    public InstagramModel(Parcel parcel) {
        instagramBean = new InstagramBean();
        instagramBean.setLikedUsers(parcel.readString());
        instagramBean.setIs_video(parcel.readInt() == 1);
        instagramBean.setDisplay_src(parcel.readString());
        instagramBean.setStore_count(parcel.readInt());
        instagramBean.setLike_count(parcel.readInt());
        instagramBean.setGroup(parcel.readString());
        instagramBean.setVideo_url(parcel.readString());
        instagramBean.setStoredUsers(parcel.readString());
        instagramBean.setOwner(parcel.readString());
        instagramBean.setObjectId(parcel.readString());

        addLikedUsers();
        addStoredUsers();
    }

    public InstagramModel(String json) {
        instagramBean = JSON.parseObject(json, InstagramBean.class);

        addLikedUsers();
        addStoredUsers();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(instagramBean.getLikedUsers());
        dest.writeInt(instagramBean.getIs_video() ? 1 : 0);
        dest.writeString(instagramBean.getDisplay_src());
        dest.writeInt(instagramBean.getStore_count());
        dest.writeInt(instagramBean.getLike_count());
        dest.writeString(instagramBean.getGroup());
        dest.writeString(instagramBean.getVideo_url());
        dest.writeString(instagramBean.getStoredUsers());
        dest.writeString(instagramBean.getOwner());
        dest.writeString(instagramBean.getObjectId());
    }

    @Override
    public InstagramBean getInstagramBean() {
        return instagramBean;
    }

    @Override
    public void addLikedUser(String userId) {
        likedUsers.add(userId);
    }

    @Override
    public void addStoredUser(String userId) {
        storedUsers.add(userId);
    }

    @Override
    public boolean isUserLiked(IUserModel user) {
        return likedUsers.contains(user.getUserBean().getObjectId());
    }

    @Override
    public boolean isUserStored(IUserModel user) {
        return storedUsers.contains(user.getUserBean().getObjectId());
    }

    @Override
    public void userLikes(IUserModel user) {
        likedUsers.add(user.getUserBean().getObjectId());

        int count = instagramBean.getLike_count();
        instagramBean.setLike_count(count + 1);
    }

    @Override
    public void userCancelLike(IUserModel user) {
        likedUsers.remove(user.getUserBean().getObjectId());
        int count = instagramBean.getLike_count();
        instagramBean.setLike_count(count - 1);
    }

    @Override
    public void userFavourite(IUserModel user) {
        storedUsers.add(user.getUserBean().getObjectId());
        int count = instagramBean.getStore_count();
        instagramBean.setStore_count(count + 1);
    }

    @Override
    public void userCancelFavourite(IUserModel user) {
        storedUsers.remove(user.getUserBean().getObjectId());
        int count = instagramBean.getStore_count();
        instagramBean.setStore_count(count - 1);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Parcelable.Creator<InstagramModel> CREATOR = new Parcelable.Creator<InstagramModel>() {

        @Override
        public InstagramModel createFromParcel(Parcel source) {
            return null;
        }

        @Override
        public InstagramModel[] newArray(int size) {
            return new InstagramModel[size];
        }
    };

    private void addLikedUsers() {
        String string = instagramBean.getLikedUsers();
        if (string != null) {
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    addLikedUser(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }

    private void addStoredUsers() {
        String string = instagramBean.getStoredUsers();
        if (string != null) {
            try {
                JSONArray jsonArray = new JSONArray(string);
                for (int i = 0; i < jsonArray.length(); i++) {
                    addStoredUser(jsonArray.getString(i));
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
