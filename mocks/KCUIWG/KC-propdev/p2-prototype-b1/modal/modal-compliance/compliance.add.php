<?php session_start();

    include "../inc/header.modal.php";
?>


<script type='text/javascript'>
//<![CDATA[

$(document).ready(function(){

 /*$( "#compliance-form select" ).on('change', function() {
         console.log($(this).text());
    });*/


    $('#update-compliance-entry0').click(function(e){

        var data = $('#compliance-form0').serialize();

        console.log(data);

        $.post('../../save-session.php', data, function(t){
            console.log(t);

            parent.$.fancybox.close();


            $.post('../../process.php', {'action' : 'appendNewComplianceEntry'}, function(t){
                parent.$('.compliance-entries').append(t);
                console.log(t);
            });
        });



        return false;
    });
    $('#cancel-update-compliance-entry0').click(function(e){
       parent.$.fancybox.close();
    });

});
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Add New Protocol</h3>
		</div>

            <div class="modal-body">

<?php
       $id = 0;
       $entry['type'] = 0;
       $entry['approval_status'] = 0;
       $entry['protocol_no'] = '';
       $entry['application_date'] = '';
       $entry['approval_date'] = '';
       $entry['expiration_date'] = '';
       $actionLabel = "Add Entry";
       $action = "addComplianceEntry";
       include "compliance.form.php";?>

            </div>
    </div>
</div>



<?php include('../inc/footer.modal.php'); ?>
