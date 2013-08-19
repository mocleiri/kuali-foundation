<?php
# Variables
$page = 'keypersonnel-start';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
		
	<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-lg-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Key Personnel</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="">
						<fieldset>
							<legend>Search for and add key personnel</legend>
							<p>Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, and any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).
							<div class="form-group clearfix">
								<button class="btn btn-default">+ Add Personnel</button>
							</div>
						</fieldset>
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