<div class="panel panel-default" id="attachmentsNotesEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-6">
          <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion5" href="#attachmentsNotesCollapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['topic']?></a> </h4>
        </div>
        <div class="col-md-3"> McGregor, Geoff </div>
        <div class="col-md-3"> <?php echo $entry['uploadTime']?> </div>
      </div>
    </div>
    <div id="attachmentsNotesCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsNotesInfo<?php echo $id?>">
            <?php include "attachments.notes.preview.php";?>
      </div>
    </div>
  </div>