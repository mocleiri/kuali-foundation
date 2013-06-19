#!/bin/bash -e

#
# EC2 AMI ID: ami-05da696c
#

yum install subversion -y

yum erase puppet -y
yum install puppet-2.7.18 -y

yum install rubygems-1.8.11 -y
yum install ruby-nokogiri-1.5.2 -y
gem install fog --version 1.8.0 --no-ri --no-rdoc

