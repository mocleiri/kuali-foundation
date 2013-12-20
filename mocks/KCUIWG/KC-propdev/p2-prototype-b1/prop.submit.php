<?php
# Variables
$page = 'prop-submit';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" sty  le="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">
        
        <h3>Submit</h3>
        <div class="boxContent">
          <div class="well">
            <h4>Proposal progress</h4>
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

          <div class="">
            <div class="proposal-actions clearfix">
              <div class="pull-left">
                <a href="#" class="btn btn-primary">Submit for Review</a>
                <a href="#" class="btn btn-link">View Route Log</a>
              </div>
              <div class="pull-right">
                <a href="#" class="btn btn-default">Save and Exit</a>
                <a href="#" class="btn btn-default">Exit without saving</a> <!-- <a href="#" class="btn btn-danger">Delete</a> -->
                <div class="btn-group">
                  <button type="button" class="btn btn-default dropdown-toggle" data-toggle="dropdown">
                    More actions <span class="caret"></span>
                  </button>
                  <ul class="dropdown-menu" role="menu">
                    <li><a href="#">Resend notifications</a></li>
                    <li><a href="#">Reload proposal</a></li>
                    <li><a href="#">Cancel proposal</a></li>
                    <li class="divider"></li>
                    <li><a href="#" class="label-danger" style="color: white;">Delete Proposal</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>

          <!-- <div class="" style="margin-top: 16px;">
            <a href="#">Send Notifications</a> | <a href="#">View Route Log</a>
          </div> -->
        </div>
        
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>