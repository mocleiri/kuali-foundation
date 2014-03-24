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
    <h3>Proposal Attachment</h3>
  </div>
  <div class="modal-body">
    <div class="container">
      <form class="form-horizontal" method="get" action="">
        <fieldset>
          <legend style="display:none">Enter lookup criteria</legend>
          <div class="form-group clearfix">
            <label for="description" class="control-label col-sm-3 col-xs-3">Description:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <textarea class="form-control" rows="3" id="description" name="description"></textarea>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="type" class="control-label col-sm-3 col-xs-3">Type:</label>
            <div class="col-sm-9 col-xs-9">
              <select name="type" id="type" class="form-control input-sm col-md-8" style="display: none;">
                <option> </option>
                <option value="Research Plan">Research Plan</option>
                <option value="Narrative">Narrative</option>
                <option value="Equipment">Equipment</option>
                <option value="Bibliography">Bibliography</option>
                <option value="Project Summary">Project Summary</option>
                <option value="Budget Justification">Budget Justification</option>
                <option value="Additional Keypersons">Additional Keypersons</option>
                <option value="Additional Equipment">Additional Equipment</option>
                <option value="Personal Data">Personal Data</option>
                <option value="Facilities">Facilities</option>
                <option value="Subaward Budget">Subaward Budget</option>
                <option value="Table of Contents">Table of Contents</option>
                <option value="Supplementary Documentation">Supplementary Documentation</option>
                <option value="Other">Other</option>
              </select>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="approval_status" class="control-label col-sm-3 col-xs-3">Status:</label>
            <div class="col-sm-9 col-xs-9">
              <select name="approval_status" id="approval_status" class="form-control input-sm col-md-8" style="display: none;">
                <option> </option>
                <option value="Complete">Complete</option>
                <option value="Incomplete">Incomplete</option>
              </select>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="contact" class="control-label col-sm-3 col-xs-3">Contact:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <input type="text" name="contact" id="contact" class="form-control input-sm col-md-8 " placeholder="" value="">
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="email" class="control-label col-sm-3 col-xs-3">Email:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <input type="text" name="email" id="email" class="form-control input-sm col-md-8 " placeholder="" value="">
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="phone" class="control-label col-sm-3 col-xs-3">Phone:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <input type="text" name="phone" id="phone" class="form-control input-sm col-md-8 " placeholder="" value="">
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="comments" class="control-label col-sm-3 col-xs-3">Comments:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <textarea class="form-control" rows="3" id="comments" name="comments"></textarea>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
  </div>
  <div class="modal-footer"> <a href="attachments.proposal.view.php" class="btn btn-primary">Save</a> <a href="#" class="btn btn-link fancy-close">Cancel</a></div>
</div>
<?php include('../inc/footer.modal.php'); ?>
