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

		
	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
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

				            <!-- <div class="page-controls">
				            	<div class="well">
				            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
				            	</div>
				            </div> -->
				        </div>

						<table  class="table table-condensed">
							<tbody>
								<tr>
									<th style="width:30%">Proposal type</th>
									<td><div class="uif-switchme"><span>New</span></div></td>
								</tr>
								<tr>
									<th style="width:30%">Lead unit</th>
									<td><div class="uif-switchme"><span>0000001 - University</span></div></td>
								</tr>
								<tr>
									<th>Activity type</th>
									<td><div class="uif-switchme"><span>Clinical Trial</span></div></td>
								</tr>
								<tr>
									<th>Start date</th>
									<td><div class="uif-switchme uif-switchme-date"><span>01/01/2014</span></div></td>
								</tr>
								<tr>
									<th>End date</th>
									<td><div class="uif-switchme uif-switchme-date"><span>12/31/2014</span></div></td>
								</tr>
								<tr>
									<th>Sponsor code</th>
									<td><div class="uif-switchme"><span>0123456789</span></div></td>
								</tr>
								<tr>
									<th>Project title</th>
									<td><div class="uif-switchme"><span>This is just a test proposal</span></div></td>
								</tr>
								<tr>
									<th>Award ID</th>
									<td><div class="uif-switchme"><span>477asd</span></div></td>
								</tr>
								<tr>
									<th>Original institutional ID</th>
									<td><div class="uif-switchme"><span>999dnfa</span></div></td>
								</tr>
								<tr>
									<th>Keywords</th>
									<td><div class="uif-switchme uif-switchme-select"><span>hello,there,how,are,you,today</span></div>
										<!-- <select name="keywords" id="keywords" class="form-control input-sm col-md-8 chzn" multiple>
											<?php
											get_options();
											?>
										</select> -->
									</td>
								</tr>
							</tbody>
						</table>

					</div>
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
			<button class="btn btn-default">Save</button>
			<button class="btn btn-primary">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>