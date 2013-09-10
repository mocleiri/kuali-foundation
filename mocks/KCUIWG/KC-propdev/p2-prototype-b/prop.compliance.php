<?php
# Variables
$page = 'compliance';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container-fluid">
		
		<?php require_once( 'themes/kc/inc/doc-header.php' ); ?>

		<div class="row-temp-disabled">

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				<div class="section-title">
					<h2>Compliance</h2>
				</div>

				<div class="section-content">
					<div class="tab-title-container clearfix">
						<h4>Manage compliance requirements for this proposal.</h4>

			            <div class="page-controls">
			            	<div class="well">
			            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Add new</button>
			            	</div>
			            </div>
			        </div>
							
					<div class="entry-row clearfix" id="compliance_1">
						<div class="entry-row-internal">
							<table class="table table-condensed">
								<tr>
									<th width="30%">Type</th>
									<td>International Programs</td>
								</tr>
								<tr>
									<th>Status</th>
									<td>Approved</td>
								</tr>
								<tr>
									<th>Protocol #</th>
									<td>723671AC</td>
								</tr>
								<tr>
									<th>Exemption #</th>
									<td>888JJKDF</td>
								</tr>
								<tr>
									<th>Application date</th>
									<td>8/30/2013</td>
								</tr>
								<tr>
									<th>Appoval date</th>
									<td>8/30/2013</td>
								 </tr>
								 <tr>
								 	<th>Expiration date</th>
								 	<td>9/1/2014</td>
								 </tr>
							</table>
						</div>
						<div class="entry-row-actions">
							<div class="well">
								<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
						 		<button class="btn btn-link btn-danger">Delete</button>
							</div>
						</div>
					</div>

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

				<!-- // -->

			</div>

		</div>

	</div>

</section>

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