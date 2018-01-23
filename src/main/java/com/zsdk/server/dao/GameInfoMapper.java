package com.zsdk.server.dao;

import com.zsdk.server.model.GameInfo;

import java.util.List;

public interface GameInfoMapper {
    int deleteByPrimaryKey(Integer appId);

    int insert(GameInfo record);

    GameInfo selectByPrimaryKey(Integer appId);

    void update(GameInfo record);

    List<GameInfo> findAll();
}