<?php
# Variables
$page = 'personnel-creditintel';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">   

<?php require_once( 'assets/inc/document-header.php' ) ?>
      
      
      <div class="leftnavContent">
      <?php require_once( 'assets/inc/document-nav.php' ) ?>
      
      <div id="content" role="application">
        <div class="row-fluid">
          









 <div class="span12 content"> <h3>Intellectual Credit</h3>
          <div class=" formfields">
            <div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto"></div>
            <div style=" text-align:center; margin-bottom:30px"><a href="#" class="ajax-modal btn btn-small" data-backdrop="true" data-controls-modal="res-modal" data-keyboard="true" url="prop.keypersonnel.credit.frame.html">edit allocation</a> </div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>





















        </div> </div>
      




</div>
<div class="docControls"> <a href="prop.keypersonnel.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.keypersonnel.creditfa.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>




    </div>
  </div>
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
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
