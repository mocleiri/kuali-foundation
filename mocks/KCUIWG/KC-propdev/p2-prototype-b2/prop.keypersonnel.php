<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-start';

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
						<h2>Key Personnel</h2>
					</div>

					<div class="section-content">
						<div class="tab-title-container clearfix">
							<div class="info-message">
								<div class="alert alert-success">
									Great, Edward Haskell was successfully added!
								</div>
							</div>
			        	</div>

						<div id="personnel-tabs">
							<div class="panel-group" id="accordion1">
								<div class="panel panel-default">
									
									<div class="panel-heading">
										<div class="row">
											<div class="col-md-6">
												<h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"> <span aria-hidden="true" class="icon-caret-down"></span> Edward H Haskell </a></h4>
											</div>
											<div class="col-md-6">
												<a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a>
											</div>
										</div>
									</div>

									<div id="collapse2" class="panel-collapse collapse">
										<div class="panel-body">
											<table class="table table-collapsed">
												<tr>
													<th width="25%">Person role</th>
													<td>PI</td>
												</tr>
												<tr>
													<th>Full name</th>
													<td>Edward R. Haskell</td>
												</tr>
												<tr>
													<th>Username</th>
													<td>erhaskell</td>
												</tr>
												<tr>
													<th>Email address</th>
													<td>erhaskell@email.com</td>
												</tr>
												<tr>
													<th>Office phone</th>
													<td>321-321-1214</td>
												</tr>
												<tr>
													<th>Office fax</th>
													<td>321-321-1121</td>
												</tr>
												<tr>
													<th>Pager</th>
													<td>321-333-1656</td>
												</tr>
												<tr>
													<th>Mobile</th>
													<td>321-321-1221</td>
												</tr>
												<tr>
													<th>Primary title</th>
													<td>Acme Primary Title</td>
												</tr>
												<tr>
													<th>Directory title</th>
													<td>Acme Directory Title</td>
												</tr>
												<tr>
													<th>Home unit</th>
													<td>UA-VPIT</td>
												</tr>
												<tr>
													<th>Division</th>
													<td>University Administration</td>
												</tr>
												<tr>
													<th>eRA Commons username</th>
													<td>erhaskell</td>
												</tr>
												<tr>
													<th>Office address</th>
													<td>Kuali Foundation, 1135 Kuali Drive, Kuali, KU</td>
												</tr>
												<tr>
													<th>Country</th>
													<td>United States</td>
												</tr>
												<tr>
													<th>Percentage effort</th>
													<td>50%</td>
												</tr>
												<tr>
													<th>Faculty</th>
													<td>-</td>
												</tr>
											</table>
										</div>
									</div>
								</div>
							</div>

							<br />
					        <div class="well">
			            		<button class="btn btn-default launch-modal" data-modal-page="modal/modal-addpersonnel/start.html" data-modal-height="500">Add personnel</button>
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
			<button class="btn btn-primary" href="prop.keypersonnel.creditintel.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>