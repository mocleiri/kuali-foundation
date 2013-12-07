<form method="post" class="form-horizontal" id="add-attachments-proposal-form" enctype="multipart/form-data">
  <input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
  <input type="hidden" id="section" name="section" value="proposal"/>
  <div class="row">
    <div class="col-md-6">
      <div class="form-group clearfix">
        <label for="type" class="control-label col-sm-3 col-xs-3">Type:</label>
        <div class="col-sm-9 col-xs-9">
          <select name="type" id="type" class="form-control input-sm col-md-8">
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
          <select name="approval_status" id="approval_status" class="form-control input-sm col-md-8">
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
    </div>
    <div class="col-md-6">
      <div class="form-group clearfix">
        <label for="file" class="control-label col-sm-3 col-xs-3">File:</label>
        <div class="col-sm-9 col-xs-9 input-group">
          <input type="file" id="file" name="file">
        </div>
      </div>
      <div class="form-group clearfix">
        <label for="description" class="control-label col-sm-3 col-xs-3">Description:</label>
        <div class="col-sm-9 col-xs-9 input-group">
          <textarea class="form-control" rows="3" id="description" name="description"></textarea>
        </div>
      </div>
      <div class="form-group clearfix">
        <label for="comments" class="control-label col-sm-3 col-xs-3">Comments:</label>
        <div class="col-sm-9 col-xs-9 input-group">
          <textarea class="form-control" rows="3" id="comments" name="comments"></textarea>
        </div>
      </div>
    </div>
  </div></div>
    <div class="modal-footer">
      <button class="btn btn-primary" id="add-attachments-proposal-entry"><?php echo $actionLabel?></button>
      <button class="btn btn-link" id=""> Cancel</button>
    </div>
  </div>
</div>
</form>