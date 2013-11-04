<?php session_start();

    /*
     *	Saves request variables to $SESSION array to use throughout the application.
     * 	-- Tadas Paegle
    */


    switch($_REQUEST['action']){
     case "saveKeyPerson":

        $_SESSION['person'][$_SESSION['personnelId']]['personnel_role'] = $_REQUEST['personnel_role'];
        $_SESSION['person'][$_SESSION['personnelId']]['multiple_pis'] = $_REQUEST['multiple_pis'];
        $_SESSION['person'][$_SESSION['personnelId']]['keyperson_role'] = $_REQUEST['keyperson_role'];

     break;
     case "addComplianceEntry":
         foreach($_REQUEST as $index=>$field){
            $list[$index] = trim($field);
         }
         $_SESSION['compliance'][] = $list;

     break;
     case "updateComplianceEntry":
         foreach($_REQUEST as $index=>$field){
             $list[$index] = trim($field);
          }
          $_SESSION['compliance'][$_REQUEST['id']] = $list;
     break;
     case "removePersonnelSession":

        removeSessionModuleEntry('person');

     break;
     case "removeComplianceEntry":

        removeSessionModuleEntry('compliance');

     break;
     case "addAttachmentsProposalEntry":

         foreach($_REQUEST as $index=>$field){
            $list[$index] = trim($field);
          }

          $list["uploadFile"] = str_replace("C:\\fakepath\\", "", $list["uploadFile"]);
          $list['uploadTime'] = date("n/j/Y g:i A");

          $_SESSION['attachments']['proposal'][] = $list;

     break;
     case "removeAttachmentsProposalEntry":

        if(isset($_REQUEST["id"])){

            $id = $_REQUEST["id"];
            if(isset($_SESSION['attachments']['proposal'][$id])) unset($_SESSION['attachments']['proposal'][$id]);
        }

     break;
     default:
          foreach($_REQUEST as $index=>$field){
              if(is_array($field)){
                  foreach($field as $key=>$val){

                    if(is_array($val)){
                      foreach($val as $key2=>$val2){
                          $_SESSION[$index][$key][$key2] = $val2;
                      }
                    }else{
                      $_SESSION[$index][$key] = $val;
                    }
                  }
              }else{
                  $_SESSION[$index] = trim($field);
              }
          }


     break;

    }

     function removeSessionModuleEntry($module){
        if(isset($_REQUEST["id"])){
            $id = $_REQUEST["id"];
         if(isset($_SESSION[$module][$id])) unset($_SESSION[$module][$id]);
        }
     }

    echo "<pre>";
    print_r($_SESSION);
    echo "</pre>";
?>