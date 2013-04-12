<?php
# Variables
$page = 'personnel-creditfa';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container"> <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
          <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>
        <div class="span10">
          <h3>F &amp; A Credit</h3>
          <div class=" formfields">
          
          
          
          
          
          <div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto"></div>
                  <div style=" text-align:center; margin-bottom:30px"><a href="#" class="ajax-modal btn" data-backdrop="true" data-controls-modal="res-modal" data-keyboard="true" url="prop.keypersonnel.credit.frame.html"><i class="icon icon-edit"></i> Edit allocation</a> </div>
                
          
          
          
          
          
          
          
          
          
            
          </div>
          <div class="clearfix"></div>
        </div>
      </div>     <div class="docControls"> <a href="prop.keypersonnel.creditintel.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.compliance.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container --> 

<script type="text/javascript">
$(document).ready(function(){
  var data = [
    ['Durkin, T (BL-CEDP)', 25],['Durkin, T (UA-VPIT)', 25], ['Hopf, J', 25], 
    ['Johnson, N', 25]
  ];
  var plot1 = jQuery.jqplot ('chart1', [data], 
    { 
      seriesDefaults: {
        // Make this a pie chart.
        renderer: jQuery.jqplot.PieRenderer, 
        rendererOptions: {
          // Put data labels on the pie slices.
          // By default, labels show the percentage of the slice.
          showDataLabels: true
        }
      }, 
      legend: { show:true, location: 'e' }
    }
  );
});
</script>
</body>
</html>
