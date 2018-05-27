Setlocal
set "zookeeper=%ZOOKEEPER_HOME%/bin/zkServer"
set "redis=%REDIS_HOME%/redis-windows-master/src/msopentech/redis-64.2.8.2101/redis-server"
start %zookeeper%
start %redis%
Endlocal