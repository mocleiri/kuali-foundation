<?php
# Variables
$section = 'basics';
$page = 'basics-search';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1"> <!-- Main content goes here -->
        
        <div class="section-title">
          <h3>Opportunity Search</h3>
        </div>        
        
        <div class="btn-row-widget-action">
            <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/lookup-oppsearch.php"> <i class="icon-search" aria-hidden="true"></i> Find an opportunity</button>
        </div>
      
        <div class="section-content hidden">
          <form action="#" method="post" class="">
            <fieldset>
              <legend>Opportunity actions</legend>
              <div class="form-group clearfix">
                <button class="btn btn-default modal" data-page="page-name-here.html">Load New Opportunity</button>
              </div>
            </fieldset>
          </form>
        </div>
        
        <div class="uif-stickyFooter uif-stickyButtonFooter">
          <div class="btn-row-page-action">
            <button href="prop.basics.details.php" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button href="prop.basics.deliveryinfo.php" class="btn btn-primary">Save and Continue</button>
          </div>
          
        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>