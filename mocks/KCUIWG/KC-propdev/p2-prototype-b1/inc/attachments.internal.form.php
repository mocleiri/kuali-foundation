 <script>

       $(document).ready(function(){
          $('#attachment-internal-form<?php echo $id?> #type').val("<?php echo $entry['type']?>");

       });

     </script>
<form method="post" class="form-horizontal" enctype="multipart/form-data" id="attachment-internal-form<?php echo $id?>" >
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
<input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Added by:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime']?>)</span></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="type" class="control-label col-md-3">Attachment Type:</label>
    <div class="col-md-9">
      <select name="type" id="type" class="form-control input-sm col-md-8">
        <option value="">select:</option>
        <option value="MIT Cost-Sharing Distribution" selected="selected">MIT Cost-Sharing Distribution</option>
        <option value="MIT F&amp;A Under-recovery Distribution">MIT F&amp;A Under-recovery Distribution</option>
        <option value="Other Institutional Attachment">Other Institutional Attachment</option>
        <option value="Institutional Narrative 1 - Change this description as Needed">Institutional Narrative 1 - Change this description as Needed</option>
        <option value="1st Institutional Attachment">1st Institutional Attachment</option>
        <option value="2nd Institutional Attachment">2nd Institutional Attachment</option>
      </select>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="file" class="control-label col-md-3">File:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> <?php echo $entry['uploadFile']?></a></span>
      <span class="">
      <div class="dropdown pull-right"><span class="caret"></span> <a data-toggle="dropdown" href="#">Actions</a>
        <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
          <li><a href="#">Replace File</a></li>
          <li><a href="#">Set Permissions</a></li>
        </ul>
      </div>
      </span>
      </p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="description" class="control-label col-md-3">Description:</label>
    <div class="col-md-9 input-group">
      <textarea class="form-control" rows="5" id="description" name="description"><?php echo $entry['description']?></textarea>
    </div>
  </div>
  <div class="btn-row-widget-action pull-right">
    <button class="btn btn-primary btn-xs update-attachment-internal-entry" entryId="<?php echo $id?>" id="update-attachment-internal-entry<?php echo $id?>"> <?php echo $actionLabel?></button>
        <button class="btn btn-link btn-xs cancel-update-attachments-internal-entry" entryId="<?php echo $id?>" id="cancel-update-attachments-internal-entry<?php echo $id?>"> Cancel</button>
  </div>
</form>