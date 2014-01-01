#!/bin/bash
#
# Copyright 2004-2014 The Kuali Foundation
#
# Licensed under the Educational Community License, Version 2.0 (the "License");
# you may not use this file except in compliance with the License.
# You may obtain a copy of the License at
#
# http://www.opensource.org/licenses/ecl2.php
#
# Unless required by applicable law or agreed to in writing, software
# distributed under the License is distributed on an "AS IS" BASIS,
# WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
# See the License for the specific language governing permissions and
# limitations under the License.
#

# GTK+ and Firefox for Amazon Linux
# Written by Joseph Lawson 2012-06-03
# http://joekiller.com
# http://joekiller.com/2012/06/03/install-firefox-on-amazon-linux-x86_64-compiling-gtk/
 
# chmod 755 ./gtk-firefox.sh
# sudo ./gtk-firefox.sh
 
 
TARGET=/usr/local
LATEST_MOZILLA_URL=http://download.cdn.mozilla.net/pub/mozilla.org/firefox/releases/latest/linux-x86_64/en-US/
 
function install_amazon_managed_packages()
{
yum --assumeyes install make libjpeg-devel libpng-devel \
libtiff-devel gcc libffi-devel gettext-devel libmpc-devel \
libstdc++46-devel xauth gcc-c++ libtool libX11-devel \
libXext-devel libXinerama-devel libXi-devel libxml2-devel \
libXrender-devel libXrandr-devel libXt dbus-glib alsa-lib-devel
}

function init()
{
export installroot=$TARGET/src
export workpath=$TARGET

mkdir -p $workpath
mkdir -p $installroot
cd $installroot
PKG_CONFIG_PATH="$workpath/lib/pkgconfig"
PATH=$workpath/bin:$PATH
export PKG_CONFIG_PATH PATH
 
bash -c "
cat << EOF > /etc/ld.so.conf.d/firefox.conf
$workpath/lib
$workpath/firefox
EOF
ldconfig
"
}
 
function finish()
{
cd $workpath
wget -r --no-parent --reject "index.html*" -nH --cut-dirs=7 $LATEST_MOZILLA_URL
tar xf firefox*
cd bin
ln -s ../firefox/firefox
ldconfig
}
 
function install()
{
echo Install Begin ---- $(date) - $1
wget $1
FILE=`basename $1`
if [ ${FILE: -3} == ".xz" ]
then tar xfJ $FILE
else tar xf $FILE
fi
SHORT=${FILE:0:4}*
cd $SHORT
./configure --prefix=$workpath
make --jobs=3 --silent
make install --jobs=3 --silent
ldconfig
cd ..
echo Install Complete - $(date) - $1
}
 

echo Starting Firefox Install - $(date)
install_amazon_managed_packages
init
install ftp://ftp.gnu.org/gnu/autoconf/autoconf-2.69.tar.xz
install http://download.savannah.gnu.org/releases/freetype/freetype-2.4.9.tar.gz
install http://www.freedesktop.org/software/fontconfig/release/fontconfig-2.9.0.tar.gz
install http://ftp.gnome.org/pub/gnome/sources/glib/2.32/glib-2.32.3.tar.xz
install http://cairographics.org/releases/pixman-0.26.0.tar.gz
install http://cairographics.org/releases/cairo-1.12.2.tar.xz
install http://ftp.gnome.org/pub/gnome/sources/pango/1.30/pango-1.30.0.tar.xz
install http://ftp.gnome.org/pub/gnome/sources/atk/2.4/atk-2.4.0.tar.xz
install http://ftp.gnome.org/pub/GNOME/sources/gdk-pixbuf/2.26/gdk-pixbuf-2.26.1.tar.xz
install http://ftp.gnome.org/pub/gnome/sources/gtk+/2.24/gtk+-2.24.10.tar.xz
finish
echo Firefox Install Complete - $(date)

