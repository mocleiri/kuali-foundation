<?php session_start();

   switch($_REQUEST['action']){
    case "editComplianceEntry":
         $id = $_REQUEST['id'];
         $entry = $_SESSION['compliance'][$id];
         //print_r($entry);
         $actionLabel = "Update entry";
         $action = "updateComplianceEntry";
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
    case "updateComplianceEntry":
    $id = $_REQUEST['id'];
        $entry = $_SESSION['compliance'][$id];
        include "inc/compliance.entry.php";
    break;
    case "appendAttachmentProposalEntry":

           $id = max(array_keys($_SESSION['attachments']['proposal']));
           $entry = $_SESSION['attachments']['proposal'][$id];
          include "inc/attachments.proposal.entry.php";
        break;
    case "editAttachmentProposalEntry":
        $id = $_REQUEST['id'];
          // $entry = $_SESSION['attachments']['proposal'][$id];
          //print_r($entry);
          $actionLabel = "Update entry";
          $action = "updateAttachmentProposalEntry";
         include('inc/attachments.proposal.form.php');

    break;
    case "previewAttachmentProposalEntry":
          $id = $_REQUEST['id'];
           //$entry = $_SESSION['attachments']['proposal'][$id];
         //  print_r($entry);
          include('inc/attachments.proposal.preview.php');
    break;
    default:


    break;

   }

?>