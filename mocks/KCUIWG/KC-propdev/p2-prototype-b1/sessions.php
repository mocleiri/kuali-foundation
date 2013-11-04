<?php session_start();


/*unset($_SESSION['person']);

unset($_SESSION['field']);
unset($_SESSION['compliance']);*/
unset($_SESSION['person']);
if(isset($_GET['unset']) && $_GET['unset'])  unset($_SESSION[$_GET['unset']]);
echo "<pre>";
print_r($_SESSION);
//var_dump($_SESSION);
echo "</pre>";




