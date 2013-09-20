<?php
# Variables
$page = 'compliance';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

	<div class="container">
		<div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
			  	<div id="content" class="uif-page" tabindex="-1">

					<div class="section-title">
						<h2>Compliance</h2>
					</div>

					<div class="section-content">
						<div class="tab-title-container clearfix">
							<h4>Manage compliance requirements for this proposal</h4>
				        </div>

				        <div class="well">
				        	<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Add new requirement</button>
				        </div>

				        <h4>Existing requirements</h4>

						<div class="entry-row clearfix" id="compliance_1">
							<div class="panel panel-default">
								<div class="panel-heading">
									<div class="row">
										<div class="col-md-6">
											<h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse3"> <span aria-hidden="true" class="icon-caret-down"></span> International Programs...</a></h4>
										</div>
										<div class="col-md-6">
											<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
										</div>
									</div>
								</div>
								<div id="collapse3" class="panel-collapse collapse">
									<div class="panel-body">
										<div class="form-group clearfix">
											<label for="req_01_type" class="control-label col-md-3">Type:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm" name="req_01_type" id="req_01_type" value="International Programs" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
												<!-- <span class="input-group-btn">
													<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
												</span> -->
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_status" class="control-label col-md-3">Status:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm" name="req_01_status" id="req_01_status" value="Approved" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_protocol" class="control-label col-md-3">Protocol #:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm" name="req_01_protocol" id="req_01_protocol" value="72342AC" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_exemption" class="control-label col-md-3">Exemption #:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm" name="req_01_exemption" id="req_01_exemption" value="888JJKDF" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_app_date" class="control-label col-md-3">Application date:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm uif-dateControl" name="req_01_app_date" id="req_01_app_date" value="8/30/2013" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_approval_date" class="control-label col-md-3">Approval date:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm uif-dateControl" name="req_01_approval_date" id="req_01_approval_date" value="8/30/2013" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
										<div class="form-group clearfix">
											<label for="req_01_exp_date" class="control-label col-md-3">Expiration date:</label>
											<div class="col-md-5 input-group">
												<input type="text" class="form-control input-sm uif-dateControl" name="req_01_exp_date" id="req_01_exp_date" value="9/1/2014" />
												<div class="helper-text">
													Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
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
			<button class="btn btn-primary" href="prop.attachments.proposal.php">Save and continue...</button>
		</div>
	</div>
</div>

<script type="text/javascript">
$('#compliance_2').hide();
$('#compliance_edit').hide();

$('#compliance_add').click(function(e) {
	e.preventDefault();
	$('#compliance_2').show();
});

$('#link_edit').click(function(e) {
	e.preventDefault();
	$('#compliance_1').hide();
	$('#compliance_edit').show();
});

$('#link_delete').click(function(e) {
	e.preventDefault();
	$('#compliance_1').fadeOut();
});

$('#link_save').click(function(e) {
	e.preventDefault();
	$('#compliance_edit').hide();
	$('#compliance_1').show();
});

$('#link_cancel').click(function(e) {
	e.preventDefault();
	$('#compliance_edit').hide();
	$('#compliance_1').show();
});
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>