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
							<legend>Enter organization details</legend>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="inst_name">Organization:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="inst_name" name="inst_name" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="address_1">Address 1:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="address_1" name="address_1" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="address_2">Address 2:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="address_2" name="address_2" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="city">City:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="city" name="city" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="state">State:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="state" name="state" class="form-control input-sm">
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-sm-4 col-xs-4" for="zip">ZIP:</label>
								<div class="col-sm-8 col-xs-8">
									<input type="text" id="zip" name="zip" class="form-control input-sm">
								</div>
							</div>

						</fieldset>
					</form>
				</div>
			</div>

			<div class="modal-footer">
				<a href="lookup-institution-other.php" class="btn btn-default">Find an organization</a>
				<a href="lookup-institution-results.php" class="btn btn-primary">Add and continue</a>
			</div>
		</div>
	</div>

<?php include( 'inc/footer.php' ); ?>