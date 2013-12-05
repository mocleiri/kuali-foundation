<?php session_start();

    /*
     *	Saves request variables to $SESSION array to use throughout the application.
     * 	-- Tadas Paegle
    */


      foreach($_REQUEST as $index=>$field){
          $_SESSION[$index] = trim($field);
      }


    echo "<pre>";
    print_r($_SESSION);
    echo "</pre>";
    echo date("n/j/Y g:i A");
?>