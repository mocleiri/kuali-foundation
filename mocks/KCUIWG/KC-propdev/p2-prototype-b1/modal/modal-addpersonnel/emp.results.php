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
  			<h4>Search Results</h4>
  			<table class="table table-condensed">
				<thead>
					<tr>
						<th width="10"></th>
						<th>Name</th>
						<th>User ID</th>
					</tr>
				</thead>
				<tbody>
				    <?php
                          foreach($persons as $id=>$person) {
                               if(!isset($_SESSION['person'][$id])) {   ?>

                           <tr>
                                <td><input type="radio" value="<?php echo $id?>" name="personnelId" id="personnelId_<?php echo $id ?>" /></td>
                                <td><label for="personnelId_<?php echo $id ?>"><?php echo $person['name']?></label></td>
                                <td><?php echo $person['user']?></td>
                            </tr>



                           <?php    }
                          }

				    ?>
				</tbody>
			</table>
		</div>

		<div class="modal-footer">
			<button href="emp.search.php" class="btn btn-default">Go back</button>
			<button id="choose-personnel" href="emp.role.php" class="btn btn-primary">Next</button>
		</div>
	</div>
</div>

<!-- <script src="../../themes/kc/js/fancybox/jquery.fancybox.js"></script> -->
<script src="../../themes/kc/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.min.js"></script>
<script src="../../themes/kc/js/jquery.multiselect.filter.min.js"></script>
<script src="../../themes/kc/js/functions.js"></script>

<script type='text/javascript'>
//<![CDATA[

$(document).ready(function(){
    $('#choose-personnel').click(function(e){

        var radio =  $('input[name="personnelId"]:checked');
        var id =   $('input[name="personnelId"]:checked').val();
        var data = { 'personnelId':  id };

        $('#personnel-id',top.document).val(id);
        console.log(id);
        $.post('../../save-session.php', data, function(t){
            console.log(t);
            window.location = $('#choose-personnel').attr('href');
        });

        return false;
    });

});
//$(window).load(function(){
//	$('table.table.table-condensed tr td').click(
//
//	function(e) {
//		e.preventDefault(); // prevent the default action
//		e.stopPropagation; // stop the click from bubbling
//		$(this).closest('tbody').find('.selected').removeClass('selected');
//		$(this).parent('tr').addClass('selected');
//	});
//});
//]]>
</script>
</body>
</html>