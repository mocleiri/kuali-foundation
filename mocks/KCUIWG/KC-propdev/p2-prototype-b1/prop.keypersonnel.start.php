<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-start';

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
        
        <h3>Key Personnel</h3>
        <p>Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, and any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).</p>
        <div class="btn-row-widget-action">
          <button class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-addpersonnel/start.html"><span aria-hidden="true" class="icon-plus"></span> Add Personnel</button>
        </div>
        
        <!-- // -->
        
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <button  onclick="location.href='prop.basics.orgloc.php'" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='prop.keypersonnel.creditintel.php'" class="btn btn-primary">Save and Continue</button>
          </div>
          <!-- // --> 
          
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>
