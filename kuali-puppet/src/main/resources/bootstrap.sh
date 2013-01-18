#!/bin/bash -e

alias yum=yum -y

yum erase puppet
yum install puppet
yum install rubygems
yum install ruby-nokogiri
yum install subversion

