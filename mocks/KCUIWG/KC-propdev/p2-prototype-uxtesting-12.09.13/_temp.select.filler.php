<?php

/*
	TEMPORARY
	Array of random 'words' for use in prop select boxes
	This can be removed later
	Chris Rodriguez
*/
function generate_random_select_options($len = 10) {

	$word = array_merge(range('a', 'z'), range('A', 'Z'));
	shuffle($word);
	return substr(implode($word), 0, $len);
}

function get_options() {

	for ($i = 0; $i < 200; $i++) {
		echo '<option>' . generate_random_select_options() . '</option>';
	}

}

?>