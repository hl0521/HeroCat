package me.uteacher.www.herocat.dao.Proxy;

import me.uteacher.www.herocat.dao.IDAOGetCallback;
import me.uteacher.www.herocat.model.application.IApplicationModel;

/**
 * Created by HL0521 on 2016/1/24.
 */
public interface IApplicationDAO {

    public void getApplication(IDAOGetCallback<IApplicationModel> callback);

}
