
                    <form method="post" class="form-horizontal">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Added by:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime'];?>)</span></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="type" class="control-label col-md-3">Type:</label>
                            <div class="col-md-9">
                              <p class="form-control-static"><?php echo $entry['type']?></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="approval_status" class="control-label col-md-3">Status:</label>
                            <div class="col-md-9">
                              <p class="form-control-static"><?php echo $entry['approval_status']?></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Contact:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"><?php echo $entry['contact']?></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"><?php echo $entry['email']?></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Phone:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"><?php echo $entry['phone']?></p>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">File:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> <span class="pull-left"><span aria-hidden="true" class="icon-file-2"></span> <a href="#"><?php echo $entry['uploadFile']?></a></span> </p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Description:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"><?php echo $entry['description']?></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"><?php echo $entry['comments']?></p>
                            </div>
                          </div>
                        </div>
                        <div class="btn-row-widget-action pull-right">
                          <button class="btn btn-primary btn-xs" entryId="<?php echo $id?>" id="edit_attachments_proposal_entry<?php echo $id?>"> Edit</button>
                          <button class="btn btn-link btn-xs" id="cancel_attachments_proposal_preview<?php echo $id?>"> Cancel</button>
                        </div>
                      </div>
                    </form>
<script>
  $(document).ready(function(){


   $('#edit_attachments_proposal_entry<?php echo $id?>').click(function(){

         var container = $(this).parents('div').eq(2);
         console.log($(container).html());
         $.post('process.php', {"action": "editAttachmentProposalEntry", "id" : $(this).attr('entryId') }, function(t){
            $(container).html(t);
            // console.log(t);

         });



         return false;
   });

    $('#cancel_attachments_proposal_preview<?php echo $id?>').click(function(){

        $("#collapse<?php echo $id?>").collapse('hide');
        return false;
    });




  });
  </script>