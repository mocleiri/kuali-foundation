<?php session_start();

    include "../inc/header.modal.php";
?>
<script type='text/javascript'>


$(document).ready(function(){

    $('#add-entry').click(function(e){


        var data = $('#add-attachments-notes-form').serialize();

         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;


            $.post('../../process.php', data, function(t){
                parent.$('.attachments-notes-entries').append(t);
              console.log(t);
               parent.$.fancybox.close();
            });

        return false;
    });
    $('#cancel-add-attachments-notes-entry').click(function(e){
       parent.$.fancybox.close();
    });

});
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Add Note</h3>
		</div>

            <div class="modal-body">

<?php
       $id = 0;

        $actionLabel = "Add Entry";
        $action = "addAttachmentsEntry";
        include "attachments.notes.form.php";?>
    </div>
</div>
<?php include('../inc/footer.modal.php'); ?>