<?php session_start();

    include "../inc/header.modal.php";
?>
<script type='text/javascript'>
//<![CDATA[

$(document).ready(function(){

 /*$( "#compliance-form select" ).on('change', function() {
         console.log($(this).text());
    });*/


    $('#add-attachments-proposal-entry').click(function(e){

        var data = $('#add-attachments-proposal-form').serialize();

         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;

            $.post('../../process.php', data, function(t){
                parent.$('.attachments-proposal-entries').append(t);
               // console.log(t);
               parent.$.fancybox.close();
            });

        return false;
    });
    $('#cancel-update-attachments-proposal-entry0').click(function(e){
       parent.$.fancybox.close();
    });

});
</script>
<div class="modal-dialog">
	<div class="modal-content">
		<div class="modal-header">
			<h3>Add Proposal Attachment</h3>
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
        $action = "addAttachmentsProposalEntry";
        include "attachments.proposal.form.php";?>

            </div>
    </div>
</div>
<?php include('../inc/footer.modal.php'); ?>