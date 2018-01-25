# sdkserver

-----------------------------

## login and register

## add game manager

## add redis pool

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
##发送字段说明

| 字段        | 类型   |  来源  |
| --------   | -----:  | :----:  |
| sign     | String |   见签名算法|
| token       |   String   |登录成功返回的token|
| appId        |    String   |游戏分配的appId|
| appKey        |    String   |游戏分配的appKey,仅登录签名用,无需发送|

------------

##返回字段说明
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
