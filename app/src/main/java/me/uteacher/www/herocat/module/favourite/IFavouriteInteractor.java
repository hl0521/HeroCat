package me.uteacher.www.herocat.module.favourite;

import java.util.List;

import me.uteacher.www.herocat.model.instagram.IInstagramModel;
import me.uteacher.www.herocat.model.user.IUserModel;

/**
 * Created by HL0521 on 2016/2/1.
 */
public interface IFavouriteInteractor {

    public void getUserFavouriteItems(IUserModel userModel, OnGetUserFavouriteItemsListener listener);

    public void getUserFavouriteItems(IUserModel userModel, int limit, int skip, OnGetUserFavouriteItemsListener listener);

    public interface OnGetUserFavouriteItemsListener {

        public void onSuccess(IUserModel userModel, List<IInstagramModel> instagramModels);

        public void onFailure(String error);

    }

}
