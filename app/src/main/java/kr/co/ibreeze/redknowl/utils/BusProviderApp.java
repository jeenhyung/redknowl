package kr.co.ibreeze.redknowl.utils;

import com.squareup.otto.Bus;
import com.squareup.otto.ThreadEnforcer;

/**
 * Created by cozmo-air1 on 2015-01-06.
 */
public final class BusProviderApp {
    private static final Bus BUS  = new Bus();
    public static Bus getInstance() {return  BUS; }
    private BusProviderApp(){}
}
