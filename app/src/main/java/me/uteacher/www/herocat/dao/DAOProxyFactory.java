package me.uteacher.www.herocat.dao;

import me.uteacher.www.herocat.dao.AVCloudImpl.AVApplicationDAOImpl;
import me.uteacher.www.herocat.dao.AVCloudImpl.AVInstagramDAOImpl;
import me.uteacher.www.herocat.dao.AVCloudImpl.AVUserDAOImpl;
import me.uteacher.www.herocat.dao.Proxy.ApplicationDAOProxy;
import me.uteacher.www.herocat.dao.Proxy.IApplicationDAO;
import me.uteacher.www.herocat.dao.Proxy.IInstagramDAO;
import me.uteacher.www.herocat.dao.Proxy.IUserDAO;
import me.uteacher.www.herocat.dao.Proxy.InstagramDAOProxy;
import me.uteacher.www.herocat.dao.Proxy.UserDAOProxy;

/**
 * Created by HL0521 on 2016/1/24.
 */
public class DAOProxyFactory {

    private static IUserDAO userDAO;

    public static IUserDAO getUserDAO() {
        if (userDAO == null) {
            userDAO = new UserDAOProxy(new AVUserDAOImpl());
        }
        return userDAO;
    }

    private static IInstagramDAO instagramDAO;

    public static IInstagramDAO getInstagramDAO() {
        if (instagramDAO == null) {
            instagramDAO = new InstagramDAOProxy(new AVInstagramDAOImpl());
        }
        return instagramDAO;
    }

    private static IApplicationDAO applicationDAO;

    public static IApplicationDAO getApplicationDAO() {
        if (applicationDAO == null) {
            applicationDAO = new ApplicationDAOProxy(new AVApplicationDAOImpl());
        }
        return applicationDAO;
    }
}
