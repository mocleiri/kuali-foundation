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
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1">

        <div class="row has-tools">
          <div class="col-md-12">
            <h3>S2S Opportunity Search</h3>
          </div>

          <div class="col-md-12">
            <div class="uif-toolbar">
              <button class="btn btn-default btn-starter launch-modal" data-modal-page="modal/lookup-oppsearch.php"><i class="icon icon-search"></i> Find an opportunity</button>
            </div>
          </div>
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
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <div class="container btn-row-page-action">
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