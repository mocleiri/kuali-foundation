<?php
# Variables
$page = 'personnel-credit';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/header.php' ) ?>
<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->

<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
             <div class="box">
          <div class="boxHeader ">
            <h3> Credit Allocation</h3>
          </div>
          
      
          <div class="boxContent expandTarget" style="" >
            <div class="tabbable  clearfix"> <!-- Only required for left/right tabs -->
              
              <ul class="nav nav-tabs">
                <li class="active"><a href="#intel" data-toggle="tab">Intellectual</a></li>
                <li ><a href="#fa" data-toggle="tab">F&amp;A Revenue</a></li>
              </ul>
              <div class="tab-content">
                <div class="tab-pane active" id="intel">
                
                
                
          
                  <div class="boxSubheader">
                    <h4>Intellectual Credit Split</h4>
                  </div>
                  
                  
                  
                  
                  
                  
                  
                 
 <div  id="chart1" style="height:300px; width:500px; margin-left:auto; margin-right:auto"></div>  <div style=" text-align:center; margin-bottom:30px"><a href="#" class="ajax-modal btn btn-small" data-backdrop="true" data-controls-modal="res-modal" data-keyboard="true" url="prop.keypersonnel.credit.frame.html">edit allocation</a>
 </div>
                </div>
                <div class="tab-pane " id="fa">
                  <h4>F&amp;A Revenue</h4>
                  <p>F&amp;A credit allocation would use the same user interaction method as intellectual allocation.</p>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                  <br>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div style=" padd12px; text-align:center">
          <a href="prop.keypersonnel.populated.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.access.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
      </div>
    </div>
  </form>
</div>


<!-- Modal -->

<div class="modal hide fade" id="addemployee"></div>
<div class="modal hide fade" id="addnonemployee"></div>
<div class="modal hide fade" id="deletepersonnel"></div>


<?php include( 'assets/inc/scripts.global.php' ) ?>

<script type="text/javascript" src="assets/js/jquery.jqplot.min.js"></script>


<script type="text/javascript" src="assets/js/jqplot.pieRenderer.min.js"></script>




<script type="text/javascript">
    $(document).ready(function () {
		
	
});


    $(".ajax-modal").live('click', function () {
        var url = $(this).attr('url');
        var modal_id = $(this).attr('data-controls-modal');
        $("#" + modal_id).load(url).modal('show');
    });
</script>

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