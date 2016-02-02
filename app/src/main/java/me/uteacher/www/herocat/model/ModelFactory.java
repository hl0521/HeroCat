package me.uteacher.www.herocat.model;

import android.os.Parcel;

import me.uteacher.www.herocat.model.application.ApplicationModel;
import me.uteacher.www.herocat.model.application.IApplicationModel;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.instagram.InstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;
import me.uteacher.www.herocat.model.user.UserModel;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class ModelFactory {

    public static IUserModel creatUserModel(Parcel parcel) {
        return new UserModel(parcel);
    }

    public static IUserModel createUserModel(String json) {
        return new UserModel(json);
    }

    public static IInstagramModel createInstagramModel(Parcel parcel) {
        return new InstagramModel(parcel);
    }

    public static IInstagramModel createInstagramModel(String json) {
        return new InstagramModel(json);
    }

    public static IApplicationModel createApplicationModel(Parcel parcel) {
        return new ApplicationModel(parcel);
    }

    public static IApplicationModel createApplicationModel(String json) {
        return new ApplicationModel(json);
    }

}
