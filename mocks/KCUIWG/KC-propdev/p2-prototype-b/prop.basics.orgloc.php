<?php
# Variables
$section = 'basics';
$page = 'basics-orgloc';

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
					<h2>Organization / Location</h2>
				</div>

				<div class="section-content">
					<form action="" method="post" class="form-horizontal">
						<h3>Applicant Organization <span><a class="link-edit" href="#">Edit</a></span></h3>

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
							<label for="congressional_1" class="control-label col-md-3">Congressional districts:</label>
							<div class="col-md-5">
								<select name="congressional_1" id="congressional_1" class="form-control input-sm col-md-8 chzn" multiple>
									<?php
									get_options();
									?>
								</select>
							</div>
						</div>

						<h3>Performing Organization <span><a class="link-edit" href="#">Edit</a></span></h3>

						<div class="form-group clearfix">
							<label for="org_2" class="control-label col-md-3">Organization:</label>
							<div class="col-md-5">
								<input type="text" name="org_2" id="org_2" class="form-control input-sm col-md-8" value="UNIVERSITY OF MICHIGAN" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="address_2_2" class="control-label col-md-3">Address 1:</label>
							<div class="col-md-5">
								<input type="text" name="address_2_2" id="address_2_2" class="form-control input-sm col-md-8" value="2044 WOLVERINE TOWER" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="address_2_2" class="control-label col-md-3">Address 2:</label>
							<div class="col-md-5">
								<input type="text" name="address_2_2" id="address_2_2" class="form-control input-sm col-md-8" value="3003 STATE STREET" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="city_2" class="control-label col-md-3">City:</label>
							<div class="col-md-5">
								<input type="text" name="city_2" id="city_2" class="form-control input-sm col-md-8" value="ANN ARBOR" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="state_2" class="control-label col-md-3">State:</label>
							<div class="col-md-5">
								<input type="text" name="state_2" id="state_2" class="form-control input-sm col-md-8" value="MI" disabled />
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="zip_2" class="control-label col-md-3">Zip:</label>
							<div class="col-md-5">
								<input type="text" name="zip_2" id="zip_2" class="form-control input-sm col-md-8" value="48109-1273" disabled />
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
					</form>
				</div>

				<!-- <div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<h3>Applicant Organization <span><a class="link-edit" href="#">Change</a></span></h3>
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

							<dt>Congressional District</dt>
							<dd>MI-302 <a class="link-delete" href="#">- Delete</a></dd>

							<dt>Congressional District</dt>
							<dd>MI-307 <a class="link-delete" href="#">- Delete</a></dd>

							<dt>Congressional District</dt>
							<dd><a class="link-add" href="#">+ Add</a></dd>
						</dl>

						<h3>Performing Organization <span><a class="link-edit" href="#">Change</a></span></h3>
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

							<dt>Congressional District</dt>
							<dd>MI-302 <a class="link-delete" href="#">- Delete</a></dd>

							<dt>Congressional District</dt>
							<dd>MI-307 <a class="link-delete" href="#">- Delete</a></dd>

							<dt>Congressional District</dt>
							<dd><a class="link-add" href="#">+ Add</a></dd>
						</dl>
					</form>
				</div> -->

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