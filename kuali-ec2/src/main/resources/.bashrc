# .bashrc

# Source global definitions
if [ -f /etc/bashrc ]; then
        . /etc/bashrc
fi

# User specific aliases and functions
export PS1="\[\e[36;1m\]\u@\h\w\[\e[32;1m\]$ \[\e[0m\]"
export CLICOLOR=1

ENVIRONMENT=${environment}
JAVA_HOME=/usr/java/${jdk.version}
CATALINA_HOME=/usr/local/tomcat
CATALINA_BASE=/usr/local/$USER
CATALINA_PID=$CATALINA_BASE/logs/catalina.pid
CATALINA_OPTS="$CATALINA_OPTS -Xms${heap.min}"
CATALINA_OPTS="$CATALINA_OPTS -Xmx${heap.max}"
CATALINA_OPTS="$CATALINA_OPTS -XX:MaxPermSize=${heap.maxPermSize}"
CATALINA_OPTS="$CATALINA_OPTS -verbose:gc"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintGCDetails"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintGCDateStamps"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintHeapAtGC"
CATALINA_OPTS="$CATALINA_OPTS -XX:+PrintTenuringDistribution"
CATALINA_OPTS="$CATALINA_OPTS -Xloggc:$CATALINA_BASE/logs/heap.log"
CATALINA_OPTS="$CATALINA_OPTS -XX:HeapDumpPath=$CATALINA_BASE/logs"
CATALINA_OPTS="$CATALINA_OPTS -XX:+HeapDumpOnOutOfMemoryError"
#CATALINA_OPTS="$CATALINA_OPTS -agentlib:hprof=file=$CATALINA_BASE/logs/snapshot.hprof,format=b"

PATH=$JAVA_HOME/bin:$CATALINA_HOME/bin:$PATH
export JAVA_HOME PATH CATALINA_HOME CATALINA_BASE CATALINA_OPTS CATALINA_PID ENVIRONMENT

