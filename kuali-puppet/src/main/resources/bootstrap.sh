#!/bin/bash -e

#
# EC2 AMI ID: ami-05da696c
#

yum erase puppet -y
yum install puppet-2.7.18 -y
yum install rubygems -y
yum install ruby-nokogiri -y
yum install subversion -y

