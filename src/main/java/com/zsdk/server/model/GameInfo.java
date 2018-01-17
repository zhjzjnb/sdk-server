package com.zsdk.server.model;

import java.util.Date;

public class GameInfo {
    private Integer appId;

    private String appKey;

    private String appSecret;

    private String publicKey;

    private String privateKey;

    private Date createTime;

    private String gameName;

    private String payCallback;

    public Integer getAppId() {
        return appId;
    }

    public void setAppId(Integer appId) {
        this.appId = appId;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey == null ? null : appKey.trim();
    }

    public String getAppSecret() {
        return appSecret;
    }

    public void setAppSecret(String appSecret) {
        this.appSecret = appSecret == null ? null : appSecret.trim();
    }

    public String getPublicKey() {
        return publicKey;
    }

    public void setPublicKey(String publicKey) {
        this.publicKey = publicKey == null ? null : publicKey.trim();
    }

    public String getPrivateKey() {
        return privateKey;
    }

    public void setPrivateKey(String privateKey) {
        this.privateKey = privateKey == null ? null : privateKey.trim();
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGameName() {
        return gameName;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName == null ? null : gameName.trim();
    }

    public String getPayCallback() {
        return payCallback;
    }

    public void setPayCallback(String payCallback) {
        this.payCallback = payCallback == null ? null : payCallback.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", appId=").append(appId);
        sb.append(", appKey=").append(appKey);
        sb.append(", appSecret=").append(appSecret);
        sb.append(", publicKey=").append(publicKey);
        sb.append(", privateKey=").append(privateKey);
        sb.append(", createTime=").append(createTime);
        sb.append(", gameName=").append(gameName);
        sb.append(", payCallback=").append(payCallback);
        sb.append("]");
        return sb.toString();
    }
}