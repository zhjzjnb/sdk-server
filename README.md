# sdkserver

------

## login and register

## add game manager

## add redis pool

# 登录成功token验证
## 使用http post方式发送json到服务器,数据结构如下

```http
http://host/sdkserver/check.do
content-type:application/json
{
    "sign":"sign",
    "token":"token",
    "appId":"appId"
}

```

| 字段        | 类型   |  来源  |
| --------   | -----:  | :----:  |
| sign     | String |   [见签名算法](how-to-sign)|
| token       |   String   |登录成功返回的token|
| appId        |    String   |游戏分配的appId|
| appKey        |    String   |游戏分配的appKey|

## how to sign
```
sign=md5(appId+appKey+token)小写
上述符号+仅连接作用

```
