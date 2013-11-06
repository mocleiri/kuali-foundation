<?php session_start();

   switch($_REQUEST['action']){
    case "removePersonnelSession":

       removeSessionModuleEntry('person');

       if(isset($_REQUEST["id"])){

           $id = $_REQUEST["id"];
           if(isset($_SESSION['compliance'][$id])) unset($_SESSION['compliance'][$id]);
       }

    break;
   case "addComplianceEntry":
        foreach($_REQUEST as $index=>$field){
           $list[$index] = trim($field);
        }
        $_SESSION['compliance'][] = $list;

         $id = max(array_keys($_SESSION['compliance']));
         $entry = $_SESSION['compliance'][$id];
         include "inc/compliance.entry.php";

    break;
    break;
    case "editComplianceEntry":
         $id = $_REQUEST['id'];
         $entry = $_SESSION['compliance'][$id];
         //print_r($entry);

         $exemptions = explode(',', $entry['exemptions']);

         $entry['exemptions'] = str_replace(' ', '', '"'.implode('","',$exemptions).'"');
         $actionLabel = "Update entry";
         $action = "updateComplianceEntry";

         include('themes/kc/inc/header.php');
         include('modal/modal-compliance/compliance.form.php');
         include('themes/kc/inc/footer.php');

    break;
    case "previewComplianceEntry":
             $id = $_REQUEST['id'];
             $entry = $_SESSION['compliance'][$id];
           //  print_r($entry);

            include('inc/compliance.entry.preview.php');

        break;
    case "updateComplianceEntry":
        foreach($_REQUEST as $index=>$field){
            if(is_array($field)){
            //get the exceptions array into a string
               $list[$index] = implode(', ', $field);
            }else{
                $list[$index] = trim($field);
             }
          }
          $_SESSION['compliance'][$_REQUEST['id']] = $list;
        $id = $_REQUEST['id'];
        $entry = $_SESSION['compliance'][$id];
        include "inc/compliance.entry.php";
    break;
    case "removeComplianceEntry":

        if(isset($_REQUEST["id"])){

            $id = $_REQUEST["id"];
            if(isset($_SESSION['compliance'][$id])) unset($_SESSION['compliance'][$id]);
        }

     break;
    case "addAttachmentsProposalEntry":

         foreach($_REQUEST as $index=>$field){
            $list[$index] = trim($field);
          }

          $list["uploadFile"] = str_replace("C:\\fakepath\\", "", $list["uploadFile"]);
          $list['uploadTime'] = date("n/j/Y g:i A");

          $_SESSION['attachments']['proposal'][] = $list;

          $id = max(array_keys($_SESSION['attachments']['proposal']));
          $entry = $_SESSION['attachments']['proposal'][$id];
         include "inc/attachments.proposal.entry.php";
     break;

    case "editAttachmentProposalEntry":
        $id = $_REQUEST['id'];
          $entry = $_SESSION['attachments']['proposal'][$id];
          //print_r($entry);
          $actionLabel = "Update entry";
          $action = "updateAttachmentProposalEntry";
        include('themes/kc/inc/header.php');
         include('inc/attachments.proposal.form.php');
         include('themes/kc/inc/footer.php');

    break;
    case "previewAttachmentProposalEntry":
          $id = $_REQUEST['id'];
          $entry = $_SESSION['attachments']['proposal'][$id];
         //  print_r($entry);
          include('inc/attachments.proposal.preview.php');
    break;
    case "updateAttachmentProposalEntry":
            foreach($_REQUEST as $index=>$field){
               $list[$index] = trim($field);
           }

           $list["uploadFile"] = $_SESSION['attachments']['proposal'][$_REQUEST['id']]["uploadFile"];
           $list['uploadTime'] = $_SESSION['attachments']['proposal'][$_REQUEST['id']]['uploadTime'];

           $_SESSION['attachments']['proposal'][$_REQUEST['id']] = $list;
            $id = $_REQUEST['id'];
            $entry = $_SESSION['attachments']['proposal'][$id];
            include "inc/attachments.proposal.entry.php";
    break;
    case "removeAttachmentsProposalEntry":

            if(isset($_REQUEST["id"])){

                $id = $_REQUEST["id"];
                if(isset($_SESSION['attachments']['proposal'][$id])) unset($_SESSION['attachments']['proposal'][$id]);
            }

         break;
     case "addAttachmentsPersonnelEntry":
        //print_r($_REQUEST);
        foreach($_REQUEST as $index=>$field){
            $list[$index] = trim($field);
          }

          $list["uploadFile"] = str_replace("C:\\fakepath\\", "", $list["uploadFile"]);
          $list['uploadTime'] = date("n/j/Y g:i A");

          $_SESSION['attachments']['personnel'][] = $list;
          $id = max(array_keys($_SESSION['attachments']['personnel']));
          $entry = $_SESSION['attachments']['personnel'][$id];
          include "inc/attachments.personnel.entry.php";

     break;
    case "editAttachmentPersonnelEntry":

        $id = $_REQUEST['id'];
          $entry = $_SESSION['attachments']['personnel'][$id];
         // print_r($entry);
          $actionLabel = "Update entry";
          $action = "updateAttachmentPersonnelEntry";
        include('themes/kc/inc/header.php');
         include('inc/attachments.personnel.form.php');
         include('themes/kc/inc/footer.php');

    break;
    case "previewAttachmentPersonnelEntry":
          $id = $_REQUEST['id'];
          $entry = $_SESSION['attachments']['personnel'][$id];
         //  print_r($entry);
          include('inc/attachments.personnel.preview.php');
    break;
    case "updateAttachmentPersonnelEntry":
         foreach($_REQUEST as $index=>$field){
            $list[$index] = trim($field);
         }

         $list["uploadFile"] = $_SESSION['attachments']['personnel'][$_REQUEST['id']]["uploadFile"];
         $list['uploadTime'] = $_SESSION['attachments']['personnel'][$_REQUEST['id']]['uploadTime'];

        $_SESSION['attachments']['personnel'][$_REQUEST['id']] = $list;
        $id = $_REQUEST['id'];
        $entry = $_SESSION['attachments']['personnel'][$id];
        include "inc/attachments.personnel.entry.php";
    break;
    case "removeAttachmentsPersonnelEntry":

         if(isset($_REQUEST["id"])){
             $id = $_REQUEST["id"];
             if(isset($_SESSION['attachments']['personnel'][$id])) unset($_SESSION['attachments']['personnel'][$id]);
         }

      break;
    default:


    break;

   }

?>