<?php session_start();

    include "../inc/header.modal.php";
?>
<script type='text/javascript'>


$(document).ready(function(){

    $('#add-attachments-internal-entry').click(function(e){


        var data = $('#add-attachments-internal-form').serialize();

         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;


            $.post('../../process.php', data, function(t){
                parent.$('.attachments-internal-entries').append(t);
               // console.log(t);
               parent.$.fancybox.close();
            });

        return false;
    });

});
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Add Internal Attachment</h3>
		</div>

            <div class="modal-body">

<?php
       $id = 0;
        $actionLabel = "Add Entry";
        $action = "addAttachmentsInternalEntry";
        include "attachments.internal.form.php";?>

            </div>
    </div>
</div>
<?php include('../inc/footer.modal.php'); ?>