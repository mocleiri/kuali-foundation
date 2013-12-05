<div class="panel panel-default" id="complianceEntry<?php echo $id?>">
  <div class="panel-heading">
    <div class="row">
      <a class="accordion-toggle icon icon-caret-right" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
      <!-- <div class="col checkbox" style="float: left; display: inline; padding: 10px; margin: 0 0 0 0;">
        <input type="checkbox" style="margin-left: 0; float: none;" id="attachment-compliance-id-<?php echo $id ?>" name="attachment-compliance-id-<?php echo $id ?>" />
      </div> -->
      <div class="col panel-title-container">
        <label for="attachment-compliance-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
      </div>
      <div class="col panel-file-preview">
        <?php echo $entry['approval_status']?>
      </div>
      <div class="col panel-status-preview">
        <?php echo $entry['protocol_no']?>
      </div>
      <div class="col panel-remove pull-right">
        <a href="#"><span aria-hidden="true" section="compliance" class="icon-remove remove-compliance-entry" entryId="<?php echo $id?>"></span></a>
      </div>
    </div>
  </div>
  <div id="collapse<?php echo $id?>" class="panel-collapse collapse">
    <div class="panel-body" id="complianceInfo<?php echo $id?>">
        <?php include "compliance.entry.preview.php";?>
    </div>
  </div>
</div>