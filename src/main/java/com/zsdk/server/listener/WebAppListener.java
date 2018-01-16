package com.zsdk.server.listener;


import com.google.gson.Gson;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.util.Log;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhj on 17/3/15.
 */
public class WebAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        Log.i("WebAppListener contextInitialized");


    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Log.i("WebAppListener contextDestroyed");
    }
}
