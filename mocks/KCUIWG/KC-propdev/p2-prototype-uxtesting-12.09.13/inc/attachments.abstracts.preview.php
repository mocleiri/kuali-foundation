<form method="post" class="form-horizontal">
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Added by:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime']?>)</span></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
    <div class="col-md-9">
      <p class="form-control-static"><?php echo $entry['type']?></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Abstract Details:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"><?php echo $entry['description']?></p>
    </div>
  </div>


  <div class="btn-row-widget-action pull-right">
    <button class="btn btn-primary btn-xs" entryId="<?php echo $id?>" id="edit_attachments_abstracts_entry<?php echo $id?>"> Edit</button>
        <button class="btn btn-link btn-xs" id="cancel_attachments_abstracts_preview<?php echo $id?>"> Cancel</button>
  </div>


</form>

<script>

  $(document).ready(function(){


   $('#edit_attachments_abstracts_entry<?php echo $id?>').click(function(){

         $.post('process.php', {"section": "abstracts", "action": "editAttachmentsEntry", "id" : $(this).attr('entryId') }, function(t){
            $("#attachmentAbstractsInfo<?php echo $id?>").html(t);
         });

         return false;
   });

    $('#cancel_attachments_abstracts_preview<?php echo $id?>').click(function(){

        $("#attachmentsAbstractsCollapse<?php echo $id?>").collapse('hide');
        return false;
    });




  });
</script>