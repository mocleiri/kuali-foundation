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
                    <li><a href="#">Reload proposal</a></li>
                    <li><a href="#">Cancel proposal</a></li>
                    <li class="divider"></li>
                    <li><a href="#" class="label-danger" style="color: white;">Delete Proposal</a></li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <span><a href="#">Send Notifications</a><!--  | <a href="#">View Route Log</a></span> -->
        </div>
        
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>