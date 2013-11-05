<?php session_start();

    include "../inc/header.modal.php";
?>
<script type='text/javascript'>
//<![CDATA[

$(document).ready(function(){

 /*$( "#compliance-form select" ).on('change', function() {
         console.log($(this).text());
    });*/


    $('#add-attachments-personnel-entry').click(function(e){


        var data = $('#add-attachments-personnel-form').serialize();

        console.log(data);
         console.log($('#file').val());
         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;

        $.post('../../save-session.php', data, function(t){
            console.log(t);

            parent.$.fancybox.close();


            $.post('../../process.php', {'action' : 'appendAttachmentPersonnelEntry'}, function(t){
                parent.$('.attachments-personnel-entries').append(t);
               // console.log(t);
            });
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
       $entry['type'] = 0;
       $entry['approval_status'] = 0;
       $entry['protocol_no'] = '';
       $entry['application_date'] = '';
       $entry['approval_date'] = '';
       $entry['expiration_date'] = '';
        $actionLabel = "Add Entry";
        $action = "addAttachmentsPersonnelEntry";
        include "attachments.personnel.form.php";?>

            </div>
    </div>
</div>
<?php include('../inc/footer.modal.php'); ?>