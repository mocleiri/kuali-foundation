<?php session_start();

    if(isset($_REQUEST["id"])){
    $id = $_REQUEST["id"];
     if(isset($_SESSION['person'][$id])) unset($_SESSION['person'][$_REQUEST["id"]]);
    }


  ?>
