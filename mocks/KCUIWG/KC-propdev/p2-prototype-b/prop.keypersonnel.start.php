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

	<div class="container">
		
		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9" id="content" tabindex="-1">
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
								<button class="btn btn-default launch-modal" data-modal-page="modal/modal-addpersonnel/start.html">+ Add Personnel</button>
							</div>
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