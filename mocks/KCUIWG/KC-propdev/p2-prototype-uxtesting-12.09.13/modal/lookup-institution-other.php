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

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="inst_name">Institution name:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="inst_name" name="inst_name" class="form-control input-sm">
								</div>
							</div>

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

						</fieldset>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<a href="lookup-institution-other-add.php" class="btn btn-default">Add unlisted organization</a>
				<a href="lookup-institution-results.php" class="btn btn-primary">Search</a>
			</div>
		</div>
	</div>

<?php include( 'inc/footer.php' ); ?>