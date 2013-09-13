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

	<div class="container-fluid"> 	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				
					<h3>Key Personnel</h3>
				

				<div class="section-content">
					<form action="#" method="post" class="">
						<fieldset>
							<legend>Search for and add key personnel</legend>
							<p>Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, and any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).
							<div class="form-group clearfix">
								<button class="btn btn-default launch-modal" data-modal-page="modal/modal-addpersonnel/start.html">+ Add Personnel</button>
							</div>
						</fieldset>
					</form>
				</div>

				<!-- // -->

			</div></div>

		</div>

		<!-- Button row -->
		<div class="btn-row-page-action">
			
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>