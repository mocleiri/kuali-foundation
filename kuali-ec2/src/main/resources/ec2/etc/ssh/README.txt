The only change to the sshd_config file in this directory
is to flip PasswordAuthentication to "yes" instead of "no"

OLE wanted people to be able to authenticate via a password
instead of using public/private keys.

They have vendors that are using sftp to upload gigantic text files of data