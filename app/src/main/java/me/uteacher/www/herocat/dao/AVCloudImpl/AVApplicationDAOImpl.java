package me.uteacher.www.herocat.dao.AVCloudImpl;

import com.avos.avoscloud.AVException;
import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.FindCallback;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.xml.transform.Source;

import me.uteacher.www.herocat.dao.BaseDAO;
import me.uteacher.www.herocat.dao.DAOException;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.dao.Proxy.IApplicationDAO;
import me.uteacher.www.herocat.model.ModelFactory;
import me.uteacher.www.herocat.model.application.IApplicationModel;

/**
 * Created by HL0521 on 2016/1/24.
 */
public class AVApplicationDAOImpl extends BaseDAO implements IApplicationDAO {

    private static final String TAG = "AVApplicationDAOImpl";

    public AVApplicationDAOImpl() {

    }

    // 下面这个方法暂时不用了，因为我在 ApplicationModel 中实现了这个功能
    @Deprecated
    private void addCategoriesInfo(IApplicationModel applicationModel, AVObject avObject) {
        JSONArray jsonArray = avObject.getJSONArray("categories");
        JSONObject jsonObject;
        String key;
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                jsonObject = jsonArray.getJSONObject(i);
                key = jsonObject.keys().next();
                applicationModel.addCategory(key, jsonObject.getString(key));
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    // 下面这个方法暂时不用了，因为我在 ApplicationModel 中实现了这个功能
    @Deprecated
    private void addShareInfo(IApplicationModel applicationModel, AVObject avObject) {
        JSONObject shareObject = avObject.getJSONObject("share");
        for (Iterator<String> iterator = shareObject.keys(); iterator.hasNext(); ) {
            String shareKey = iterator.next();

            HashMap<String, String> infoMap = new HashMap<>();
            try {
                JSONObject infoObject = shareObject.getJSONObject(shareKey);
                for (Iterator<String> iterator1 = infoObject.keys(); iterator1.hasNext(); ) {
                    String infoKey = iterator1.next();
                    infoMap.put(infoKey, infoObject.getString(infoKey));
                }
                applicationModel.addShareInfo(shareKey, infoMap);
            } catch (JSONException e1) {
                e1.printStackTrace();
            }
        }
    }

    @Override
    public void getApplication(final IDAOGetCallback<IApplicationModel> callback) {
        AVQuery<AVObject> avQuery = new AVQuery<>("Application");

        avQuery.findInBackground(new FindCallback<AVObject>() {
            @Override
            public void done(List<AVObject> list, AVException e) {
                if (e == null) {
                    AVObject avObject = list.get(0);
                    IApplicationModel applicationModel = ModelFactory.createApplicationModel(
                            avObject.toJSONObject().toString());

                    // 添加 类别 信息：如 NBA、欧冠、球星 等等
//                    addCategoriesInfo(applicationModel, avObject);
                    // 添加 分享 信息
//                    addShareInfo(applicationModel, avObject);

                    callback.done(applicationModel, null);
                } else {
                    callback.done(null, new DAOException(e.getMessage()));
                }
            }
        });
    }
}
