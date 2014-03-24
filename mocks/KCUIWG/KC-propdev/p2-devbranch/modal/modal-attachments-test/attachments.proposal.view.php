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
              <p class="form-control-static">Manor we shall merit by chief wound no or would. Oh towards between subject passage sending mention or it. Sight happy do burst fruit to woody begin at. </p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="type" class="control-label col-sm-3 col-xs-3">Type:</label>
            <div class="col-sm-9 col-xs-9">
              <p class="form-control-static">Narrative</p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="approval_status" class="control-label col-sm-3 col-xs-3">Status:</label>
            <div class="col-sm-9 col-xs-9">
              <p class="form-control-static">Complete</p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="contact" class="control-label col-sm-3 col-xs-3">Contact:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <p class="form-control-static">Ward Cleaver</p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="email" class="control-label col-sm-3 col-xs-3">Email:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <p class="form-control-static">wcleaver@institution.edu</p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="phone" class="control-label col-sm-3 col-xs-3">Phone:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <p class="form-control-static">812-123-1234</p>
            </div>
          </div>
          <div class="form-group clearfix">
            <label for="comments" class="control-label col-sm-3 col-xs-3">Comments:</label>
            <div class="col-sm-9 col-xs-9 input-group">
              <p class="form-control-static">Repulsive questions contented him few extensive supported. Of remarkably thoroughly he appearance in. Supposing tolerably applauded or of be. Suffering unfeeling so objection agreeable allowance me of. </p>
            </div>
          </div>
        </fieldset>
      </form>
    </div>
  </div>
  <div class="modal-footer"> <a href="attachments.proposal.edit.php" class="btn btn-primary">Edit</a> <a href="attachments.proposal.edit.php" class="btn btn-link fancy-close">Cancel</a></div>
</div>
<?php include('../inc/footer.modal.php'); ?>
