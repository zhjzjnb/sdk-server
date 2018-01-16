package com.zsdk.server.model;

import java.util.Date;

public class UserInfo extends UserInfoKey {
    private String password;

    private String platform;

    private Short root;

    private String systemVersion;

    private String systemName;

    private String deviceModel;

    private String deviceName;

    private Date registerTime;

    private String registerPlace;

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform == null ? null : platform.trim();
    }

    public Short getRoot() {
        return root;
    }

    public void setRoot(Short root) {
        this.root = root;
    }

    public String getSystemVersion() {
        return systemVersion;
    }

    public void setSystemVersion(String systemVersion) {
        this.systemVersion = systemVersion == null ? null : systemVersion.trim();
    }

    public String getSystemName() {
        return systemName;
    }

    public void setSystemName(String systemName) {
        this.systemName = systemName == null ? null : systemName.trim();
    }

    public String getDeviceModel() {
        return deviceModel;
    }

    public void setDeviceModel(String deviceModel) {
        this.deviceModel = deviceModel == null ? null : deviceModel.trim();
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName == null ? null : deviceName.trim();
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public String getRegisterPlace() {
        return registerPlace;
    }

    public void setRegisterPlace(String registerPlace) {
        this.registerPlace = registerPlace == null ? null : registerPlace.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", password=").append(password);
        sb.append(", platform=").append(platform);
        sb.append(", root=").append(root);
        sb.append(", systemVersion=").append(systemVersion);
        sb.append(", systemName=").append(systemName);
        sb.append(", deviceModel=").append(deviceModel);
        sb.append(", deviceName=").append(deviceName);
        sb.append(", registerTime=").append(registerTime);
        sb.append(", registerPlace=").append(registerPlace);
        sb.append("]");
        return sb.toString();
    }
}