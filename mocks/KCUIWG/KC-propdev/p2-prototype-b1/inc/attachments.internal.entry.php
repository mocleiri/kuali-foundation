<div class="panel panel-default" id="attachmentsInternalEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-6">
          <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion3" href="#attachmentsInternalCollapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a></h4>
        </div>
        <div class="col-md-3"> <?php echo $entry['uploadFile']?> </div>
        <div class="col-md-3"><a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove remove-attachments-internal-entry" entryId="<?php echo $id?>"></span></a></div>
      </div>
    </div>
    <div id="attachmentsInternalCollapse<?php echo $id?>" class="panel-collapse collapse">
          <div class="panel-body" id="attachmentsInternalInfo<?php echo $id?>">
        <?php include "attachments.internal.preview.php";?>
      </div>
    </div>
  </div>