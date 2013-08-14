<?php
# Variables
$page = 'basics-deliveryinfo';

# Includes
require_once( 'kc/inc/head.php' );
require_once( 'kc/inc/nav.php' );
require_once( 'kc/inc/toolbar.php' );
?>

<section id="main">
		
	<?php require_once( 'kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<?php require_once( 'kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-lg-9">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Delivery Info</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<h3>Recipient <span><a href="#">Change</a></span></h3>
						<dl>
							<dt>Organization</dt>
							<dd>UNIVERSITY OF MICHIGAN</dd>

							<dt>Address 1</dt>
							<dd>2044 Wolverine Tower</dd>

							<dt>Address 2</dt>
							<dd>3003 State Street</dd>

							<dt> City</dt>
							<dd>ANN ARBOR</dd>

							<dt>State</dt>
							<dd>MI</dd>

							<dt>Zip</dt>
							<dd>48109-1273</dd>
						</dl>

						<h3>Delivery Details</h3>
						<fieldset>
							<legend>Enter details for this delivery</legend>
							<div class="form-group clearfix">
								<label for="mail_by" class="control-label col-lg-3">Mail by:</label>
								<div class="col-lg-5">
									<select name="mail_by" id="mail_by" class="form-control input-sm col-lg-8">
										<option value="">select</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_type" class="control-label col-lg-3">Type:</label>
								<div class="col-lg-5">
									<select name="mail_type" id="mail_type" class="form-control input-sm col-lg-8">
										<option value="">select</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_account_id" class="control-label col-lg-3">Mail account ID:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control input-sm" name="mail_account_id" id="mail_account_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="number_of_copies" class="control-label col-lg-3">Number of copies:</label>
								<div class="col-lg-5">
									<input type="text" class="form-control input-sm" name="number_of_copies" id="number_of_copies" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_description" class="control-label col-lg-3">Mail description:</label>
								<div class="col-lg-5">
									<textarea name="mail_description" id="mail_description" class="form-control input-sm"></textarea>
								</div>
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

<?php require_once( 'kc/inc/footer.php' ); ?>