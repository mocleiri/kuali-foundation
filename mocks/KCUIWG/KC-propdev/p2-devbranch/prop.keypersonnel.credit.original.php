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
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> 	<!-- Main content goes here -->

				   
        <h3 class="pull-left"> Credit Allocation</h3>
        
        
        <a href="#" class="btn btn-xs btn-default pull-right" style="margin-top:22px;"> <span aria-hidden="true" class="icon-refresh"></span> Refresh View</a>
<table class="table table-condensed credit-allocation">
          <tbody>
         
            <tr>
              <th width="20.0%">&nbsp;</th>
              <th width="20.0%">Recognition</th>
              <th width="20.0%">Responsibility</th>
              <th width="20.0%">Space</th>
              <th width="20.0%">Financial</th>
            </tr>
      
          <tr class="active">
            <td ><strong> Edward H Haskell </strong></td>
            <td><strong>
              <input type="text" tabindex="301" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="302" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="303" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="304" value="0.00" title="Credit">
              </strong></td>
          </tr>
          <tr>
            <td class="text-muted" >BL-IIDC - IND INST ON DISABILITY/COMMNTY</td>
            <td><input type="text" tabindex="305" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="306" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="307" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="308" value="0.00" title="Credit"></td>
          </tr>
          <tr>
            <td class="text-muted" >000001 - University</td>
            <td><input type="text" tabindex="309" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="310" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="311" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="312" value="0.00" title="Credit"></td>
          </tr>
          <tr>
            <td >Unit Total:</td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
          </tr>
          <tr class="active">
            <td ><strong> Ward Cleaver </strong></td>
            <td><strong>
              <input type="text" tabindex="314" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="315" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="316" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="317" value="0.00" title="Credit">
              </strong></td>
          </tr>
          <tr>
            <td class="text-muted">000001 - University</td>
            <td><input type="text" tabindex="318" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="319" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="320" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="321" value="0.00" title="Credit"></td>
          </tr>
          <tr> <td >Unit Total:</td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
          </tr>
          <!-- <tr class="active" >
            <td ><strong>June Cleaver </strong></td>
            <td><strong>
              <input type="text" tabindex="323" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="324" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="325" value="0.00" title="Credit">
              </strong></td>
            <td><strong>
              <input type="text" tabindex="326" value="0.00" title="Credit">
              </strong></td>
          </tr>
          <tr>
            <td class="text-muted" >000001 - University</td>
            <td><input type="text" tabindex="327" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="328" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="329" value="0.00" title="Credit"></td>
            <td><input type="text" tabindex="330" value="0.00" title="Credit"></td>
          </tr> -->
          <tr> <td >Unit Total:</td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
          </tr>
          <!-- tr>
            <td colspan="5" >Totals</td>
          </tr> -->
          <tr class="active">
            <td ><strong>Investigator Total: </strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
            <td><strong>0.00</strong></td>
          </tr>
          </tbody>
          
        </table>
                    
	<!--			
<ul class="nav nav-tabs" id="myTab">
  <li class="active"><a href="#home">Recognition</a></li>
  <li><a href="#profile">Responsibility</a></li>
  <li><a href="#messages">Space</a></li>
  <li><a href="#settings">Financial</a></li>
</ul>

<div class="tab-content">
  <div class="tab-pane active" id="home">Pie or bar chart for Recognition goes here</div>
  <div class="tab-pane" id="profile">Pie or bar chart for Responsibility goes here</div>
  <div class="tab-pane" id="messages">Pie or bar chart for Space goes here</div>
  <div class="tab-pane" id="settings">Pie or bar chart for Financial goes here</div>
</div>
-->

				
			

				<!-- // -->

        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row --> <div class="container btn-row-page-action"> <button  onclick="location.href='prop.keypersonnel.start.php'" class="btn btn-default">Back</button>
<button class="btn btn-default">Save</button>
      <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-primary">Save and Continue</button>
			
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



<script>
$('#myTab a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})
</script>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>
