

                <form method="post" class="form-horizontal">
                  <div class="form-group clearfix">
                    <label for="type" class="control-label col-md-3">Type:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['type']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_status" class="control-label col-md-3">Approval status:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['approval_status']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="protocol_no" class="control-label col-md-3">Protocol number:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['protocol_no']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="application_date" class="control-label col-md-3">Application Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['application_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_date" class="control-label col-md-3">Approval Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['approval_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="expiration_date" class="control-label col-md-3">Expiration Date:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['expiration_date']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Exemption:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['exemptions']?></p>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="comments" class="control-label col-md-3">Comments:</label>
                    <div class="col-md-9">
                      <p class="form-control-static"><?php echo $entry['comments']?></p>
                    </div>
                  </div>
                  <div class="btn-row-widget-action pull-right">
                    <button class="btn btn-primary btn-xs edit-entry" complianceEntryId="<?php echo $id?>" id="edit_entry<?php echo $id?>"> Edit</button>
                    <button class="btn btn-link btn-xs" complianceEntryId="<?php echo $id?>" id="cancel_preview<?php echo $id?>"> Cancel</button>
                  </div>
                </form>
  <script>
  $(document).ready(function(){


   $('#edit_entry<?php echo $id?>').bind('click', function(){
         //alert('test');
         var container = $(this).parent('div').parent('form').parent('div');
         $(container).load('process.php', {"action": "editComplianceEntry", "id" : $(this).attr('complianceEntryId') });
//         $.post('process.php', {"action": "editComplianceEntry", "id" : $(this).attr('complianceEntryId') }, function(t){
//             $(container).empty().append(t);
//
//         });

         console.log(container);

         return false;
   });

    $('#cancel_preview<?php echo $id?>').click(function(){

        $("#collapse<?php echo $id?>").collapse('hide');
        return false;
    });




  });
  </script>