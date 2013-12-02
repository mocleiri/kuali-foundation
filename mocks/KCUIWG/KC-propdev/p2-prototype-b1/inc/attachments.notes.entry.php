<div class="panel panel-default" id="attachmentsNotesEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <a class="accordion-toggle icon icon-caret-right" data-toggle="collapse" data-parent="#accordion" href="#attachmentsNotesCollapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
        <div class="col checkbox">
          <input type="checkbox" id="attachment-notes-id-<?php echo $id ?>" name="attachment-notes-id-<?php echo $id ?>" />
        </div>
        <div class="col panel-title-container">
          <label for="attachment-notes-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
        </div>
        <div class="col panel-file-preview">
          <?php echo $entry['uploadFile']?>
        </div>
        <div class="col panel-status-preview">
          <?php echo $entry['approval_status']?>
        </div>
        <div class="col panel-remove pull-right"
          <a href="#"><span aria-hidden="true" section="notes" class="icon-remove remove-attachments-notes-entry" entryId="<?php echo $id?>"></span></a>
        </div>
      </div>
    </div>
    <div id="attachmentsNotesCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsNotesInfo<?php echo $id?>">
          <?php include "attachments.notes.preview.php";?>
      </div>
  </div>
</div>