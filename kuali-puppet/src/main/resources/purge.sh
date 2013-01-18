#!/bin/bash -e

#PACKAGE=puppet
#VERSION=2.7.18

#yum erase $PACKAGE -y
#yum install $PACKAGE-$VERSION -y

yum erase puppet -y
yum install puppet -y
yum install rubygems -y
yum install ruby-nokogiri -y
yum install subversion -y

