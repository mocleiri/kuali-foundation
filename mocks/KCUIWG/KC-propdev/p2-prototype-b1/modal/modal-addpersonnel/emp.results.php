<?php

session_start();
include ("keypersonnel.data.php");

include "../inc/header.modal.php";
?>

<div class="modal-dialog">
	<div class="modal-content">

		<!-- <form action="../../session-control-save.php" method="post"> -->

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
                              if(!isset($_SESSION['keyPersonnel'][$id])) {
                               ?>

                           <tr>
                                <td><input type="radio" value="<?php echo $id?>" name="personnelId" id="personnelId_<?php echo $id ?>" /></td>
                                <td><label for="personnelId_<?php echo $id ?>"><?php echo $person['name']?></label></td>
                                <td><?php echo $person['user']?></td>
                            </tr>



                           <?php  }
                          }

				    ?>
				</tbody>
			</table>
		</div>

		<div class="modal-footer">
			<input type="hidden" name="prev" value="modal/modal-addpersonnel/emp.search.php" />
			<input type="hidden" name="next" value="modal/modal-addpersonnel/emp.role.php" />
			<!-- <input type="submit" href="emp.search.php" class="btn btn-default" value="Back" />
			<input type="submit" id="choose-personnel" href="emp.role.php" class="btn btn-primary" value="Next" /> -->
			<button href="emp.search.php" class="btn btn-default">Back</button>
			<button id="choose-personnel" href="emp.role.php" class="btn btn-primary">Next</button>
		</div>

		<!-- </form> -->
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

        //var radio =  $('input[name="personnelId"]:checked');
        var id =   $('input[name="personnelId"]:checked').val();
        var data = { 'personnelId':  id, 'action': 'storeSessions' };

        $('#personnel-id',top.document).val(id);
        //console.log(id);
        $.post('../../process.php', data, function(t){
            //console.log(t);
            window.location = $('#choose-personnel').attr('href');
        });

        return false;
    });

});

//]]>
</script>
<?php include "../inc/footer.modal.php";       ?>