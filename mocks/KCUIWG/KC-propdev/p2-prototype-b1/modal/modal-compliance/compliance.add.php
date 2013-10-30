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


    $('#update-compliance-entry').click(function(e){

        $( "#compliance-form select").each(function(){
              $(this +  " option").each(function(i,v){
              if($(this).is(':selected')){
                                console.log('selected');
                              }

              });


               console.log('hi');
            });
            return false;

        var data = $('#compliance-form').serialize();

        console.log(data);

        $.post('../../save-session.php', data, function(t){
            console.log(t);
            //window.location = $('#choose-personnel').attr('href');
        });

        return false;
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

<?php include "compliance.form.php";?>

            </div>
    </div>
</div>
</body>
</html>