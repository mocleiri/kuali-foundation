<?php session_start();


/*unset($_SESSION['person']);

unset($_SESSION['field']);
unset($_SESSION['compliance']);
unset($_SESSION['person']);*/


$_SESSION['compliance'][0]['exemptions'] = "E2, E3, E5"  ;

echo "<pre>";
print_r($_SESSION);
//var_dump($_SESSION);
echo "</pre>";




