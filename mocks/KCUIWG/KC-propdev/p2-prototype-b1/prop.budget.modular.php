<?php
# Variables
$section = 'budget';
$page = 'budget-modular';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">

	<div class="container-fluid"> 	<?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
    <?php require_once( 'themes/kc/inc/uif-documentFunctions.php' ); ?>

			<div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
				<?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
			</div>

			  <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper closed"><div id="content" class="uif-page" tabindex="-1">
				<!-- Main content goes here -->

				
					<h3>Budget Modular</h3>
				

				<div class="section-content">
					<form action="#" method="post" class="form-horizontal">
						<fieldset>
							<legend>Coming soon</legend>
							
						</fieldset>
					</form>
				</div>

				<!-- // -->

			</div></div>

		

		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.budget.distincome.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.budget.actions.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->

	</div>

</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>