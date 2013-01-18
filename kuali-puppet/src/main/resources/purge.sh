#!/bin/bash -e

yum erase puppet -y
yum erase rubygems -y
yum erase ruby-nokogiri -y
yum erase subversion -y
yum erase man -y
yum erase zip -y
yum erase unzip -y
yum erase wget -y
yum erase rsync -y
yum erase openssh-clients -y
yum erase subversion -y
yum erase git -y
yum erase ruby-devel -y 

rm -rf /usr/java
rm -rf /root/.m2
rm -rf /root/.gem
rm -rf /root/.subversion
rm -rf /usr/local/tomcat
rm -rf /home/tomcat
