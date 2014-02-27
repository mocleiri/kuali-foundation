#!/usr/bin/expect

spawn dpkg-reconfigure unattended-upgrades -freadline
expect "Automatically download and install stable updates?"
send "Y\r"

# done
expect eof
