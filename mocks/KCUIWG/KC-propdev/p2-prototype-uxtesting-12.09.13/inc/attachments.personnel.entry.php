  <div class="panel panel-default" id="attachmentsPersonnelEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <a class="accordion-toggle icon icon-caret-right" data-toggle="collapse" data-parent="#accordion" href="#attachmentsPersonnelCollapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
        <div class="col checkbox">
          <input type="checkbox" id="attachment-personnel-id-<?php echo $id ?>" name="attachment-personnel-id-<?php echo $id ?>" />
        </div>
        <div class="col panel-title-container">
          <label for="attachment-personnel-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
        </div>
        <div class="col panel-file-preview">
          <?php echo $entry['uploadFile']?>
        </div>
        <div class="col panel-status-preview">
          <?php echo $entry['approval_status']?>
        </div>
        <div class="col panel-remove pull-right">
          <a href="#"><span aria-hidden="true" section="personnel" class="icon-remove remove-attachments-personnel-entry" entryId="<?php echo $id?>"></span></a>
        </div>
      </div>
    </div>
    <div id="attachmentsPersonnelCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsPersonnelInfo<?php echo $id?>">
          <?php include "attachments.personnel.preview.php";?>
      </div>
  </div>
</div>