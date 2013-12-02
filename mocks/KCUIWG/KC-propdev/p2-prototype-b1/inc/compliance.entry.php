<!-- <div class="panel panel-default" id="complianceEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3">
          <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a> </h4>
        </div>
        <div class="col-md-2"> <?php echo $entry['approval_status']?> </div>
        <div class="col-md-3"><?php echo $entry['protocol_no']?></div>
        <div class="col-md-3"> Exp: <?php echo $entry['expiration_date']?> </div>
        <div class="col-md-1"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove remove-compliance-entry" entryId="<?php echo $id?>"></span></a> </div>
      </div>
    </div>
    <div id="collapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body">

            <?php include "compliance.entry.preview.php";?>

        </div>
    </div>
  </div> -->




<div class="panel panel-default" id="complianceEntry<?php echo $id?>">
  <div class="panel-heading">
    <div class="row">
      <a class="accordion-toggle icon icon-caret-right" style="display: block; float: left; text-align: center; margin: -10px 0 -10px 0; padding: 25px 10px; border-right: 1px solid #ccc; background: #f9f9f9;" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
      <!-- <div class="col checkbox" style="float: left; display: inline; padding: 10px; margin: 0 0 0 0;">
        <input type="checkbox" style="margin-left: 0; float: none;" id="attachment-compliance-id-<?php echo $id ?>" name="attachment-compliance-id-<?php echo $id ?>" />
      </div> -->
      <div class="col panel-title-container" style="float: left; display: inline; padding: 10px;">
        <label for="attachment-compliance-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
      </div>
      <div class="col panel-file-preview" style="float: left; display: inline; padding: 10px;">
        <?php echo $entry['approval_status']?>
      </div>
      <div class="col panel-status-preview" style="float: left; display: inline; padding: 10px;">
        <?php echo $entry['protocol_no']?>
      </div>
      <div class="col panel-remove pull-right" style="padding: 10px 10px 10px 0;">
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