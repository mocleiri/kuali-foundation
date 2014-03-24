<?php

# Includes
require_once( 'inc/head.php' );
?>

<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header clearfix">
      <h3> Routing Trail</h3>
    </div>
    <div class="modal-body">
      <div class="boxContent">
       
         
          <div class="progress-details">
            <div class="empty complete" style="width: 20%">In Progress</div>
            <div class="empty complete" style="width: 20%">Routing &amp; Review</div>
            <div class="empty active" style="width: 20%">Final Institutional Review</div>
            <div class="empty" style="width: 20%">Approved</div>
            <div class="empty" style="width: 20%">Submitted to Sponsor</div>
          </div>
          <div class="progress">
            <div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
            <div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
            <div class="progress-bar progress-bar-warning" style="width: 20%;" title="In Progress" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
            <div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
            <div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
          </div>
        
      </div>
    </div>
  
  </div>
</div>
</div>
<?php require_once( 'inc/footer.php' ); ?>