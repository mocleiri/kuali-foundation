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
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->

				
					<h3>Key Personnel</h3>
				

				
					<form action="#" method="post" class="">
						<fieldset>
							<legend>Search for and add key personnel</legend>

							<div class="info-message">
								<div class="alert alert-success">
									Great, Edward Haskell was successfully added!
								</div>
							</div>

							<p>Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, and any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).

							<div class="key-personnel">
								<div class="panel-group" id="accordion">
									<div class="panel">
										<div class="panel-heading">
											<h6 class="panel-title">
												<a class="accordion-toggle collapsed" data-toggle="collapse" data-parent="#accordion" href="#prop1">Edward Haskell (haskelldw)</a>
											</h6>
										</div>
										<div id="prop1" class="panel-collapse collapse">
											<div class="panel-body">
												Stuff here...
											</div>
										</div>
									</div>
								</div>
							</div>

							<div class="form-group clearfix">
								<button class="btn btn-default launch-modal" data-modal-page="modal/modal-addpersonnel/start.html">+ Add Personnel</button>
							</div>
						</fieldset>
					</form>
				

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
	<div class="btn-row-page-action">
		<div class="container-fluid">
			<div class="row"> <button  onclick="location.href='filenamehereeeeeeeeee'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='filenamehereeeeeeeeee'" class="btn btn-primary">Save and continue</button>
			</div>
		</div>
	</div>
	<!-- // -->
        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>









<section id="main"> 	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?><div class="container-fluid">

		

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed">
			

			</div>

		

	</div>

	

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>