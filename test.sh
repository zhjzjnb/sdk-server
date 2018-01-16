ab -n 50 -c 10 -p ./query.json -T application/json http://192.168.1.147:8081/sdkserver/login



# ab -n 800 -c 800  http://192.168.0.10/ 
# （-n发出800个请求，-c模拟800并发，相当800人同时访问，后面是测试url）


# Server Software:        Microsoft-HTTPAPI/2.0 
# Server Hostname:        192.168.0.10 
# Server Port:            80

# Document Path:          / 
# Document Length:        315 bytes       HTTP响应数据的正文长度

# Concurrency Level:      800 
# Time taken for tests:   0.914 seconds    所有这些请求处理完成所花费的时间 
# Complete requests:      800             完成请求数 
# Failed requests:        0                失败请求数 
# Write errors:           0                
# Non-2xx responses:      800 
# Total transferred:      393600 bytes     网络总传输量 
# HTML transferred:       252000 bytes     HTML内容传输量 
# Requests per second:    875.22 [#/sec] (mean) 吞吐量-每秒请求数 
# Time per request:       914.052 [ms] (mean)  服务器收到请求，响应页面要花费的时间 
# Time per request:       1.143 [ms] (mean, across all concurrent requests) 并发的每个请求平均消耗时间 
# Transfer rate:          420.52 [Kbytes/sec] received 平均每秒网络上的流量，可以帮助排除是否存在网络流量过大导致响应时间延长的问题


# 网络上消耗的时间的分解： 
# Connection Times (ms) 
#               min  mean[+/-sd] median   max 
# Connect:        0    1   0.5      1       3 
# Processing:   245  534 125.2    570     682 
# Waiting:       11  386 189.1    409     669 
# Total:        246  535 125.0    571     684

# 整个场景中所有请求的响应情况。在场景中每个请求都有一个响应时间 
# 其中 50％ 的用户响应时间小于 571 毫秒 
# 80 ％ 的用户响应时间小于 652 毫秒 
# 最大的响应时间小于 684 毫秒 
# Percentage of the requests served within a certain time (ms) 
#   50%    571 
#   66%    627 
#   75%    646 
#   80%    652 
#   90%    666 
#   95%    677 
#   98%    681 
#   99%    682 
# 100%    684 (longest request)

