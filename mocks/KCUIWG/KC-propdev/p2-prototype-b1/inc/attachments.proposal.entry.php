<div class="panel panel-default" id="attachmentsProposalEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <div class="col-md-3">
          <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#attachmentsProposalCollapse<?php echo $id?>"><span aria-hidden="true" class="icon-caret-right"></span> <?php echo $entry['type']?></a> </h4>
        </div>
        <div class="col-md-3"> <?php echo $entry['uploadFile']?> </div>
        <div class="col-md-3"> <?php echo $entry['approval_status']?> </div>
        <div class="col-md-3"><a class="pull-right" href="#"><span aria-hidden="true" section="proposal" class="icon-remove remove-attachments-proposal-entry" entryId="<?php echo $id?>"></span></a></div>
      </div>
    </div>
    <div id="attachmentsProposalCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsProposalInfo<?php echo $id?>">


          <?php include "attachments.proposal.preview.php";?>


      </div>
  </div>
</div>