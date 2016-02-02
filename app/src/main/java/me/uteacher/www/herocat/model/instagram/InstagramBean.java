package me.uteacher.www.herocat.model.instagram;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class InstagramBean {

    /**
     * 数据格式
     * {
     * "likedUsers": [
     *      "5616026600b08664368bdb0e" (备注：存储的是 用户 的ObjectId，数量不固定，Json 数组格式)
     *      "5616026600b08664368bdb1e"
     *      "5616026600b08664368bdb2e"
     *      ],
     * "is_video":    true,
     * "display_src": "http://7xklzg.com1.z0.glb.clouddn.com/okcthunder%25--3880239575749391116.jpg",
     * "store_count": 65,
     * "like_count":  31,
     * "group":       "NBA",
     * "video_url":   "http://7xklzg.com1.z0.glb.clouddn.com/okcthunder%25--3880239575749391116.mp4",
     * "storedUsers": [
     *      "5616026600b08664368bdb0e" (备注：存储的是 用户 的ObjectId，数量不固定，Json 数组格式)
     *      "5616026600b08664368bdb1e"
     *      ],
     * "owner":       "okcthunder",
     * "objectId":    "566e662200b0bf377b2eb456",
     * }
     */

    private String likedUsers;
    private boolean is_video;
    private String display_src;
    private int store_count;
    private int like_count;
    private String group;
    private String video_url;
    private String storedUsers;
    private String owner;
    private String objectId;

    public String getLikedUsers() {
        return likedUsers;
    }

    public void setLikedUsers(String likedUsers) {
        this.likedUsers = likedUsers;
    }

    public boolean getIs_video() {
        return is_video;
    }

    public void setIs_video(boolean is_video) {
        this.is_video = is_video;
    }

    public String getDisplay_src() {
        return display_src;
    }

    public void setDisplay_src(String display_src) {
        this.display_src = display_src;
    }

    public int getStore_count() {
        return store_count;
    }

    public void setStore_count(int store_count) {
        this.store_count = store_count;
    }

    public int getLike_count() {
        return like_count;
    }

    public void setLike_count(int like_count) {
        this.like_count = like_count;
    }

    public String getGroup() {
        return group;
    }

    public void setGroup(String group) {
        this.group = group;
    }

    public String getVideo_url() {
        return video_url;
    }

    public void setVideo_url(String video_url) {
        this.video_url = video_url;
    }

    public String getStoredUsers() {
        return storedUsers;
    }

    public void setStoredUsers(String storedUsers) {
        this.storedUsers = storedUsers;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
