<?php
# Variables
$page = 'questions';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container">
		
		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row">

			<div class="col-md-3">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-md-9" id="content" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Questionnaire</h2>
				</div>

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<div class="clearfix">
							<div class="col-md-5">
								<h6>Question</h6>
							</div>
							<div class="col-md-1">
								<h6>Answer</h6>
							</div>
							<div class="col-md-2">
								<h6>Review date</h6>
							</div>
							<div class="col-md-4">
								<h6>Explanation</h6>
							</div>
						</div>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Will new space or remodeling be required for this project?</legend>
							</div>
							<div class="col-md-1">
								<label for="yes1" class="radio"><input type="radio" name="q1" id="yes1">Yes</label>
								<label for="no1" class="radio"><input type="radio" name="q1" id="no1">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e1" class="off-screen">Explanation:</label><input type="text" name="e1" id="e1" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Patents: Answer "N/A" if there are No patents. Answer "Yes" if all patents have been reported. Answer "No" if there are patents that have Not been reported.</legend>
							</div>
							<div class="col-md-1">
								<label for="yes2" class="radio"><input type="radio" name="q2" id="yes2">Yes</label>
								<label for="no2" class="radio"><input type="radio" name="q2" id="no2">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e2" class="off-screen">Explanation:</label><input type="text" name="e2" id="e2" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Is the principal investigator changing with this application? If so, please enter last and first names of previous investigator in explanation. Please enter LAST NAME, FIRST NAME.</legend>
							</div>
							<div class="col-md-1">
								<label for="yes3" class="radio"><input type="radio" name="q3" id="yes3">Yes</label>
								<label for="no3" class="radio"><input type="radio" name="q3" id="no3">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e3" class="off-screen">Explanation:</label><input type="text" name="e3" id="e3" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">For U.S. Department of Education, check "Yes" or "No" only if assistance is being requested under a program that gives special consideration to Novice applicants. Otherwise, check "Not Applicable".</legend>
							</div>
							<div class="col-md-1">
								<label for="yes4" class="radio"><input type="radio" name="q4" id="yes4">Yes</label>
								<label for="no4" class="radio"><input type="radio" name="q4" id="no4">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e4" class="off-screen">Explanation:</label><input type="text" name="e4" id="e4" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Does this project have an international connection: that is, collaboration with internationally-based colleagues or institutions; significant activity outside the United States; or pertain topically to countries, regions or populations outside the United States? If so, please indicate in the explanation field the countries with which it is connected. (Used by the International Affairs Office.)</legend>
							</div>
							<div class="col-md-1">
								<label for="yes5" class="radio"><input type="radio" name="q5" id="yes5">Yes</label>
								<label for="no5" class="radio"><input type="radio" name="q5" id="no5">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e5" class="off-screen">Explanation:</label><input type="text" name="e5" id="e5" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Historical Sites Are Affected</legend>
							</div>
							<div class="col-md-1">
								<label for="yes6" class="radio"><input type="radio" name="q6" id="yes6">Yes</label>
								<label for="no6" class="radio"><input type="radio" name="q6" id="no6">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e6" class="off-screen">Explanation:</label><input type="text" name="e6" id="e6" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
							</div>
						</fieldset>

						<fieldset class="fieldset-horizontal clearfix">
							<div class="col-md-5">
								<legend class="legend-normal">Does this project have an actual or potential impact on the environment?</legend>
							</div>
							<div class="col-md-1">
								<label for="yes7" class="radio"><input type="radio" name="q7" id="yes7">Yes</label>
								<label for="no7" class="radio"><input type="radio" name="q7" id="no6">No</label>
							</div>
							<div class="col-md-2">
								<p>07/15/2013</p>
							</div>
							<div class="col-md-4 input-group">
								<label for="e7" class="off-screen">Explanation:</label><input type="text" name="e7" id="e7" class="form-control input-sm" />
								<span class="input-group-btn">
									<button class="btn btn-default input-sm">View</button>
								</span>
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
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>