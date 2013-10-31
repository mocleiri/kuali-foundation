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


    $('#update-compliance-entry0').click(function(e){

        var data = $('#compliance-form0').serialize();

        console.log(data);
         data += '&action=addComplianceEntry';
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
</head>
<body>
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

        include "compliance.form.php";?>

            </div>
    </div>
</div>
</body>
</html>