<?php session_start();


/*unset($_SESSION['person']);

unset($_SESSION['field']);
unset($_SESSION['compliance']);
unset($_SESSION['person']);*/


//$_SESSION['compliance'][0]['exemptions'] = "E2, E3, E5"  ;

///$_SESSION['attachments']['personnel'][0]['type'] = "Current Pending";
unset($_SESSION['attachments']['personnel'][]);
echo "<pre>";
print_r($_SESSION);
//var_dump($_SESSION);
echo "</pre>";




