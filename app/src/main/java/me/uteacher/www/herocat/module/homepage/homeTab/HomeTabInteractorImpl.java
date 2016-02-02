package me.uteacher.www.herocat.module.homepage.homeTab;

import java.io.InputStream;
import java.util.List;

import me.uteacher.www.herocat.app.BaseInteractor;
import me.uteacher.www.herocat.config.DefaultAppConfig;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.DAOProxyFactory;
import me.uteacher.www.herocat.dao.IDAOQueryCallback;
import me.uteacher.www.herocat.http.HttpClientFactory;
import me.uteacher.www.herocat.http.IStreamResponseCallback;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/20.
 */
public class HomeTabInteractorImpl extends BaseInteractor implements IHomeTabInteractor {

    @Override
    public void getInstagramItemsByGroup(String group, final OnGetInstagramItemListener listener) {
        DAOProxyFactory.getInstagramDAO().queryInstagramItemsByGroup(group
                , DefaultAppConfig.DEFAULT_ITEM_LIMIT
                , DefaultAppConfig.DEFAULT_ITEM_SKIP
                , new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getInstagramItemsByGroup(int limit, int skip, String group, final OnGetInstagramItemListener listener) {
        DAOProxyFactory.getInstagramDAO().queryInstagramItemsByGroup(group, limit, skip
                , new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getInstagramItemsByPopularity(final OnGetInstagramItemListener listener) {
        DAOProxyFactory.getInstagramDAO().queryInstagramItemsByPopularity(DefaultAppConfig.DEFAULT_ITEM_LIMIT
                , DefaultAppConfig.DEFAULT_ITEM_SKIP, new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getInstagramItemsByPopularity(int limit, int skip, final OnGetInstagramItemListener listener) {
        DAOProxyFactory.getInstagramDAO().queryInstagramItemsByPopularity(limit, skip
                , new IDAOQueryCallback<IInstagramModel>() {
            @Override
            public void done(List<IInstagramModel> items, DAOException e) {
                if (e == null) {
                    listener.onSuccess(items);
                } else {
                    listener.onFailure(e.getMessage());
                }
            }
        });
    }

    @Override
    public void getImageStream(String url, final OnGetImageStreamCallback callback) {
        HttpClientFactory.getHttpClient().get(url, new IStreamResponseCallback() {
            @Override
            public void onSuccess(InputStream inputStream) {
                callback.onSuccess(inputStream);
            }

            @Override
            public void onFailure(String error) {
                callback.onFailure(error);
            }
        });
    }

    @Override
    public void locateVideoUri(IInstagramModel instagramModel, onVideoUriLocatedCallback callback) {

    }
}
