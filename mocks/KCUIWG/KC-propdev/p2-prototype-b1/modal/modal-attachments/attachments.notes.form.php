<form method="post" class="form-horizontal" enctype="multipart/form-data" id="add-attachments-notes-form" >
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
<input type="hidden" id="section" name="section" value="notes"/>
<input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
  <div class="form-group clearfix">
      <label for="topic" class="control-label col-md-3">Note Topic:</label>
      <div class="col-md-9 input-group">
        <input type="text" name="topic" id="topic" class="form-control input-sm col-md-8 " placeholder="" value="<?php echo $entry['topic']?>">
      </div>
    </div>
  <div class="form-group clearfix">
    <label for="description" class="control-label col-md-3">Note Text:</label>
    <div class="col-md-9 input-group">
      <textarea class="form-control" rows="5" name="description" id="description"><?php echo $entry['description']?></textarea>
    </div>
  </div>


  <div class="btn-row-widget-action pull-right">
   <button class="btn btn-primary btn-xs add-attachments-notes-entry" entryId="<?php echo $id?>" id="add-entry"> <?php echo $actionLabel?></button>
   <button class="btn btn-link btn-xs cancel-add-attachments-notes-entry" entryId="<?php echo $id?>" id="cancel-add-attachments-notes-entry"> Cancel</button>
  </div>


</form>