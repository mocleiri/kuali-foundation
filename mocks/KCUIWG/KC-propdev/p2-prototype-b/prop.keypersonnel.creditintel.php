<?php
# Variables
$section = 'keypersonnel';
$page = 'keypersonnel-intelcredit';

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
					<h2>Intellectual Credit</h2>
				</div>

				<div class="section-content">
					<div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto"></div>
					<button class="btn btn-default">Edit allocation...</button>
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

<?php require_once( 'themes/kc/inc/footer.php' ); ?>