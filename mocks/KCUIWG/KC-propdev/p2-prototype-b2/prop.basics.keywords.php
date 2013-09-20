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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container"><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
			  	<div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Keywords</h2>
				</div>

				<div class="section-content">
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
			<button class="btn btn-primary" href="prop.keypersonnel.start.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>