package com.zsdk.server.listener;


import com.google.gson.Gson;
import com.zsdk.server.cache.CacheManager;
import com.zsdk.server.dao.GameInfoMapper;
import com.zsdk.server.model.UserInfo;
import com.zsdk.server.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by zhj on 17/3/15.
 */
@Repository
public class WebAppListener implements ApplicationListener {

    @Autowired
    GameInfoMapper gameInfoMapper;

    private static boolean loaded = false;

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
//        Log.i("onApplicationEvent:"+Thread.currentThread().getName()+" self:"+this);
        if (loaded){
            return;
        }
        loaded = true;
        CacheManager.getInstance().loadGameData(gameInfoMapper.findAll());

    }
}
