<form method="post" class="form-horizontal">
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Added by:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime']?>)</span></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="type" class="control-label col-md-3">Person:</label>
    <div class="col-md-9">
      <p class="form-control-static"><?php echo $entry['person']?></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
    <div class="col-md-9">
      <p class="form-control-static"><?php echo $entry['type']?></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">File:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> <?php echo $entry['uploadFile']?></a></span> </p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Description:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"><?php echo $entry['description']?></p>
    </div>
  </div>
  <div class="btn-row-widget-action pull-right">
    <button class="btn btn-primary btn-xs" id="edit_attachments_personnel_entry<?php echo $id?>" entryId="<?php echo $id?>"> Edit</button>
    <button class="btn btn-link btn-xs" id="cancel_attachments_personnel_preview<?php echo $id?>"> Cancel</button>
  </div>
</form>

<script>
  $(document).ready(function(){


   $('#edit_attachments_personnel_entry<?php echo $id?>').click(function(){

         $.post('process.php', {"section": "personnel", "action": "editAttachmentsEntry", "id" : $(this).attr('entryId') }, function(t){
            $("#attachmentsPersonnelInfo<?php echo $id?>").html(t);
         });
         return false;
   });

    $('#cancel_attachments_personnel_preview<?php echo $id?>').click(function(){
        $("#collapseAttachmentsPersonnel<?php echo $id?>").collapse('hide');
        return false;
    });




  });
  </script>