#!/bin/bash
 
rm -rf /usr/local/src
rm -rf /etc/ld.so.conf.d/firefox.conf

yum --assumeyes erase make libjpeg-devel libpng-devel \
libtiff-devel gcc libffi-devel gettext-devel libmpc-devel \
libstdc++46-devel xauth gcc-c++ libtool libX11-devel \
libXext-devel libXinerama-devel libXi-devel libxml2-devel \
libXrender-devel libXrandr-devel libXt dbus-glib alsa-lib-devel