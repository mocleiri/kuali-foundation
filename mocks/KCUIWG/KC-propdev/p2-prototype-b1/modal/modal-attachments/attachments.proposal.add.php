<!DOCTYPE html>
<head>
<link rel="stylesheet" href="../../themes/bootstrap/css/bootstrap.css" />
<link rel="stylesheet" href="../../themes/kc/css/smoothness/jquery-ui-1.9.2.custom.min.css" />
<link rel="stylesheet" href="../../themes/kc/css/jquery.multiselect.css" />
<link rel="stylesheet" href="../../themes/kc/css/jquery.multiselect.filter.css" />
<link rel="stylesheet" href="../../themes/kc/icons/style.css" />
<link rel="stylesheet" href="../../themes/kc/css/custom.css" />

<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script type='text/javascript'>
//<![CDATA[

$(document).ready(function(){

 /*$( "#compliance-form select" ).on('change', function() {
         console.log($(this).text());
    });*/


    $('#add-attachments-proposal-entry').click(function(e){

        var data = $('#add-attachments-proposal-form').serialize();

        console.log(data);
         console.log($('#file').val());
         var fileName = $('#file').val();

         data += "&uploadFile=" + fileName;

        $.post('../../save-session.php', data, function(t){
            console.log(t);

            parent.$.fancybox.close();


            $.post('../../process.php', {'action' : 'appendAttachmentProposalEntry'}, function(t){
                parent.$('.attachments-proposal-entries').append(t);
               // console.log(t);
            });
        });



        return false;
    });
    $('#cancel-update-compliance-entry0').click(function(e){
       parent.$.fancybox.close();
    });

});
</script>
</head>
<body>
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
</body>
</html>