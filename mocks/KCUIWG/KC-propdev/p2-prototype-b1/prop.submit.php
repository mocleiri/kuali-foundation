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
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
        <h3>Submit</h3>
        <div class="boxContent">
          <div class="well">
            <div class="proposal-actions clearfix">
              <div class="pull-left"> <a href="#" class="btn btn-primary">Submit for Review</a> </div>
              <div class="pull-right"> <a href="#" class="btn btn-default">Save</a> <a href="#" class="btn btn-default">Close</a> <a href="#" class="btn btn-danger">Delete Proposal</a> </div>
            </div>
          </div>
          <span><a href="#">Send Notifications</a> | <a href="#">View Route Log</a></span> </div>
        
        <!-- // --> 
        
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>