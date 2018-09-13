redis安装：
  启动：redis-server redis.windows.conf

设置成服务：
  redis-server --service-install redis.windows-service.conf --loglevel verbose

卸载服务：redis-server --service-uninstall
开启服务：redis-server --service-start
停止服务：redis-server --service-stop


==================
启动redis-cli
keys * 查询所有key
get key 查询value

TTL key 查询key的过期时间