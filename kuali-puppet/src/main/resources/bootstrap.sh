#!/bin/bash -e
echo $(date)

RUBY=ruby
PUPPET=puppet

RUBY_VERSION=1.8.7.371
PUPPET_VERSION=2.7.18

RUBY_PACKAGE=$RUBY-$RUBY_VERSION
PUPPET_PACKAGE=$PUPPET-$PUPPET_VERSION

yum -y erase $PUPPET
yum -y erase $RUBY
yum -y install $RUBY_PACKAGE
yum -y install $PUPPET_PACKAGE

echo $(date)