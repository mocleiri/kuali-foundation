<?php session_start();

    include "../inc/header.modal.php";
?>
<script type='text/javascript'>


$(document).ready(function(){

    $('#add-attachments-personnel-entry').click(function(e){


        var data = $('#add-attachments-personnel-form').serialize();

         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;


            $.post('../../process.php', data, function(t){
                parent.$('.attachments-personnel-entries').append(t);
               // console.log(t);
               parent.$.fancybox.close();
            });

        return false;
    });
    $('#cancel-update-attachments-personnel-entry0').click(function(e){
       parent.$.fancybox.close();
    });

});
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Add Personnel Attachment</h3>
		</div>

            <div class="modal-body">

<?php
       $id = 0;
//       $entry['type'] = 0;
//       $entry['approval_status'] = 0;
//       $entry['protocol_no'] = '';
//       $entry['application_date'] = '';
//       $entry['approval_date'] = '';
//       $entry['expiration_date'] = '';
        $actionLabel = "Add Entry";
        $action = "addAttachmentsEntry";
        include "attachments.personnel.form.php";?>

            </div>
    </div>
</div>
<?php include('../inc/footer.modal.php'); ?>