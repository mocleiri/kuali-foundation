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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->

				<div class="section-title">
					<h3>Opportunity Search</h3>
				</div>
                <div id="oppsearch-tabs">
                    <a class="various fancybox.ajax btn btn-sm btn-default" data-fancybox-type="ajax" href="modal/lookup-oppsearch.html">Load New...</a>
                    <!--<a data-toggle="modal" data-target="#myModal" href="modal/lookup-oppsearch.html" class="btn btn-sm btn-default">Load New...</a>-->
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
				</div>

				<!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter" style="position:fixed; left: 0; bottom: 0px; width:100%"> 
          
         
		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.basics.details.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.basics.deliveryinfo.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->

        </div>
      </div>
    </div>
  </div>
</section>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>





