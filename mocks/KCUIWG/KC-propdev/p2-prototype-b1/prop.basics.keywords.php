<?php
# Variables
$section = 'basics';
$page = 'basics-keywords';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
include( '_temp.select.filler.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
        <h3>Keywords</h3>
        <form action="#" method="post" class="form-horizontal">
          <div class="form-group clearfix">
            <label for="keywords" class="control-label col-md-3">Keywords:</label>
            <div class="col-md-5">
              <select name="keywords" id="keywords" class="form-control input-sm col-md-8 chzn" multiple>
                <?php
									get_options();
									?>
              </select>
            </div>
          </div>
        </form>
        
        <!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <button  onclick="location.href='filenamehereeeeeeeeee'" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='filenamehereeeeeeeeee'" class="btn btn-primary">Save and continue</button>
          </div>
          <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>
<section id="main">
  <div class="container-fluid" >
    <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
    <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
    </div>
    <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"> </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>