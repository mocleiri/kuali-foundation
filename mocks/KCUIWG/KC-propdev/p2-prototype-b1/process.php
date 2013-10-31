<?php session_start();

   switch($_REQUEST['action']){
    case "editComplianceEntry":
       echo  $id = $_REQUEST['id'];
         $entry = $_SESSION['compliance'][$id];
         //print_r($entry);
        include('modal/modal-compliance/compliance.form.php');

    break;
    case "appendNewEntry":
     $id = max(array_keys($_SESSION['compliance']));
       $entry = $_SESSION['compliance'][$id];
      include "inc/compliance.entry.php";
    break;
    default:


    break;

   }

?>