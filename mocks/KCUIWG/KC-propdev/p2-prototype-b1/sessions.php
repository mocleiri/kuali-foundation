<?php session_start();


unset($_SESSION['person']);

unset($_SESSION['field']);
echo "<pre>";
print_r($_SESSION);
//var_dump($_SESSION);
echo "</pre>";




