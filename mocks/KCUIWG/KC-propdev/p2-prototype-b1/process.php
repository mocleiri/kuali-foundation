<?php session_start();

   switch($_REQUEST['action']){

   case "storeSessions":

      foreach($_REQUEST as $index=>$field){

           $_SESSION[$index] = trim($field);
        }

   break;
   case "addKeyPersonnel":


           $id = $_SESSION['personnelId'];
           $_SESSION['keyPersonnel'][$id]['personnel_role'] = $_REQUEST['personnel_role'];
           $_SESSION['keyPersonnel'][$id]['multiple_pis'] = $_REQUEST['multiple_pis'];
           $_SESSION['keyPersonnel'][$id]['keyperson_role'] = $_REQUEST['keyperson_role'];
//echo "<pre>";
//print_r($_SESSION['keyPersonnel'][$id]);
//echo "</pre>";
            include('themes/kc/inc/header.php');
           include "prop.keypersonnel.person.php";
           include('themes/kc/inc/footer.php');

        break;
    case "removePersonnelSession":

       if(isset($_REQUEST["id"])){

           $id = $_REQUEST["id"];
           if(isset($_SESSION['keyPersonnel'][$id])) unset($_SESSION['keyPersonnel'][$id]);
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
        $id = $_REQUEST['id'];
         foreach($_REQUEST as $index=>$field){
            $entry[$index] = trim($field);
         }

         $entry["uploadFile"] = $_SESSION['attachments']['personnel'][$id]["uploadFile"];
         $entry['uploadTime'] = $_SESSION['attachments']['personnel'][$id]['uploadTime'];

        $_SESSION['attachments']['personnel'][$id] = $entry;

        //$entry = $_SESSION['attachments']['personnel'][$id];
        include "inc/attachments.personnel.entry.php";
    break;
    case "removeAttachmentsPersonnelEntry":

         if(isset($_REQUEST["id"])){
             $id = $_REQUEST["id"];
             if(isset($_SESSION['attachments']['personnel'][$id])) unset($_SESSION['attachments']['personnel'][$id]);
         }

      break;

      case "addAttachmentsInternalEntry":
              //print_r($_REQUEST);
          foreach($_REQUEST as $index=>$field){
              $list[$index] = trim($field);
            }

            $list["uploadFile"] = str_replace("C:\\fakepath\\", "", $list["uploadFile"]);
            $list['uploadTime'] = date("n/j/Y g:i A");

            $_SESSION['attachments']['internal'][] = $list;
            $id = max(array_keys($_SESSION['attachments']['internal']));
            $entry = $_SESSION['attachments']['internal'][$id];
            include "inc/attachments.internal.entry.php";

       break;
      case "editAttachmentInternalEntry":

              $id = $_REQUEST['id'];
                $entry = $_SESSION['attachments']['internal'][$id];
               // print_r($entry);
                $actionLabel = "Update entry";
                $action = "updateAttachmentInternalEntry";
              include('themes/kc/inc/header.php');
               include('inc/attachments.internal.form.php');
               include('themes/kc/inc/footer.php');

          break;
    case "previewAttachmentInternalEntry":
      $id = $_REQUEST['id'];
      $entry = $_SESSION['attachments']['internal'][$id];
     //  print_r($entry);
      include('inc/attachments.internal.preview.php');
    break;
    case "updateAttachmentInternalEntry":
        $id = $_REQUEST['id'];
         foreach($_REQUEST as $index=>$field){
            $entry[$index] = trim($field);
         }

         $entry["uploadFile"] = $_SESSION['attachments']['internal'][$id]["uploadFile"];
         $entry['uploadTime'] = $_SESSION['attachments']['internal'][$id]['uploadTime'];

        $_SESSION['attachments']['internal'][$id] = $entry;

        //$entry = $_SESSION['attachments']['personnel'][$id];
        include "inc/attachments.internal.entry.php";
        break;
    case "removeAttachmentsInternalEntry":

     if(isset($_REQUEST["id"])){
         $id = $_REQUEST["id"];
         if(isset($_SESSION['attachments']['internal'][$id])) unset($_SESSION['attachments']['internal'][$id]);
     }

    break;
    case "addAttachmentsAbstractsEntry":
                  //print_r($_REQUEST);
      foreach($_REQUEST as $index=>$field){
          $list[$index] = trim($field);
        }

        $list["uploadFile"] = str_replace("C:\\fakepath\\", "", $list["uploadFile"]);
        $list['uploadTime'] = date("n/j/Y g:i A");

        $_SESSION['attachments']['abstracts'][] = $list;
        $id = max(array_keys($_SESSION['attachments']['abstracts']));
        $entry = $_SESSION['attachments']['abstracts'][$id];
        include "inc/attachments.abstracts.entry.php";

   break;
  case "editAttachmentAbstractsEntry":

          $id = $_REQUEST['id'];
            $entry = $_SESSION['attachments']['abstracts'][$id];
           // print_r($entry);
            $actionLabel = "Update entry";
            $action = "updateAttachmentAbstractsEntry";
          include('themes/kc/inc/header.php');
           include('inc/attachments.abstracts.form.php');
           include('themes/kc/inc/footer.php');

      break;
    case "previewAttachmentAbstractsEntry":
      $id = $_REQUEST['id'];
      $entry = $_SESSION['attachments']['abstracts'][$id];
     //  print_r($entry);
      include('inc/attachments.abstracts.preview.php');
    break;
    case "updateAttachmentAbstractsEntry":
        $id = $_REQUEST['id'];
         foreach($_REQUEST as $index=>$field){
            $entry[$index] = trim($field);
         }

         $entry["uploadFile"] = $_SESSION['attachments']['abstracts'][$id]["uploadFile"];
         $entry['uploadTime'] = $_SESSION['attachments']['abstracts'][$id]['uploadTime'];

        $_SESSION['attachments']['abstracts'][$id] = $entry;

        //$entry = $_SESSION['attachments']['personnel'][$id];
        include "inc/attachments.abstracts.entry.php";
        break;
    case "removeAttachmentsAbstractsEntry":

     if(isset($_REQUEST["id"])){
         $id = $_REQUEST["id"];
         if(isset($_SESSION['attachments']['abstracts'][$id])) unset($_SESSION['attachments']['abstracts'][$id]);
     }

    break;
    default:


    break;

   }

?>