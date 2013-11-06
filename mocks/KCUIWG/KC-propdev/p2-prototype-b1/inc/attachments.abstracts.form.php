 <script>

       $(document).ready(function(){
          $('#attachment-abstracts-form<?php echo $id?> #type').val("<?php echo $entry['type']?>");

       });

     </script>
<form method="post" class="form-horizontal" enctype="multipart/form-data" id="attachment-abstracts-form<?php echo $id?>" >
<input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
<input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
  <div class="form-group clearfix">
    <label for="" class="control-label col-md-3">Added by:</label>
    <div class="col-md-9 input-group">
      <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
    </div>
  </div>
  <div class="form-group clearfix">
    <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
    <div class="col-md-9">
      <select name="type" id="type" class="form-control input-sm col-md-8">
        <option value="">select</option>
        <option value="Project Summary" selected="selected">Project Summary</option>
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
    <label for="description" class="control-label col-md-3">Abstract Details:</label>
    <div class="col-md-9 input-group">
      <textarea class="form-control" rows="5" name="description" id="description">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
    </div>
  </div>


  <div class="btn-row-widget-action pull-right">
   <button class="btn btn-primary btn-xs update-attachment-abstracts-entry" entryId="<?php echo $id?>" id="update-attachment-abstracts-entry<?php echo $id?>"> <?php echo $actionLabel?></button>
   <button class="btn btn-link btn-xs cancel-update-attachments-abstracts-entry" entryId="<?php echo $id?>" id="cancel-update-attachments-abstracts-entry<?php echo $id?>"> Cancel</button>
  </div>


</form>