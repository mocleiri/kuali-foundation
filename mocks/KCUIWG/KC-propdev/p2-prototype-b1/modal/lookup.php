<?php include ('head.php') ?>

<body>
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h3>Lookup</h3>
			</div>

			<div class="modal-body">
				<form class="form-horizontal" method="get" action="">
					<fieldset>
						<legend>Enter lookup criteria</legend>

						<div class="form-group clearfix">
							<label for="award_id" class="control-label col-md-3">Award ID:</label>
							<div class="col-md-5">
								<input type="text" id="award_id" name="award_id" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="sponsor_award_id">Sponsor award ID:</label>
							<div class="col-md-5">
								<input type="text" id="sponsor_award_id" name="sponsor_award_id" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="account_id">Account ID:</label>
							<div class="col-md-5">
								<input type="text" id="account_id" name="account_id" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="award_status">Award status:</label>
							<div class="col-md-5 input-group">
								<select name="award_status" id="award_status" class="form-control input-sm col-md-8" multiple>
									<option></option>
									<option>Active</option>
									<option>Closed</option>
									<option>Hold</option>
									<option>Inactive</option>
									<option>Pending</option>
									<option>Terminated</option>
								</select>
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup.php" data-modal-height="500"></a>
								</span>
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="sponsor_id">Sponsor ID:</label>
							<div class="col-md-5 input-group">
								<input type="text" id="sponsor_id" name="sponsor_id" class="form-control input-sm col-md-8">
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup.php" data-modal-height="500"></a>
								</span>
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="award_title">Award title:</label>
							<div class="col-md-5">
								<input type="text" id="award_title" name="award_title" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="investigator">Investigator:</label>
							<div class="col-md-5">
								<input type="text" id="investigator" name="investigator" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="lead_unit_id">Lead unit ID:</label>
							<div class="col-md-5 input-group">
								<input type="text" id="lead_unit_id" name="lead_unit_id" class="form-control input-sm col-md-8">
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup.php" data-modal-height="500"></a>
								</span>
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="lead_unit">Lead unit:</label>
							<div class="col-md-5 input-group">
								<input type="text" id="lead_unit" name="lead_unit" class="form-control input-sm col-md-8">
								<span class="input-group-btn">
									<a href="#" class="icon-search launch-modal" data-modal-page="modal/lookup.php" data-modal-height="500"></a>
								</span>
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="osp_admin">OSP administrator:</label>
							<div class="col-md-5">
								<input type="text" id="osp_admin" name="osp_admin" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3" for="archive_location">Archive location:</label>
							<div class="col-md-5">
								<input type="text" id="archive_location" name="archive_location" class="form-control input-sm col-md-8">
							</div>
						</div>

						<div class="form-group clearfix">
							<label class="control-label col-md-3">Archive dates:</label>
							<div class="col-md-5">
								<div class="col-md-6 date">
									<input type="text" size="12" class="form-control input-sm uif-dateControl" name="archive_date_start" id="archive_date_start" placeholder="mm/dd/yyyy" value="01/03/2014" />
									<label for="archive_date_start" class="helper-text">Beginning on</label>
								</div>
								<div class="col-md-6 date">
									<input type="text" size="12" class="form-control input-sm uif-dateControl" name="archive_date_end" id="archive_date_end" placeholder="mm/dd/yyyy" value="12/18/2014" />
									<label for="archive_date_end" class="helper-text">Ending on</label>
								</div>
							</div>
						</div>

					</fieldset>
				</form>
			</div>

			<div class="modal-footer">
				<a href="lookup-oppsearch-results.php" class="btn btn-primary pull-right">Search</a>
			</div>
		</div>
	</div>

<?php include ('footer.php') ?>