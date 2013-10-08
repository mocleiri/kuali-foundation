<?php
# Variables
$section = 'basics';
$page = 'basics-search';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper" tabindex="-1"> <!-- Main content goes here -->

				<div class="section-title">
					<h3>Opportunity Search</h3>
				</div>

				<div class="alert alert-info">
					<h4><i class="icon-info-sign"></i> Grants.gov opportunity selected</h4>
					<p>You've chosen to prepare a proposal being sent to Grants.gov so no further information is required on this screen. Click "Save and continue" below to continue working on this proposal.</p>
				</div>
                <!-- <div id="oppsearch-tabs" class="well">
                    <button class="btn btn-default launch-modal" data-modal-page="modal/lookup-oppsearch.html">Find an opportunity...</button>
                </div>

				<div class="section-content hidden">
					<form action="#" method="post" class="">
						<fieldset>
							<legend>Opportunity actions</legend>
							<div class="form-group clearfix">
								<button class="btn btn-default modal" data-page="page-name-here.html">Load new opportunity...</button>
							</div>
						</fieldset>
					</form>
				</div> -->

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
		<div class="btn-row-page-action">
			<?php
			if ($alt && file_exists('prop.basics.sponsor-alt.php')) {
				echo '<button href="prop.basics.sponsor-alt.php" class="btn btn-default">Back</button>';
			} else {
				echo '<button href="prop.basics.sponsor.php" class="btn btn-default">Back</button>';
			}
			?>
			<button class="btn btn-default">Save</button>
			<?php
			if ($alt && file_exists('prop.basics.deliveryinfo-alt.php')) {
				echo '<button href="prop.basics.deliveryinfo-alt.php" class="btn btn-primary">Save and continue</button>';
			} else {
				echo '<button href="prop.basics.deliveryinfo.php" class="btn btn-primary">Save and continue</button>';
			}
			?>
			
		</div>
		<!-- // -->

        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>