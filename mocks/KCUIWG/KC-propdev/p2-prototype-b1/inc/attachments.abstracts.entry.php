  <div class="panel panel-default" id="attachmentsAbstractsEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <a class="accordion-toggle icon icon-caret-right" style="display: block; float: left; text-align: center; margin: -10px 0 -10px 0; padding: 25px 10px; border-right: 1px solid #ccc; background: #f9f9f9;" data-toggle="collapse" data-parent="#accordion" href="#attachmentsAbstractsCollapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
        <div class="col checkbox" style="float: left; display: inline; padding: 10px; margin: 0 0 0 0;">
          <input type="checkbox" style="margin-left: 0; float: none;" id="attachment-abstracts-id-<?php echo $id ?>" name="attachment-abstracts-id-<?php echo $id ?>" />
        </div>
        <div class="col panel-title-container" style="float: left; display: inline; padding: 10px;">
          <label for="attachment-abstracts-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
        </div>
        <div class="col panel-file-preview" style="float: left; display: inline; padding: 10px;">
          <?php echo $entry['uploadFile']?>
        </div>
        <div class="col panel-status-preview" style="float: left; display: inline; padding: 10px;">
          <?php echo $entry['approval_status']?>
        </div>
        <div class="col panel-remove pull-right" style="padding: 10px 10px 10px 0;">
          <a href="#"><span aria-hidden="true" section="abstracts" class="icon-remove remove-attachments-abstracts-entry" entryId="<?php echo $id?>"></span></a>
        </div>
      </div>
    </div>
    <div id="attachmentsAbstractsCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsAbstractsInfo<?php echo $id?>">
          <?php include "attachments.abstracts.preview.php";?>
      </div>
  </div>
</div>