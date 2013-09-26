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
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->

				
					<h3>Intellectual Credit</h3>
				

				
					<div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto"></div>
					<button class="btn btn-default">Edit allocation...</button>
				

				<!-- // -->

        <div class="uif-stickyFooter uif-stickyButtonFooter" style="position:fixed; left: 0; bottom: 0px; width:100%"> 
        
		<!-- Button row -->
		<div class="btn-row-page-action"> <button  onclick="location.href='prop.keypersonnel.start.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-primary">Save and continue</button>
			
		</div>
		<!-- // -->

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

<?php require_once( 'themes/kc/inc/footer.php' ); ?>
