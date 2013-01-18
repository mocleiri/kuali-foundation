#!/bin/bash -e

#
# EC2 AMI ID: ami-05da696c
#

alias yum='yum -y'

yum erase puppet
yum install puppet
yum install rubygems
yum install ruby-nokogiri
yum install subversion

