<form method="post" class="form-horizontal" enctype="multipart/form-data" id="add-attachments-internal-form" >
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
<input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
<input type="hidden" id="section" name="section" value="internal"/>
  <div class="form-group clearfix">
    <label for="type" class="control-label col-sm-3 col-xs-3">Attachment Type:</label>
    <div class="col-sm-9 col-xs-9">
      <select name="type" id="type" class="form-control input-sm col-md-8">
        <option value="">select:</option>
        <option value="MIT Cost-Sharing Distribution">MIT Cost-Sharing Distribution</option>
        <option value="MIT F&amp;A Under-recovery Distribution">MIT F&amp;A Under-recovery Distribution</option>
        <option value="Other Institutional Attachment">Other Institutional Attachment</option>
        <option value="Institutional Narrative 1 - Change this description as Needed">Institutional Narrative 1 - Change this description as Needed</option>
        <option value="1st Institutional Attachment">1st Institutional Attachment</option>
        <option value="2nd Institutional Attachment">2nd Institutional Attachment</option>
      </select>
    </div>
  </div>
  <div class="form-group clearfix">
      <label for="file" class="control-label col-sm-3 col-xs-3">File:</label>
      <div class="col-sm-9 col-xs-9 input-group">
        <input type="file" id="file" name="file">
      </div>
    </div>
  <div class="form-group clearfix">
    <label for="" class="control-label col-sm-3 col-xs-3">Description:</label>
    <div class="col-sm-9 col-xs-9 input-group">
      <textarea class="form-control" rows="5" name="description" id="description"></textarea>
    </div>
  </div>
  <div class="btn-row-widget-action pull-right">
    <button class="btn btn-primary btn-xs add-attachments-internal-entry" entryId="<?php echo $id?>" id="add-attachments-internal-entry"> <?php echo $actionLabel?></button>
        <button class="btn btn-link btn-xs fancy-close"> Cancel</button>
  </div>
</form>