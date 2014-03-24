<?php

/*
 * Sets global variables and definitions for references.
 * Chris Rodriguez, clrux@bu.edu
*/


$loc = $_SERVER['SERVER_NAME'];
define ("SERVER_ROOT", "/home/ux/www/prototypes/kc/");
define ("PROTO_VERSION", "p2-prototype-b1");

if ($loc == "localhost") {

    define ('ROOT', '/');

} else {
 
    define ('ROOT', SERVER_ROOT . PROTO_VERSION);

}
?>