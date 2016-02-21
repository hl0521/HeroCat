package me.uteacher.www.herocat.model.application;

/**
 * Created by HL0521 on 2016/1/21.
 */
public class ApplicationBean {

    /**
     * 数据格式
     * "versionNewFeature": "1. 视频和图片浏览，点赞和收藏功能\n
     *                       2. 视频和图片的分享和保存至Photo Album\n
     *                       3. App登录、注册和找回密码功能\n
     *                       4. App SNS第三方登录，包括微信、微博和QQ\n
     *                       5. 其他功能",
     * "categories": [
     *      {"hot": "热门"},
     *      {"NBA": "NBA"},
     *      {"UEFA": "欧冠"},
     *      {"Gym": "健身"},
     *      {"Star": "球星"}
     *      ],
     * "share": {
     *      "image": {
     *          "title": "英雄猫－给爱体育的你",
     *          "desc": "我在英雄猫发现了NBA和欧冠球星的热门照片。快来跟着球星们一起享受体育快乐吧！",
     *          "url": "http://www.uteacher.me/instapanda/share/"
     *      },
     *      "video": {
     *          "title": "英雄猫－给爱体育的你",
     *          "desc": "我在英雄猫发现了NBA和欧冠球星的热门视频。快来跟着球星们一起享受体育快乐吧！",
     *          "url": "http://www.uteacher.me/instapanda/share/"
     *      },
     *      "app": {
     *          "title": "英雄猫－给爱体育的你",
     *          "desc": "忍受不了别人对你喜爱的球队和球星无脑黑？热爱体育的你需要一个简单纯粹的APP，跟着球队和球星一起享受体育快乐!"
     *      }
     *      },
     * "currentVersion": 1,
     * "downloadUrl":    "http://www.uteacher.me/instapanda/getapp",
     * "versionString":  "v1.0.0",
     * "objectId":       "55caf04e40ac41014f86514e",
     * "createdAt":      "2015-08-12T07:05:50.929Z",
     * "updatedAt":      "2015-09-30T01:56:12.449Z"
     * "className":      "Application"
     * }
     */

    /**
     * LeanCloud中传输过来的数据为 JSON 格式的，以下各 变量名 对应 LeanCloud 中 JSON 格式数据的关键字
     * 用来提取相应 变量名 中的变量
     * <p/>
     * categories        app的类别
     * versionNewFeature 版本的新特性
     * currentVersion    当前的版本序列号（int型数据）
     * versionString     当前的版本序列号（字符串型）
     * downloadUrl       新版本软件的下载地址
     * share             分享相关的信息
     * objectId          LeanCloud中Application对象对应的ObjectId
     */
    private String categories;          // JSONArray  格式
    private String versionNewFeature;   // JSONObject 格式
    private int currentVersion;
    private String versionString;
    private String downloadUrl;
    private String share;
    private String objectId;

    public String getVersionString() {
        return versionString;
    }

    public void setVersionString(String versionString) {
        this.versionString = versionString;
    }

    public String getShare() {
        return share;
    }

    public void setShare(String share) {
        this.share = share;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    public String getCategories() {
        return categories;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public String getVersionNewFeature() {
        return versionNewFeature;
    }

    public void setVersionNewFeature(String versionNewFeature) {
        this.versionNewFeature = versionNewFeature;
    }

    public int getCurrentVersion() {
        return currentVersion;
    }

    public void setCurrentVersion(int currentVersion) {
        this.currentVersion = currentVersion;
    }

    public String getDownloadUrl() {
        return downloadUrl;
    }

    public void setDownloadUrl(String downloadUrl) {
        this.downloadUrl = downloadUrl;
    }
}
