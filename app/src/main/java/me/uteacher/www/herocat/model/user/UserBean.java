package me.uteacher.www.herocat.model.user;

/**
 * Created by HL0521 on 2016/1/19.
 */
public class UserBean {

    private String userName;
    private String passWord;

    private String objectId;

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public String getObjectId() {
        return objectId;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }
}
