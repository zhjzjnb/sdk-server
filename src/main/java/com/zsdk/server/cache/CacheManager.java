package com.zsdk.server.cache;

import com.zsdk.server.bean.Result;
import com.zsdk.server.config.Configuration;
import com.zsdk.server.model.AdminUser;
import com.zsdk.server.model.GameInfo;
import com.zsdk.server.service.AdminUserService;
import com.zsdk.server.util.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhj on 18/1/17.
 */

public class CacheManager {

    private Map<Integer, GameInfo> games;

    private static final CacheManager _instance = new CacheManager();

    private CacheManager() {
        games = new ConcurrentHashMap<Integer, GameInfo>();
    }

    public static CacheManager getInstance() {
        return _instance;
    }

    public synchronized GameInfo getGame(int appId){
        if(this.games.containsKey(appId)){
            return this.games.get(appId);
        }
        return null;
    }

    public synchronized void addGame(GameInfo gameInfo){

        if(games.containsKey(gameInfo.getAppId())){
            Log.e("The appID is already is exists. add game failed."+gameInfo.getAppId());
            return;
        }

        games.put(gameInfo.getAppId(), gameInfo);

    }

    public void loadGameData(List<GameInfo> gameLst){
        games.clear();
        for(GameInfo game : gameLst){
            games.put(game.getAppId(), game);
        }
        Log.i("Load games :" + games.size());
    }

    public synchronized List<GameInfo> getGameList(){

        return new ArrayList<GameInfo>(games.values());
    }
}
