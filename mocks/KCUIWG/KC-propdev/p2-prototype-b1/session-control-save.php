<?php

/*
 *	Saves all $POST variables into an array that we can use at anytime during the $SESSION.
 *	Any time we progress to another page, variables are added, edited, or removed from this array.
 *	So in the end, we'll have a collection of everything that was entered.
 * 	-- Chris Rodriguez
*/

// Start the session
session_start();

// Collect some variables
$redirect_url = $_POST['next'];
$enter_array = $_SESSION['kc_prop_user_values_array'];
$sesh = array();

// Loop through each $POSTed variable (that's not empty) and add it to the array
foreach ($_POST as $value) {

	if (isset($value)) {
		array_push($sesh, $value);
	}

}

// Update the session with original array plus new array, merged
$_SESSION['kc_prop_user_values_array'] = array_merge($enter_array, $sesh);

// Redirect to next page
header ('location: ' . $redirect_url);