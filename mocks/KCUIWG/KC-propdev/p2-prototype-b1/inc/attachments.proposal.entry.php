<div class="panel panel-default" id="attachmentProposalEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3">
          <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> Narrative</a> </h4>
        </div>
        <div class="col-md-3"> <?php echo str_replace("C:\\fakepath\\", "", $entry['uploadFile'])?> </div>
        <div class="col-md-3"> <?php echo $entry['approval_status']?> </div>
        <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
      </div>
    </div>
    <div id="collapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body ">


          <?php include "attachments.proposal.preview.php";?>


      </div>
  </div>
</div>