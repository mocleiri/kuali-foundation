<?php

# Includes
include( 'inc/head.php' );
?>


	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h3>Institution Search</h3>
			</div>

			<div class="modal-body">
				<div class="container">
					<form class="form-horizontal" method="get" action="">
						<fieldset>
							<legend>Enter lookup criteria</legend>

							<!-- <div class="form-group clearfix">
								<label for="sponsor_code" class="control-label col-sm-4 col-xs-4">Sponsor:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="sponsor_code" name="sponsor_code" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="sponsor_name">Sponsor name:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="sponsor_name" name="sponsor_name" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="accronym">Acronym:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="accronym" name="accronym" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="sponsor_type_code">Sponsor type code:</label>
								<div class="col-md-5 input-group">
									<select name="sponsor_type_code" id="sponsor_type_code" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option>Federal</option>
										<option>State</option>
										<option>Local Government</option>
										<option>Private Profit</option>
										<option>Private Non-Profit</option>
										<option>Foundation</option>
										<option>Instituion of Higher Education</option>
										<option>Foreign Federal Government</option>
										<option>Foreign State Government</option>
										<option>Foreign Local Government</option>
										<option>Foreign Private Profit</option>
										<option>Foreign Private Non-Profit</option>
										<option>Foreign Foundation</option>
										<option>Foreign Institute of Higher Education</option>
									</select>
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="dun_bs_num">DUN and Bradstreet #:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="dun_bs_num" name="dun_bs_num" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="duns_4_num">DUNS +4 #:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="duns_4_num" name="duns_4_num" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="dodac_num">DODAC #:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="dodac_num" name="dodac_num" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="cage_num">CAGE #:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="cage_num" name="cage_num" class="form-control input-sm">
								</div>
							</div> -->

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="postal_code">Postal code:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="postal_code" name="postal_code" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="state">State:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="state" name="state" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="country">Country code:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="country" name="country" class="form-control input-sm">
								</div>
							</div>

							<!-- <div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="audit_report">Audit report sent for fy:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="audit_report" name="audit_report" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="owned_by_unit">Owned by unit:</label>
								<div class="col-md-5 input-group">
									<input type="text" id="owned_by_unit" name="owned_by_unit" class="form-control input-sm">
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="address_book">Address book ID:</label>
								<div class="col-md-5 input-group">
									<input type="text" id="address_book" name="address_book" class="form-control input-sm">
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div> -->

						</fieldset>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<a href="lookup-institution-results.php" class="btn btn-primary pull-right">Search</a>
			</div>
		</div>
	</div>

<?php include( 'inc/footer.php' ); ?>