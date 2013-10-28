<?php session_start();

      //session_destroy();


    foreach($_REQUEST as $index=>$field){
      $_SESSION[$index] = trim($field);
    }

    print_r($_SESSION);
?>