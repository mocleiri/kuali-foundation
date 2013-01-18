#!/bin/bash -e

PACKAGE=puppet
VERSION=2.7.18

yum erase $PACKAGE -y
yum install $PACKAGE-$VERSION -y
yum install rubygems
yum install ruby-nokogiri

