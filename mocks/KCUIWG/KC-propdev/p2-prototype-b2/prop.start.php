<?php
# Variables
$section = 'basics';
$page = 'basics-details';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
include( '_temp.select.filler.php' );
?>

<section id="main">
		
	<?php require_once( 'themes/kc/inc/uif-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

	<div class="container">
		<div class="row-temp-disabled">
			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper"></div>
			<div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">

				<div id="content" class="uif-page" tabindex="-1">
			
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Create a proposal</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend style="display:none">Enter any relevant details for this proposal</legend>
							
							<div class="form-group clearfix">
								<label for="proposal_type" class="control-label col-md-3">Proposal type:</label>
								<div class="col-md-5">
									<select name="proposal_type" id="proposal_type" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="4">Continuation</option>
										<option value="1">New</option>
										<option value="3">Renewal</option>
										<option value="2">Resubmission</option>
										<option value="5">Revision</option>
										<option value="6">Task Order</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="lead_unit" class="control-label col-md-3">Lead unit:</label>
								<div class="col-md-5">
									<select name="lead_unit" id="lead_unit" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="000001">000001 - University</option>
										<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
										<option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
										<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="activity_type" class="control-label col-md-3">Activity type:</label>
								<div class="col-md-5">
									<select name="activity_type" id="activity_type" class="form-control input-sm col-md-8 chzn">
										<option></option>
										<option value="4">Clinical Trial</option>
										<option value="9">Construction</option>
										<option value="7">Fellowship - Post-Doctoral</option>
										<option value="6">Fellowship - Pre-Doctoral</option>
										<option value="2">Instruction</option>
										<option value="3">Public Service</option>
										<option value="1">Research</option>
										<option value="8">Student Services</option>
										<option value="5">other</option>
									</select>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label class="control-label col-md-3">Project dates:</label>
								<div class="col-md-5">
									<div class="col-md-6 date">
										<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_start" id="project_start" placeholder="mm/dd/yyyy" />
										<label for="project_start" class="helper-text">Beginning on</label>
									</div>
									<div class="col-md-6 date">
										<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_end" id="project_end" placeholder="mm/dd/yyyy" />
										<label for="project_end" class="helper-text">Ending on</label>
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_code" class="control-label col-md-3">Sponsor code:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="sponsor_code" id="sponsor_code" placeholder="Who's sponsoring this project?" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="project_title" class="control-label col-md-3">Project title:</label>
								<div class="col-md-5">
									<textarea name="project_title" id="project_title" class="form-control input-sm" placeholder="Give this project a nice title"></textarea>
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="award_id" class="control-label col-md-3">Award ID:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="award_id" id="award_id" placeholder="Enter your award ID" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inst_proposal_id" class="control-label col-md-3">Original institutional ID:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="inst_proposal_id" id="inst_proposal_id" placeholder="Enter your original insitutional ID" />
									<div class="helper-text">
										Pellentesque sodales dolor vel augue egestas, ac ultrices lectus feugiat. Suspendisse potenti. Aliquam ac pretium.
									</div>
									<span class="input-group-btn">
										<a href="#" class="icon-search launch-modal" data-modal-page="modals/lookup.html" data-modal-height="500"></a>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="keywords" class="control-label col-md-3">Keywords:</label>
								<div class="col-md-5">
									<select name="keywords" id="keywords" class="form-control input-sm col-md-8 chzn" multiple>
										<?php get_options() ?>
									</select>
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
			<!-- <button class="btn btn-default">Save</button> -->
			<button class="btn btn-primary" href="prop.basics.details.php">Save and begin proposal</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>