<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-facredit';

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
						<h2>F&amp;A Credit</h2>
					</div>

					<div class="section-content">
						<div class="tab-title-container clearfix">
							<h4>Chart loading...</h4>
				        </div>

						<div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto">

						</div>

				        <div class="well">
		            		<button class="btn btn-default launch-modal" data-modal-page="modal/lookup.html" data-modal-height="500">Edit</button>
		            	</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</section>

<script type="text/javascript">
// $(document).ready(function(){
// 	var data = [ ['Durkin, T (BL-CEDP)', 25],['Durkin, T (UA-VPIT)', 25], ['Hopf, J', 25], ['Johnson, N', 25] ];
// 	var plot1 = jQuery.jqplot('chart1', [data], { 
// 		seriesDefaults: {
// 			renderer: jQuery.jqplot.PieRenderer, rendererOptions: {
// 				showDataLabels: true
// 			}
// 		}, 
// 		legend: {
// 			show:true,
// 			location: 'e'
// 		}
// 	}
// );
// });
</script>

<div class="page-controls clearfix">
	<div class="page-actions">
		<div class="well"></div>
	</div>

	<div class="page-navigation">
		<div class="well">
			<button class="btn btn-default">Save</button>
			<button class="btn btn-primary" href="prop.compliance.php">Save and continue...</button>
		</div>
	</div>
</div>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>