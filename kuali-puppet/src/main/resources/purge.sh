#!/bin/bash -e

alias yum=yum -y

yum erase puppet
yum erase rubygems
yum erase ruby-nokogiri
yum erase subversion
yum erase man
yum erase zip
yum erase unzip
yum erase wget
yum erase rsync
yum erase openssh-clients
yum erase subversion
yum erase git
yum erase ruby-devel 

rm -rf /usr/java
rm -rf /root/.m2
rm -rf /root/.gem
rm -rf /root/.subversion
