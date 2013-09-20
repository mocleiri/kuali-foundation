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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container"><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Delivery Info</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<div class="tab-title-container clearfix">
				            <h4>Recipient</h4>
				        </div>

						<div class="form-group clearfix">
							<label for="app_org_org" class="control-label col-md-3">Organization:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_org" id="app_org_org" value="University of Michigan" />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
								</span>
							</div>
						</div>

			          	<div class="form-group clearfix">
							<label for="app_org_add1" class="control-label col-md-3">Address 1:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_add1" id="app_org_add1" value="2044 Wolverine Tower" disabled />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
							</div>
						</div>

			          	<div class="form-group clearfix">
							<label for="app_org_add2" class="control-label col-md-3">Address 2:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_add2" id="app_org_add2" value="3003 State Street" disabled />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
							</div>
						</div>

			          	<div class="form-group clearfix">
							<label for="app_org_city" class="control-label col-md-3">City:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_city" id="app_org_city" value="Ann Arbor" disabled />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
							</div>
						</div>

			          	<div class="form-group clearfix">
							<label for="app_org_state" class="control-label col-md-3">Address 2:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_state" id="app_org_state" value="MI" disabled />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
							</div>
						</div>

			          	<div class="form-group clearfix">
							<label for="app_org_zip" class="control-label col-md-3">Zipcode:</label>
							<div class="col-md-5 input-group">
								<input type="text" class="form-control input-sm" name="app_org_zip" id="app_org_zip" value="3003 State Street" disabled />
								<div class="helper-text">
									Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
								</div>
							</div>
						</div>

						<div class="form-group clearfix">
							<label for="app_org_districts" class="control-label col-md-3">Congressional districts:</label>
							<div class="col-md-5">
								<select name="app_org_districts" id="app_org_districts" class="form-control input-sm col-md-8 chzn" multiple>
									<option selected="selected">One</option>
									<option selected="selected">Two</option>
									<option selected="selected">Three</option>
								</select>
							</div>
						</div>

						<h3>Delivery Details</h3>
						<fieldset>
							<legend>Enter details for this delivery</legend>
							<div class="form-group clearfix">
								<label for="mail_type" class="control-label col-md-3">Type:</label>
								<div class="col-md-5">
									<select name="mail_type" id="mail_type" class="form-control input-sm col-md-8 chzn onchange">
										<option></option>
										<option value="regular">Regular</option>
										<option value="electronic">Electronic</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="hidden-fields" id="electronic">
								
								<div class="form-group clearfix">
									<label for="opp_id" class="control-label col-md-3">Opportunity ID:</label>
									<div class="col-md-5">
										<input type="text" name="opp_id" id="opp_id" class="form-control input-sm col-md-8" />
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="opp_title" class="control-label col-md-3">Opportunity title:</label>
									<div class="col-md-5">
										<input type="text" name="opp_title" id="opp_title" class="form-control input-sm col-md-8" />
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="cfda_num" class="control-label col-md-3">CFDA Number:</label>
									<div class="col-md-5">
										<input type="text" name="cfda_num" id="cfda_num" class="form-control input-sm col-md-8" />
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>

							</div>

							<div class="hidden-fields" id="regular">

								<div class="form-group clearfix">
									<label for="mail_by" class="control-label col-md-3">Mail by:</label>
									<div class="col-md-5">
										<select name="mail_by" id="mail_by" class="form-control input-sm col-md-8 chzn">
											<option value="">Department</option>
											<option value="">OSP</option>
										</select>
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="mail_account_id" class="control-label col-md-3">Mail account ID:</label>
									<div class="col-md-5">
										<input type="text" class="form-control input-sm" name="mail_account_id" id="mail_account_id" />
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>

								<div class="form-group clearfix">
									<label for="number_of_copies" class="control-label col-md-3">Number of copies:</label>
									<div class="col-md-5">
										<input type="text" class="form-control input-sm" name="number_of_copies" id="number_of_copies" />
										<div class="helper-text">
											Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
										</div>
									</div>
								</div>

							</div>
								
							<div class="form-group clearfix">
								<label for="submission_type" class="control-label col-md-3">Submission type:</label>
								<div class="col-md-5">
									<select name="submission_type" id="submission_type" class="form-control input-sm col-md-8 chzn">
										<option>- Please select -</option>
										<option>Decrease award</option>
										<option>Increase award</option>
										<option>Order pizza</option>
										<option>I'm kinda hungry</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="mail_description" class="control-label col-md-3">Mail description:</label>
								<div class="col-md-5">
									<textarea name="mail_description" id="mail_description" class="form-control input-sm"></textarea>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

						</fieldset>
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
			<button class="btn btn-primary" href="prop.basics.keywords.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>