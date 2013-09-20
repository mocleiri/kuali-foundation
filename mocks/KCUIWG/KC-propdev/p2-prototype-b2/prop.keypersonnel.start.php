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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
	<div class="container">
		<div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
				<div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Key Personnel</h2>
				</div>

				<div class="section-content">
					<div class="tab-title-container clearfix">
						<h4>You haven't added any key personnel yet.</h4>
			        </div>

			        <div class="well">
	            		<button class="btn btn-default launch-modal" data-modal-page="modal/modal-addpersonnel/start.html" data-modal-height="500">Add personnel</button>
	            	</div>

	                <div id="personnel-tabs">
	                </div>
	            </div>

				<!-- // -->

			</div>

		</div>

	</div>

</section>

<div class="page-controls clearfix">
	<div class="page-actions">
		<div class="well"></div>
	</div>

	<div class="page-navigation">
		<div class="well">
			<button class="btn btn-default">Save</button>
			<button class="btn btn-primary">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>