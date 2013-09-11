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

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>
			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
			
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Proposal Details</h2>
				</div>

				<div class="section-content">
					<div class="data">
						<div class="tab-title-container clearfix">
							<h4>Review the proposal details</h4>

				            <div class="page-controls">
				            	<div class="well">
				            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
				            	</div>
				            </div>
				        </div>

						<table  class="table table-condensed">
							<tbody>
								<tr>
									<th style="width:30%">Proposal type</th>
									<td>New</td>
								</tr>
								<tr>
									<th style="width:30%">Lead unit</th>
									<td>0000001 - University</td>
								</tr>
								<tr>
									<th>Activity type</th>
									<td>Clinical Trial</td>
								</tr>
								<tr>
									<th>Start date</th>
									<td>01/01/2014</td>
								</tr>
								<tr>
									<th>End date</th>
									<td>12/31/2014</td>
								</tr>
								<tr>
									<th>Sponsor code</th>
									<td>0123456789</td>
								</tr>
								<tr>
									<th>Project title</th>
									<td>This is just a test proposal</td>
								</tr>
								<tr>
									<th>Award ID</th>
									<td>477asd</td>
								</tr>
								<tr>
									<th>Original institutional ID</th>
									<td>999dnfa</td>
								</tr>
								<tr>
									<th>Keywords</th>
									<td>
										<select name="keywords" id="keywords" class="form-control input-sm col-md-8 chzn" multiple>
											<?php
											get_options();
											?>
										</select>
									</td>
								</tr>
							</tbody>
						</table>

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

					</div>
				</div>
				<!-- // -->

			</div>
		</div>

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>