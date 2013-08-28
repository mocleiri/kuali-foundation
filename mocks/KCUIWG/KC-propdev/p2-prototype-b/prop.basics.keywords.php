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
		
	<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9" id="content" tabindex="-1">
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

						<!-- <dl>
							<dt>1</dt>
							<dd>Carbon <a class="link-delete" href="#">- Delete</a></dd>

							<dt>2</dt>
							<dd>Heat <a class="link-delete" href="#">- Delete</a></dd>

							<dt>3</dt>
							<dd><a class="link-add" href="#">+ Add</a></dd>
						</dl> -->
					</form>
				</div>

				<!-- // -->

			</div>

		</div>

	</div>

	<!-- Button row -->
	<div class="button-row">
		<div class="container">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
	</div>
	<!-- // -->

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>