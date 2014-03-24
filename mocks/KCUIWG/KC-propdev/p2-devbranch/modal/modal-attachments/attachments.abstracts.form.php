<form method="post" class="form-horizontal" enctype="multipart/form-data" id="add-attachments-abstracts-form">
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
<input type="hidden" id="section" name="section" value="abstracts"/>
  <div class="form-group clearfix">
    <label for="approval_status" class="control-label col-sm-3 col-xs-3">Abstract Type:</label>
    <div class="col-sm-9 col-xs-9">
      <select name="type" id="type" class="form-control input-sm col-md-8">
        <option value="">select</option>
        <option value="Project Summary">Project Summary</option>
        <option value="Technical Abstract">Technical Abstract</option>
        <option value="Labs">Labs</option>
        <option value="Clinical">Clinical</option>
        <option value="Animal">Animal</option>
        <option value="Computer">Computer</option>
        <option value="Office">Office</option>
        <option value="Other Facilities">Other Facilities</option>
        <option value="Equipment">Equipment</option>
        <option value="Other Resources">Other Resources</option>
        <option value="Suggested Reviewers">Suggested Reviewers</option>
        <option value="Publications">Publications</option>
        <option value="Reviewers Not to Include">Reviewers Not to Include</option>
        <option value="Deviation Authorization">Deviation Authorization</option>
        <option value="Areas Affected">Areas Affected</option>
        <option value="Relevance">Relevance</option>
      </select>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="description" class="control-label col-sm-3 col-xs-3">Abstract Details:</label>
    <div class="col-sm-9 col-xs-9 input-group">
      <textarea class="form-control" rows="5" name="description" id="description"></textarea>
    </div>
  </div></div>


  <div class="modal-footer">
   <button class="btn btn-primary add-attachments-abstracts-entry" entryId="<?php echo $id?>" id="add-attachments-abstracts-entry"> <?php echo $actionLabel?></button>
   <button class="btn btn-link fancy-close"> Cancel</button>
  </div>


</form>