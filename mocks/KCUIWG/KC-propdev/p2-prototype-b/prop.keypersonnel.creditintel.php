<?php
# Variables
$page = 'keypersonnel-intelcredit';

# Includes
require_once( 'kc/inc/head.php' );
require_once( 'kc/inc/nav.php' );
require_once( 'kc/inc/toolbar.php' );
?>

<section id="main">
		
	<?php require_once( 'kc/inc/doc-header.php' ); ?>

	<div class="container">

		<div class="row">

			<div class="col-lg-3">
				<?php require_once( 'kc/inc/doc-subnav.php' ); ?>
			</div>

			<div class="col-lg-9">
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

	</div>

	<!-- Button row -->
	<div class="button-row">
		<div class="container">
			<div class="row">
				<button class="btn btn-default">Save</button>
				<button class="btn btn-primary">Save and continue...</button>
			</div>
		</div>
	</div>
	<!-- // -->

</section>

<script type="text/javascript">
$(document).ready(function(){
	var data = [ ['Durkin, T (BL-CEDP)', 25],['Durkin, T (UA-VPIT)', 25], ['Hopf, J', 25], ['Johnson, N', 25] ];
	var plot1 = jQuery.jqplot('chart1', [data], { 
		seriesDefaults: {
			renderer: jQuery.jqplot.PieRenderer, rendererOptions: {
				showDataLabels: true
			}
		}, 
		legend: {
			show:true,
			location: 'e'
		}
	}
);
});
</script>

<?php require_once( 'kc/inc/footer.php' ); ?>