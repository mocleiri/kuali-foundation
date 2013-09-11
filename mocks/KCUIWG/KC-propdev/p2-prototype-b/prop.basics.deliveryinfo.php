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

		
	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container-fluid"><div class="row-temp-disabled">

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
				            <h4>Recipeint</h4>

				            <div class="page-controls">
				            	<div class="well">
				            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
				            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Add new</button>
				            	</div>
				            </div>
				        </div>

						<table  class="table table-condensed">
							<tbody>
								<tr>
									<th style="width:30%">Organization</th>
									<td>United Technologies/Pratt &amp; Whitney <a href="#">lookup (icon)</a></td>
								</tr>
								<tr>
									<th style="width:30%">Address 1</th>
									<td>Mail Stop 169-21</td>
								</tr>
								<tr>
									<th>Address 2</th>
									<td>400 Main Street</td>
								</tr>
								<tr>
									<th>City</th>
									<td>East Hartford</td>
								</tr>
								<tr>
									<th>State</th>
									<td>CT</td>
								</tr>
								<tr>
									<th>ZIP</th>
									<td>06108</td>
								</tr>
								<tr>
									<th>Congressional districts</th>
									<td>
										<select name="congressional_2" id="congressional_2" class="form-control input-sm col-md-8 chzn" multiple>
											<?php
											get_options();
											?>
										</select>
									<td>
								</tr>
							</tbody>
						</table>

						<h3>Delivery Details</h3>
						<fieldset>
							<legend>Enter details for this delivery</legend>
							<div class="form-group clearfix">
								<label for="mail_type" class="control-label col-md-3">Type:</label>
								<div class="col-md-5">
									<select name="mail_type" id="mail_type" class="form-control input-sm col-md-8 chzn onchange">
										<option>- Please select -</option>
										<option value="regular">Regular</option>
										<option value="electronic">Electronic</option>
									</select>
								</div>
							</div>

							<div class="hidden-fields" id="electronic">
								
								<div class="form-group clearfix">
									<label for="opp_id" class="control-label col-md-3">Opportunity ID:</label>
									<div class="col-md-5">
										<input type="text" name="opp_id" id="opp_id" class="form-control input-sm col-md-8" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="opp_title" class="control-label col-md-3">Opportunity title:</label>
									<div class="col-md-5">
										<input type="text" name="opp_title" id="opp_title" class="form-control input-sm col-md-8" />
									</div>
								</div>
								
								<div class="form-group clearfix">
									<label for="cfda_num" class="control-label col-md-3">CFDA Number:</label>
									<div class="col-md-5">
										<input type="text" name="cfda_num" id="cfda_num" class="form-control input-sm col-md-8" />
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