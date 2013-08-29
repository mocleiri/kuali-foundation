<?php
# Variables
$section = 'basics';
$page = 'basics-deliveryinfo';

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
					<h2>Delivery Info</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<h3>Recipient <span><a class="link-edit" href="#">Edit</a></span></h3>

						<div class="form-group clearfix">
							<label for="org_1" class="control-label col-md-3">Organization:</label>
							<div class="col-md-5">
								<input type="text" name="org_1" id="org_1" class="form-control input-sm col-md-8" value="UNIVERSITY OF MICHIGAN" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="address_1_1" class="control-label col-md-3">Address 1:</label>
							<div class="col-md-5">
								<input type="text" name="address_1_1" id="address_1_1" class="form-control input-sm col-md-8" value="2044 WOLVERINE TOWER" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="address_1_2" class="control-label col-md-3">Address 2:</label>
							<div class="col-md-5">
								<input type="text" name="address_1_2" id="address_1_2" class="form-control input-sm col-md-8" value="3003 STATE STREET" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="city_1" class="control-label col-md-3">City:</label>
							<div class="col-md-5">
								<input type="text" name="city_1" id="city_1" class="form-control input-sm col-md-8" value="ANN ARBOR" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="state_1" class="control-label col-md-3">State:</label>
							<div class="col-md-5">
								<input type="text" name="state_1" id="state_1" class="form-control input-sm col-md-8" value="MI" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="zip_1" class="control-label col-md-3">Zip:</label>
							<div class="col-md-5">
								<input type="text" name="zip_1" id="zip_1" class="form-control input-sm col-md-8" value="48109-1273" disabled />
							</div>
						</div>

						 <div class="form-group clearfix">
							<label for="congressional_2" class="control-label col-md-3">Congressional districts:</label>
							<div class="col-md-5">
								<select name="congressional_2" id="congressional_2" class="form-control input-sm col-md-8 chzn" multiple>
									<?php
									get_options();
									?>
								</select>
							</div>
						</div>

						<!-- <dl>
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
						</dl> -->

						<h3>Delivery Details</h3>
						<fieldset>
							<legend>Enter details for this delivery</legend>
							<div class="form-group clearfix">
								<label for="mail_by" class="control-label col-md-3">Mail by:</label>
								<div class="col-md-5">
									<select name="mail_by" id="mail_by" class="form-control input-sm col-md-8 chzn">
										
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_type" class="control-label col-md-3">Type:</label>
								<div class="col-md-5">
									<select name="mail_type" id="mail_type" class="form-control input-sm col-md-8 chzn">
										
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_account_id" class="control-label col-md-3">Mail account ID:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="mail_account_id" id="mail_account_id" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="number_of_copies" class="control-label col-md-3">Number of copies:</label>
								<div class="col-md-5">
									<input type="text" class="form-control input-sm" name="number_of_copies" id="number_of_copies" />
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_description" class="control-label col-md-3">Mail description:</label>
								<div class="col-md-5">
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
	<div class="btn-row-page-action">
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