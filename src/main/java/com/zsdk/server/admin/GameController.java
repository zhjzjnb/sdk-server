package com.zsdk.server.admin;

import com.zsdk.server.bean.Data;
import com.zsdk.server.bean.Result;
import com.zsdk.server.cache.CacheManager;
import com.zsdk.server.dao.GameInfoMapper;
import com.zsdk.server.model.GameInfo;
import com.zsdk.server.util.HttpUtil;
import com.zsdk.server.util.Log;
import com.zsdk.server.util.SystemUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/games")
public class GameController {

    @Autowired
    GameInfoMapper gameInfoMapper;

    @RequestMapping(path = "/showGames", method = RequestMethod.GET)
    public String showGames() {
        return "games";
    }


    @RequestMapping(path = "/getAllGames", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Data<GameInfo> getAllGames( @RequestParam int page, @RequestParam int rows ) {
        Data<GameInfo> data = new
                Data<GameInfo>();
        List<GameInfo> list = CacheManager.getInstance().queryGamePage(page,rows);
        data.setTotal(CacheManager.getInstance().getGameCount());
        data.setRows(list);
        return data;
    }

    @RequestMapping(path="/saveGame", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result saveGame( @RequestParam int appId,@RequestParam String payCallback, @RequestParam String name) {
        Result result = new Result();
        GameInfo gameInfo = CacheManager.getInstance().queryGame(appId);
        if (gameInfo != null){
            HttpUtil.optionSuccess(result);
            gameInfo.setGameName(name);
            gameInfo.setPayCallback(payCallback);
            gameInfoMapper.update(gameInfo);
            CacheManager.getInstance().saveGame(gameInfo);
        }
        return result;
    }


    @RequestMapping(path="/removeGame", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result removeGame( @RequestParam int appId) {
        Result result = new Result();
        GameInfo gameInfo = CacheManager.getInstance().queryGame(appId);
        if (gameInfo != null){
            HttpUtil.optionSuccess(result);
            CacheManager.getInstance().removeGame(appId);
            gameInfoMapper.deleteByPrimaryKey(appId);
        }
        return result;
    }

    @RequestMapping(path="/addGame", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
    @ResponseBody
    public Result addGame(@RequestParam String payCallback, @RequestParam String name) {
        Result result = new Result();
        HttpUtil.optionSuccess(result);
        GameInfo gameInfo = new GameInfo();
        gameInfo.setGameName(name);
        gameInfo.setPayCallback(payCallback);
        gameInfo.setAppKey(SystemUtil.generateAppKey());
        gameInfo.setAppSecret(SystemUtil.generateAppSecret());
        gameInfo.setPublicKey(SystemUtil.genPublicKey());
        gameInfo.setPrivateKey(SystemUtil.genPrivateKey());
        gameInfo.setCreateTime(new Date());
        gameInfoMapper.insert(gameInfo);
        CacheManager.getInstance().addGame(gameInfo);
        return result;
    }


}