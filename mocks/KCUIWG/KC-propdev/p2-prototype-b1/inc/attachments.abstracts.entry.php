<div class="panel panel-default" id="attachmentsAbstractsEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-9">
          <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#attachmentsAbstractsCollapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a></h4>
        </div>
        <div class="col-md-3"><a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove remove-attachments-abstracts-entry" entryId="<?php echo $id?>"></span></a></div>
      </div>
    </div>
    <div id="attachmentsAbstractsCollapse<?php echo $id?>" class="panel-collapse collapse">
              <div class="panel-body" id="attachmentAbstractsInfo<?php echo $id?>">
            <?php include "attachments.abstracts.preview.php";?>

      </div>
    </div>
  </div>