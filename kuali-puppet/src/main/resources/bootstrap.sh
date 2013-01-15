#!/bin/bash -e

PUPPET=puppet
VERSION=2.7.18
PACKAGE=$PUPPET-$VERSION

yum erase $PUPPET -y
yum install $PACKAGE -y
