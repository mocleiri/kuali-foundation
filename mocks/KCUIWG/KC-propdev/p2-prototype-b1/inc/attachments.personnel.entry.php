<div class="panel panel-default" id="attachmentPersonnelEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3">
          <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion2" href="#collapseAttachmentsPersonnel<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a> </h4>
        </div>
        <div class="col-md-3"> <?php echo $entry['uploadFile']?> </div>
        <div class="col-md-3"> <?php echo $entry['person']?> </div>
        <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true"  class="icon-remove remove-attachments-personnel-entry" entryId="<?php echo $id?>"></span></a> </div>
      </div>
    </div>
    <div id="collapseAttachmentsPersonnel<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentPersonnelInfo<?php echo $id?>">
          <?php include "attachments.personnel.preview.php";?>

      </div>
    </div>
  </div>