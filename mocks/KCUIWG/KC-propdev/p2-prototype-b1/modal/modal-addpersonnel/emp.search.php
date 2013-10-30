<?php session_start();

 include "keypersonnel.data.php";

?>
<!DOCTYPE html>
<head>
<link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="../../themes/kc/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" href="../../themes/kc/css/jquery.multiselect.css" />
<link rel="stylesheet" href="../../themes/kc/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" href="../../themes/kc/icons/style.css" />
<link rel="stylesheet" href="../../themes/kc/css/custom.css" />

<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
</head>
<body>
<div class="modal-dialog">
  <div class="modal-content">
    
    <div class="modal-header">
      <h3>Add Personnel</h3>
    </div>

    <div class="modal-body">
      <fieldset>
        <legend class="off-screen">Find persons</legend>

        <fieldset>
	  		<legend>Search for:</legend>
	  		<div class="form-group clearfix">
		  		<label class="radio" for="keyPersonnelType_Employee"><input type="radio" name="keyPersonnelType" id="keyPersonnelType_Employee" value="Employee" checked="checked"> Employees</label>
		  		<label class="radio" for="keyPersonnelType_NonEmployee"><input type="radio" name="keyPersonnelType" id="keyPersonnelType_NonEmployee" value="Non-Employee">Non-Employees</label>
		  	</div>
	  	</fieldset>

	  	<fieldset>
	  		<legend>Enter any known details</legend>
	        <div class="form-group clearfix">
	          <label for="last_name" class="control-label col-md-3">Last name</label>
	          <div class="col-md-5">
	            <input type="text" id="last_name" name="last_name" class="form-control input-sm col-md-8">
	          </div>
	        </div>

	        <div class="form-group clearfix">
	          <label for="first_name" class="control-label col-md-3">First name</label>
	          <div class="col-md-5">
	            <input type="text" id="first_name" name="first_name" class="form-control input-sm col-md-8">
	          </div>
	        </div>

	        <div class="form-group clearfix">
	          <label for="username" class="control-label col-md-3">Username</label>
	          <div class="col-md-5">
	            <input type="text" id="username" name="username" class="form-control input-sm col-md-8">
	          </div>
	        </div>

	        <div class="form-group clearfix">
	          <label for="email_address" class="control-label col-md-3">Email address</label>
	          <div class="col-md-5">
	            <input type="text" id="email_address" name="email_address" class="form-control input-sm col-md-8" placeholder="user@domain.com">
	          </div>
	        </div>

	        <div class="form-group clearfix">
	          <label for="office_phone" class="control-label col-md-3">Office phone</label>
	          <div class="col-md-5">
	            <input type="tel" id="office_phone" name="office_phone" class="form-control input-sm col-md-8">
	          </div>
	        </div>
	    </fieldset>

      </fieldset>
    </div>
  
    <div class="modal-footer" data-spy="">
      <!-- <button href="emp.search.html" class="btn btn-default">Go back</button> -->
      <button href="emp.results.php" class="btn btn-primary pull-right">Continue...</button>
    </div>
  </div>
</div>

<!-- <script src="../../themes/kc/js/fancybox/jquery.fancybox.js"></script> -->
<script src="../../themes/kc/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.filter.min.js"></script>
<script src="../../themes/kc/js/functions.js"></script>
</body>
</html>