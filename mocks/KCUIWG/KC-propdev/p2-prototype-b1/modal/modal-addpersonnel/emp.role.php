<?php  session_start();

include "keypersonnel.data.php";

$personName = $persons[$_SESSION['personnelId']]['name'];

$disabled = 'disabled="disabled"';
$pi_disabled = "";
echo $pi_disabled  = array_search('pi', $_SESSION['keyPersonnel']);
foreach($_SESSION['keyPersonnel'] as $person){
  if(array_search('pi', $person)) $pi_disabled = $disabled;
}

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
				<legend>Assign a role</legend>
				<label class="radio" for="pi-contact"><input type="radio" id="pi-contact" value="pi" name="group" class="r" <?php echo $pi_disabled?>> Principal Investigator/Contact</label>
				<label class="radio" for="copi-investigator"><input type="radio" id="copi-investigator" value="copi" name="group" class="r"> Co-Principal Investigator</label>
				<div class="other" id="multi_pi">
					<div class="form-group clearfix">
                    <label for="multiple_pis" class="control-label col-md-5"><input type="checkbox" id="multiple_pis" value="1" /> Yes, this proposal has multuple PI's.</label>
                </div>
				</div>

				<label class="radio" for="keyperson"><input type="radio" id="keyperson" value="kp" name="group" class="r"> Key Person</label>
				<div class="other" id="kp_role">
					<div class="form-group clearfix">
						<label for="keyperson_role" class="control-label col-md-3">Key Person's role will be:</label>
						<div class="col-md-5">
							<input type="text" id="keyperson_role" class="form-control input-sm col-md-8" />
						</div>
					</div>
				</div>
			</fieldset>

			<div class="form-group clearfix">
				<label for="include-ib" class="control-label col-md-5"><input type="checkbox" id="include-ib" value="1" checked="checked" /> Yes, include <span><?php echo $personName?></span> in this proposal's budget</label>
			</div>
		</div>

		<div class="modal-footer" data-spy="">
			<button href="emp.results.php" class="btn btn-default">Go back</button>
			<button href="../../prop.keypersonnel.php" class="btn btn-primary fancybox-close" id="add-person">Add Person</button>
		</div>
	</div>
</div>

<!-- <script src="../../themes/kc/js/fancybox/jquery.fancybox.js"></script> -->
<script src="../../themes/kc/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.filter.min.js"></script>
<script src="../../themes/kc/js/functions.js"></script>
<script>
    $(document).ready(function() {
        $('.other').hide();

        $('.r').click(function() {

            if ($(this).val() == 'copi') {
                $('.other').hide();
                $('#multi_pi').fadeIn();
            } else if ($(this).val() == 'kp') {
                $('.other').hide();
                $('#kp_role').fadeIn();
            } else {
                $('.other').hide();
            }
        });

        $('#add-person').live("click", function(e){

             e.preventDefault();

            var role =   $('input[name="group"]:checked').val();
            var multiple_pis = 0;
            var keyperson_role = '';
            var id = $('#personnel-id',top.document).val();

            if(role == "copi") { if($("#multiple_pis").is(":checked")) multiple_pis = 1; }
            if(role == "kp"){   keyperson_role = $("#keyperson_role").val();      }

            var data = {
                'action':'addKeyPersonnel',
                'personnel_role': role,
                'multiple_pis':multiple_pis,
                'keyperson_role':keyperson_role
            };


            $.post('../../process.php', data, function(t){
                //console.log(t);
                $('#personnel-role',top.document).val(role);

                    $('.personnel-entries',top.document).append(t);
                    $("#role" + id).val(role);
                    $('#keypersonnel-message-name',top.document).html('<?php echo $personName?>');
                    $('.alert',top.document).show();
                    parent.$.fancybox.close();
            });


            return false;
        });

    });
</script>
</body>
</html>