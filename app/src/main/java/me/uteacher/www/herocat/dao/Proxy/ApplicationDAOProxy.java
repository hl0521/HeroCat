package me.uteacher.www.herocat.dao.Proxy;

import me.uteacher.www.herocat.dao.BaseProxy;
import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.application.IApplicationModel;

/**
 * Created by HL0521 on 2016/1/24.
 */
public class ApplicationDAOProxy extends BaseProxy implements IApplicationDAO {

    private IApplicationDAO applicationDAO;

    public ApplicationDAOProxy(IApplicationDAO applicationDAO) {
        this.applicationDAO = applicationDAO;
    }

    @Override
    public void getApplication(IDAOGetCallback<IApplicationModel> callback) {
        applicationDAO.getApplication(callback);
    }
}
