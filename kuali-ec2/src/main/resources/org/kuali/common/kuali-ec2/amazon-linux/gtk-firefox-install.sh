#!/bin/bash
# GTK+ and Firefox for Amazon Linux
# Written by Joseph Lawson 2012-06-03
# http://joekiller.com
# http://joekiller.com/2012/06/03/install-firefox-on-amazon-linux-x86_64-compiling-gtk/
 
# chmod 755 ./gtk-firefox.sh
# sudo ./gtk-firefox.sh
 
 
TARGET=/usr/local
 
function init()
{
export installroot=$TARGET/src
export workpath=$TARGET
 
yum --assumeyes install make libjpeg-devel libpng-devel \
libtiff-devel gcc libffi-devel gettext-devel libmpc-devel \
libstdc++46-devel xauth gcc-c++ libtool libX11-devel \
libXext-devel libXinerama-devel libXi-devel libxml2-devel \
libXrender-devel libXrandr-devel libXt dbus-glib alsa-lib-devel
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
wget -r --no-parent --reject "index.html*" -nH --cut-dirs=7 http://releases.mozilla.org/pub/mozilla.org/firefox/releases/latest/linux-x86_64/en-US/
tar xvf firefox*
cd bin
ln -s ../firefox/firefox
ldconfig
}
 
function install()
{
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
}
 

echo $(date)
init
echo $(date)
install ftp://ftp.gnu.org/gnu/autoconf/autoconf-2.69.tar.xz
echo $(date)
