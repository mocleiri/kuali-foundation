  <div class="panel panel-default" id="attachmentsInternalEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <a class="accordion-toggle icon icon-caret-right" data-toggle="collapse" data-parent="#accordion" href="#attachmentsInternalCollapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
        <div class="col checkbox">
          <input type="checkbox" id="attachment-internal-id-<?php echo $id ?>" name="attachment-internal-id-<?php echo $id ?>" />
        </div>
        <div class="col panel-title-container">
          <label for="attachment-internal-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
        </div>
        <div class="col panel-file-preview">
          <?php echo $entry['uploadFile']?>
        </div>
        <div class="col panel-status-preview">
          <?php echo $entry['approval_status']?>
        </div>
        <div class="col panel-remove pull-right">
          <a href="#"><span aria-hidden="true" section="internal" class="icon-remove remove-attachments-internal-entry" entryId="<?php echo $id?>"></span></a>
        </div>
      </div>
    </div>
    <div id="attachmentsInternalCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsInternalInfo<?php echo $id?>">
          <?php include "attachments.internal.preview.php";?>
      </div>
  </div>
</div>