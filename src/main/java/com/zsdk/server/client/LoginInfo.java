package com.zsdk.server.client;//
//	LoginInfo.java
//	Model file generated using JSONExport: https://github.com/Ahmed-Ali/JSONExport


import com.google.gson.annotations.SerializedName;


public class LoginInfo{

    @SerializedName("appId")
    private String appId;
    @SerializedName("deviceModel")
    private String deviceModel;
    @SerializedName("deviceName")
    private String deviceName;
    @SerializedName("password")
    private String password;
    @SerializedName("platform")
    private String platform;
    @SerializedName("root")
    private String root;
    @SerializedName("sign")
    private String sign;
    @SerializedName("systemName")
    private String systemName;
    @SerializedName("systemVersion")
    private String systemVersion;
    @SerializedName("username")
    private String username;

    public void setAppId(String appId){
        this.appId = appId;
    }
    public String getAppId(){
        return this.appId;
    }
    public void setDeviceModel(String deviceModel){
        this.deviceModel = deviceModel;
    }
    public String getDeviceModel(){
        return this.deviceModel;
    }
    public void setDeviceName(String deviceName){
        this.deviceName = deviceName;
    }
    public String getDeviceName(){
        return this.deviceName;
    }
    public void setPassword(String password){
        this.password = password;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPlatform(String platform){
        this.platform = platform;
    }
    public String getPlatform(){
        return this.platform;
    }
    public void setRoot(String root){
        this.root = root;
    }
    public String getRoot(){
        return this.root;
    }
    public void setSign(String sign){
        this.sign = sign;
    }
    public String getSign(){
        return this.sign;
    }
    public void setSystemName(String systemName){
        this.systemName = systemName;
    }
    public String getSystemName(){
        return this.systemName;
    }
    public void setSystemVersion(String systemVersion){
        this.systemVersion = systemVersion;
    }
    public String getSystemVersion(){
        return this.systemVersion;
    }
    public void setUsername(String username){
        this.username = username;
    }
    public String getUsername(){
        return this.username;
    }

}