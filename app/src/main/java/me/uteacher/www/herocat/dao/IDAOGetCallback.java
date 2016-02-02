package me.uteacher.www.herocat.dao;

/**
 * Created by HL0521 on 2016/1/24.
 */
public interface IDAOGetCallback<T> {

    public void done(T item, DAOException e);
    
}
