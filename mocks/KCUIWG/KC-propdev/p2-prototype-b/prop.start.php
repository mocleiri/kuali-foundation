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

		
	<?php require_once( 'themes/kc/inc/uif-unifiedPageHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>
<div class="container-fluid"><div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper"></div>
			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
			
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
										<option value="4">Continuation</option>
										<option value="1">New</option>
										<option value="3">Renewal</option>
										<option value="2">Resubmission</option>
										<option value="5">Revision</option>
										<option value="6">Task Order</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="lead_unit" class="control-label col-md-3">Lead unit:</label>
								<div class="col-md-5">
									<select name="lead_unit" id="lead_unit" class="form-control input-sm col-md-8 chzn">
										<option value="000001">000001 - University</option>
										<option value="BL-IIDC">BL-IIDC - IND INST ON DISABILITY/COMMNTY asdf asdf asdfasdf asdf asdf </option>
										<option value="IN-CARD">IN-CARD - CARDIOLOGY</option>
										<option value="IN-CARR">IN-CARR - CARDIOLOGY RECHARGE CTR</option>
									</select>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="activity_type" class="control-label col-md-3">Activity type:</label>
								<div class="col-md-5">
									<select name="activity_type" id="activity_type" class="form-control input-sm col-md-8 chzn">
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
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="project_start" class="control-label col-md-3">Start date:</label>
								<div class="col-md-3 input-group">
									<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_start" id="project_start" placeholder="mm/dd/yyyy" />
									<!-- <span class="input-group-btn add-on">
										<button class="btn btn-default input-sm">#</button>
									</span> -->
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="project_end" class="control-label col-md-3">End date:</label>
								<div class="col-md-3 input-group">
									<input type="text" size="12" class="form-control input-sm uif-dateControl" name="project_end" id="project_end" placeholder="mm/dd/yyyy" />
									<!-- <span class="input-group-btn add-on">
										<button class="btn btn-default input-sm">#</button>
									</span> -->
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="sponsor_code" class="control-label col-md-3">Sponsor code:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="sponsor_code" id="sponsor_code" />
									<span class="input-group-btn">
										<button name="sponsor_code_search" id="sponsor_code_search" class="btn btn-default input-sm">Search</button>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="project_title" class="control-label col-md-3">Project title:</label>
								<div class="col-md-5">
									<textarea name="project_title" id="project_title" class="form-control input-sm"></textarea>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="award_id" class="control-label col-md-3">Award ID:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="award_id" id="award_id" />
									<span class="input-group-btn">
										<button name="sponsor_code_search" id="sponsor_code_search" class="btn btn-default input-sm">Search</button>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="inst_proposal_id" class="control-label col-md-3">Original institutional ID:</label>
								<div class="col-md-5 input-group">
									<input type="text" class="form-control input-sm" name="inst_proposal_id" id="inst_proposal_id" />
									<span class="input-group-btn">
										<button name="sponsor_code_search" id="sponsor_code_search" class="btn btn-default input-sm">Search</button>
									</span>
								</div>
							</div>

							<div class="form-group clearfix">
								<label for="keywords" class="control-label col-md-3">Keywords:</label>
								<div class="col-md-5">
									<select name="keywords" id="keywords" class="form-control input-sm col-md-8 chzn" multiple>
										<?php
										get_options();
										?>
									</select>
								</div>
							</div>
						</fieldset>
					</form>
				</div>
				<!-- // -->

			</div>
		</div>

		<!-- Button row -->
		<div class="btn-row-page-action">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary" onclick="location.href='prop.basics.details.php'">Save and continue...</button>
			</div>
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>