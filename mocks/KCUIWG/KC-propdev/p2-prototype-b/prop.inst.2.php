<?php
# Variables
$section = 'instdata';
$page = 'institution-page2';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container">

		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Institution Data</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Coming soon</legend>
							
						</fieldset>
					</form>
				</div>

				<!-- // -->

			</div>

		</div>

		<!-- Button row -->
		<div class="btn-row-page-action">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>