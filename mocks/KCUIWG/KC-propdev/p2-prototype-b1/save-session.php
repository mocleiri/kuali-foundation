<?php session_start();

      //session_destroy();


    switch($_REQUEST['action']){
     case "saveKeyPerson":

        $_SESSION['person'][$_SESSION['personnelId']]['personnel_role'] = $_REQUEST['personnel_role'];
        $_SESSION['person'][$_SESSION['personnelId']]['multiple_pis'] = $_REQUEST['multiple_pis'];
        $_SESSION['person'][$_SESSION['personnelId']]['keyperson_role'] = $_REQUEST['keyperson_role'];

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



    echo "<pre>";
    print_r($_SESSION);
    echo "</pre>";
?>