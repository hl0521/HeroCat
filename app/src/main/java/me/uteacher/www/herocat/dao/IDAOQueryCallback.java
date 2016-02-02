package me.uteacher.www.herocat.dao;

import java.util.List;

/**
 * Created by HL0521 on 2016/1/24.
 */
public interface IDAOQueryCallback<T> {

    public void done(List<T> items, DAOException e);
}
