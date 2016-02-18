package me.uteacher.www.herocat.dao.AVCloudImpl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;
import com.avos.avoscloud.GetCallback;
import com.avos.avoscloud.SaveCallback;

import org.json.JSONArray;
import org.json.JSONException;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import me.uteacher.www.herocat.dao.BaseDAO;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.IDAODoneCallback;
import me.uteacher.www.herocat.dao.IDAOQueryCallback;
import me.uteacher.www.herocat.dao.Proxy.IInstagramDAO;
import me.uteacher.www.herocat.model.ModelFactory;
import me.uteacher.www.herocat.model.instagram.IInstagramModel;

/**
 * Created by HL0521 on 2016/1/25.
 */
public class AVInstagramDAOImpl extends BaseDAO implements IInstagramDAO {

    public AVInstagramDAOImpl() {

    }

    @Override
    public void userLikeInstagramItem(String itemId, final String userId, final IDAODoneCallback callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.getInBackground(itemId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    avObject.addUnique("likedUsers", userId);
                    avObject.increment("like_count");
                    avObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                callback.done(null);
                            } else {
                                callback.done(new DAOException(e.getMessage()));
                            }
                        }
                    });
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void userCancelLikeInstagramItem(String itemId, final String userId, final IDAODoneCallback callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.getInBackground(itemId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    List<String> users = new ArrayList<String>();
                    users.add(userId);
                    avObject.removeAll("likedUsers", users);
                    avObject.increment("like_count", -1);
                    avObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                callback.done(null);
                            } else {
                                callback.done(new DAOException(e.getMessage()));
                            }
                        }
                    });
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void userFavouriteInstagramItem(String itemId, final String userId, final IDAODoneCallback callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.getInBackground(itemId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    avObject.addUnique("storedUsers", userId);
                    avObject.increment("store_count");
                    avObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                callback.done(null);
                            } else {
                                callback.done(new DAOException(e.getMessage()));
                            }
                        }
                    });
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void userCancelFavouriteInstagramItem(final String itemId, final String userId
            , final IDAODoneCallback callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.getInBackground(itemId, new GetCallback<AVObject>() {
            @Override
            public void done(AVObject avObject, AVException e) {
                if (e == null) {
                    List<String> users = new ArrayList<>();
                    users.add(userId);
                    avObject.removeAll("storedUsers", users);
                    avObject.increment("stored_count", -1);
                    avObject.saveInBackground(new SaveCallback() {
                        @Override
                        public void done(AVException e) {
                            if (e == null) {
                                callback.done(null);
                            } else {
                                callback.done(new DAOException(e.getMessage()));
                            }
                        }
                    });
                } else {
                    callback.done(new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void queryInstagramItemsByGroup(String group, int limit, int skip
            , final IDAOQueryCallback<IInstagramModel> callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.whereEqualTo("group", group);
        avQuery.setLimit(limit);
        avQuery.setSkip(skip);
        avQuery.orderByDescending("createdAt");
        avQuery.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        avQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(3));

        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    List<IInstagramModel> items = new ArrayList<>();
                    for (AVObject avObject : list) {
                        IInstagramModel item = ModelFactory.createInstagramModel(avObject.toJSONObject().toString());
//                        addLikedUsersToModel(item, avObject);
//                        addStoredUsersToModel(item, avObject);
                        items.add(item);
                    }
                    callback.done(items, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void queryInstagramItemsByPopularity(int limit, int skip, final IDAOQueryCallback<IInstagramModel> callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        avQuery.orderByDescending("like_count");
        avQuery.setLimit(limit);
        avQuery.setSkip(skip);
        avQuery.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        avQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(3));

        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    List<IInstagramModel> items = new ArrayList<>();
                    for (AVObject avObject : list) {
                        IInstagramModel item = ModelFactory.createInstagramModel(avObject.toJSONObject().toString());
//                        addLikedUsersToModel(item, avObject);
//                        addStoredUsersToModel(item, avObject);
                        items.add(item);
                    }
                    callback.done(items, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Override
    public void queryUserFavouriteItems(String userId, int limit, int skip, final IDAOQueryCallback<IInstagramModel> callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("InstagramNode");
        // 关于下面这个方法，我不太确定，storedUsers 的值是一个 JSON 数组，包含多个值
        avQuery.whereEqualTo("storedUsers", userId);
        avQuery.setLimit(limit);
        avQuery.setSkip(skip);
        avQuery.orderByDescending("createdAt");
        avQuery.setCachePolicy(AVQuery.CachePolicy.NETWORK_ELSE_CACHE);
        avQuery.setMaxCacheAge(TimeUnit.DAYS.toMillis(3));

        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    List<IInstagramModel> items = new ArrayList<>();
                    for (AVObject avObject : list) {
                        IInstagramModel item = ModelFactory.createInstagramModel(avObject.toJSONObject().toString());
//                        addLikedUsersToModel(item, avObject);
//                        addStoredUsersToModel(item, avObject);
                        items.add(item);
                    }
                    callback.done(items, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }

    @Deprecated
    private void addLikedUsersToModel(IInstagramModel instagramModel, AVObject avObject) {
        // TODO: 2016/1/25 此处已知来自于 LeanCloud 的数据中包含的是 JSONArray 对象，如果不是，下面的代码就会出问题!
//        JSONArray jsonArray = avObject.getJSONArray("likedUsers");
        ArrayList jsonArray = (ArrayList) avObject.get("likedUsers");
        for (int i = 0; i < jsonArray.size(); i++) {
            instagramModel.addLikedUser((String) jsonArray.get(i));
        }
    }

    @Deprecated
    private void addStoredUsersToModel(IInstagramModel instagramModel, AVObject avObject) {
        // TODO: 2016/1/25 此处已知来自于 LeanCloud 的数据中包含的是 JSONArray 对象，如果不是，下面的代码就会出问题!
        JSONArray jsonArray = avObject.getJSONArray("storedUsers");
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                instagramModel.addStoredUser(jsonArray.getString(i));
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
    }
}
