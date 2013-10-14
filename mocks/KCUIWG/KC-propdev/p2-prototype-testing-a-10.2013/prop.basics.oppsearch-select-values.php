<?php
 /* http://www.example.com/json.php */
 switch($_REQUEST['id']){
	 case "opp-subtype":
		 $array['Application'] =  'Application'; 
		 $array['Preapplication'] =  'Preapplication'; 
		 $array['Change/Corrected'] =  'Change/Corrected';
 	break;
	case "opp-revisiontype":
		$array['Decrease Award'] =  'Decrease Award'; 
		$array['Decrease Award & Decrease Duration'] =  'Decrease Award & Decrease Duration'; 
		$array['Decrease Award & Increase Duration'] =  'Decrease Award & Increase Duration';
		$array['Decrease Duration'] =  'Decrease Duration';
		$array['Increase Award'] =  'Increase Award';
		$array['Increase Award & Decrease Duration'] =  'Increase Award & Decrease Duration';
		$array['Increase Duration'] =  'Increase Duration';
		 
	break;
	case "opp-status":
		$array['VALIDATED'] =  'VALIDATED'; 
		 $array['NOT VALIDATED'] =  'NOT VALIDATED'; 
	break;
	
 }

 print json_encode($array);
 ?>