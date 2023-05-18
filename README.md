# sdk-server

>[配合android_zsdk使用](https://github.com/zhjzjnb/android_zsdk)

>实现客户端登录和注册，并且token验证，目前客户端不验证token也可以拿到用户的uid

>后台admin管理，可以添加游戏，添加管理人员

>数据存储采用mysql，token临时缓存在redis中


# todo list

- [ ] 是否强制验证token才返回用户uid
- [ ] 支付宝和微信充值接入


# 登录成功token验证
## 使用http post方式发送json到服务器,数据结构如下

```http
http://host/sdkserver/check.do
content-type:application/json
{
	"sign":"String",
	"token":"String",
	"appId":"String"
}
返回也是json restful
{
	"code":"int",
	"msg":"String",
	"data":"JsonObject"
}
```
## 发送字段说明

| 字段        | 类型   |  来源  |
| --------   | -----:  | :----:  |
| sign     | String |   见签名算法|
| token       |   String   |登录成功返回的token|
| appId        |    String   |游戏分配的appId|
| appKey        |    String   |游戏分配的appKey,仅登录签名用,无需发送|

------------

## 返回字段说明


| 字段  | 值   | 含义  |
| --------   | -----:  | :----:  |
| code |    0   |成功|
|   |    -1   |传递的json参数错误|
|   |    -2   |玩家token不存在|
|   |    -3   |appId对应的游戏不存在|
| msg |    String   |code对应的中文|
| data |    JsonObject   |code为0才有数据,{ "uid":"1234567"}|


## token验证算法
```
sign=md5(appId+appKey+token) 32位数小写
上述符号+仅连接作用

```


作者 [zhj](https://github.com/zhjzjnb)   


2018 年 01月 27日 
