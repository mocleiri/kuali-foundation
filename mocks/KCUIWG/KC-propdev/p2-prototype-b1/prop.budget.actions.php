<?php
# Variables
$section = 'budget';
$page = 'budget-actions';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->

				
					<h3>Budget Actions</h3>
				

				
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Coming soon</legend>
							
						</fieldset>
					</form>
				

				<!-- // -->

        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.budget.modular.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.inst.1.php'" class="btn btn-primary">Save and Continue</button>
			
		</div>
		<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>






