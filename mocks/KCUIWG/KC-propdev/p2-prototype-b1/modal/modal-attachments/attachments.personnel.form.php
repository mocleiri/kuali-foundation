<form method="post" class="form-horizontal" id="add-attachments-personnel-form" enctype="multipart/form-data">
<input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
  <div class="form-group clearfix">
    <label for="person" class="control-label col-md-3">Person:</label>
    <div class="col-md-9">
      <select name="person" id="person" class="form-control input-sm col-md-8">
        <option> </option>
        <option value="Ken Graves">Ken Graves</option>
        <option value="Bill Evans">Bill Evans</option>
        <option value="Sarah Vaughn">Sarah Vaughn</option>
      </select>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="type" class="control-label col-md-3">Attachment Type:</label>
    <div class="col-md-9">
      <select name="type" id="type" class="form-control input-sm col-md-8">
        <option> </option>
        <option value="Biosketch">Biosketch</option>
        <option value="Budget Details">Budget Details</option>
        <option value="Current Pending">Current Pending</option>
        <option value="Other">Other</option>
        <option value="Statement of Commitment">Statement of Commitment</option>
      </select>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="file" class="control-label col-md-3">File:</label>
    <div class="col-md-9 input-group">
      <input type="file" id="file" name="file">
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="description" class="control-label col-md-3">Description:</label>
    <div class="col-md-9 input-group">
      <textarea class="form-control" rows="5" id="description" name="description"></textarea>
    </div>
  </div>
  <div class="btn-row-widget-action pull-right">
    <button class="btn btn-primary btn-xs" id="add-attachments-personnel-entry"><?php echo $actionLabel?></button>
    <button class="btn btn-link btn-xs fancy-close"> Cancel</button>
  </div>
</form>