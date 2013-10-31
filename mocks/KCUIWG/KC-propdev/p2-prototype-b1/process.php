<?php session_start();

   switch($_REQUEST['action']){
    case "editComplianceEntry":
         $id = $_REQUEST['id'];
         $entry = $_SESSION['compliance'][$id];
         //print_r($entry);
         $actionLabel = "Update entry";
        include('modal/modal-compliance/compliance.form.php');

    break;
    case "previewComplianceEntry":
             $id = $_REQUEST['id'];
             $entry = $_SESSION['compliance'][$id];
           //  print_r($entry);
            include('inc/compliance.entry.preview.php');

        break;
    case "appendNewComplianceEntry":
     $id = max(array_keys($_SESSION['compliance']));
       $entry = $_SESSION['compliance'][$id];
      include "inc/compliance.entry.php";
    break;
    default:


    break;

   }

?>