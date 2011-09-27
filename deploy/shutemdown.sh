#!/bin/sh

sudo su - tomcat1 /usr/local/tomcat/bin/forced-shutdown.sh
sudo su - tomcat2 /usr/local/tomcat/bin/forced-shutdown.sh
sudo su - tomcat3 /usr/local/tomcat/bin/forced-shutdown.sh
