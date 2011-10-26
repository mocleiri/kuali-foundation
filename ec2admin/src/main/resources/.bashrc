# .bashrc

# Source global definitions
if [ -f /etc/bashrc ]; then
        . /etc/bashrc
fi

# User specific aliases and functions

ENVIRONMENT=${environment}
JAVA_HOME=/usr/java/default
CATALINA_HOME=/usr/local/tomcat
CATALINA_BASE=/usr/local/$USER
CATALINA_PID=$CATALINA_BASE/logs/catalina.pid
CATALINA_OPTS="-Denvironment=$ENVIRONMENT"
CATALINA_OPTS="$CATALINA_OPTS -Xms512m"
CATALINA_OPTS="$CATALINA_OPTS -Xmx2g"
CATALINA_OPTS="$CATALINA_OPTS -XX:MaxPermSize=256m"
CATALINA_OPTS="$CATALINA_OPTS -verbose:gc"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintGCDetails"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintGCDateStamps"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintHeapAtGC"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintTenuringDistribution"
CATALINA_OPTS="$CATALINA_OPTS -Xloggc:$CATALINA_BASE/logs/heap.log"

PATH=$JAVA_HOME/bin:$CATALINA_HOME/bin:$PATH
export JAVA_HOME PATH CATALINA_HOME CATALINA_BASE CATALINA_OPTS CATALINA_PID ENVIRONMENT

