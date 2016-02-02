package me.uteacher.www.herocat.model.user;

import android.os.Parcel;
import android.os.Parcelable;

import com.alibaba.fastjson.JSON;

import me.uteacher.www.herocat.model.BaseModel;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class UserModel extends BaseModel implements IUserModel {

    private UserBean userBean;

    public UserModel(UserBean userBean) {
        this.userBean = userBean;
    }

    public UserModel(Parcel source) {
        userBean = new UserBean();
        userBean.setUserName(source.readString());
        userBean.setPassWord(source.readString());
        userBean.setObjectId(source.readString());
    }

    public UserModel(String json) {
        userBean = JSON.parseObject(json, UserBean.class);
    }

    @Override
    public UserBean getUserBean() {
        return userBean;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(userBean.getUserName());
        dest.writeString(userBean.getPassWord());
        dest.writeString(userBean.getObjectId());
    }

    public static final Parcelable.Creator<UserModel> CREATOR = new Parcelable.Creator<UserModel>() {

        @Override
        public UserModel createFromParcel(Parcel source) {
            return new UserModel(source);
        }

        @Override
        public UserModel[] newArray(int size) {
            return new UserModel[size];
        }
    };

}
