<div class="panel panel-default" id="attachmentsProposalEntry<?php echo $id?>">
    <div class="panel-heading">
      <div class="row">
        <a class="accordion-toggle icon icon-caret-right" data-toggle="collapse" data-parent="#accordion" href="#attachmentsProposalCollapse<?php echo $id?>" entryId="<?php echo $id?>" style=""></a>
        <div class="col checkbox">
          <input type="checkbox" id="attachment-proposal-id-<?php echo $id ?>" name="attachment-proposal-id-<?php echo $id ?>" />
        </div>
        <div class="col panel-title-container">
          <label for="attachment-proposal-id-<?php echo $id ?>"><strong class="panel-title"><?php echo $entry['type']?></strong></label>
        </div>
        <div class="col panel-file-preview">
          <?php echo $entry['uploadFile']?>
        </div>
        <div class="col panel-status-preview">
          <?php echo $entry['approval_status']?>
        </div>
        <div class="col panel-remove pull-right">
          <a href="#"><span aria-hidden="true" section="proposal" class="icon-remove remove-attachments-proposal-entry btn btn-default btn-xs" entryId="<?php echo $id?>"></span></a>
        </div>
      </div>
    </div>
    <div id="attachmentsProposalCollapse<?php echo $id?>" class="panel-collapse collapse">
      <div class="panel-body" id="attachmentsProposalInfo<?php echo $id?>">
          <?php include "attachments.proposal.preview.php";?>
      </div>
  </div>
</div>