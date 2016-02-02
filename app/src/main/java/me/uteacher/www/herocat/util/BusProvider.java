package me.uteacher.www.herocat.util;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by HL0521 on 2016/2/2.
 */
public final class BusProvider {

    private static final Bus BUS = new Bus(ThreadEnforcer.ANY);

    public static Bus getInstance() {
        return BUS;
    }

    private BusProvider() {

    }
}
