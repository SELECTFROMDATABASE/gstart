Setlocal
set "zookeeper=%ZOOKEEPER_HOME%/bin/zkServer"
set "redis=%REDIS_HOME%/redis-server.exe %REDIS_HOME%\redis.windows.conf"
start %zookeeper%
start %redis%
Endlocal
