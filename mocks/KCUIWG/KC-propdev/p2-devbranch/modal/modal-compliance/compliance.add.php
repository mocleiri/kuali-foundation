<?php
session_start();
include "../inc/header.modal.php";
?>
<script type='text/javascript'>
$(document).ready(function(){
    $('#update-compliance-entry0').click(function(e){
        var data = $('#compliance-form0').serialize();
        $.post('../../process.php', data, function(t){
            parent.$('.compliance-entries').append(t);
            parent.$.fancybox.close();
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
      include "compliance.form.php";
      ?>
    </div>
  </div>
</div>
<?php include('../inc/footer.modal.php'); ?>