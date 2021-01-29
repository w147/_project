#!/bin/bash
# chkconfig:2345 64 36
# description: Tomcat start/stop/restart script.

### BEGIN INIT INFO
# Provides: tomcat
# Required-Start: 
# Should-Start: 
# Required-Stop: 
# Default-Start: 2 3 4 5
# Default-Stop: 0 1 6
# Short-Description: start and stop Tomcat
# Description: Tomcat Service start&restart&stop script
### END INIT INFO

##Written by zero.##
JAVA_HOME=/usr/local/jdk1.8/
JAVA_BIN=/usr/local/jdk1.8/bin
JRE_HOME=/usr/local/jdk1.8/jre
PATH=$PATH:/usr/local/jdk1.8/bin:/usr/local/jdk1.8/jre/bin
CLASSPATH=/usr/local/jdk1.8/jre/lib:/usr/local/jdk1.8/lib:/usr/local/jdk1.8/jre/lib/charsets.jar
TOMCAT_BIN=/usr/local/tomcat/bin
RETVAL=0
prog="Tomcat"

start()
{
   echo "Starting $prog......"
   /bin/bash $TOMCAT_BIN/startup.sh
   RETVAL=$?
   return $RETVAL
}
stop()
{
   echo "Stopping $prog......"
   /bin/bash $TOMCAT_BIN/shutdown.sh
   RETVAL=$?
   return $RETVAL
}
restart(){
   echo "Restarting $prog......"
   stop
   start
}

case "$1" in
   start)
        start
        ;;
   stop)
       stop
       ;;
   restart)
       restart
       ;;
   *)
       echo $"Usage: $0 {start|stop|restart}"
       RETVAL=1
esac
exit $RETVAL