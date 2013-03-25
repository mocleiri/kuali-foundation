<?php
# Variables
$page = 'review-specialreview';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        
        <div class="box"> <div class="boxHeader ">
            <h3>Special Review (Proposal Number: 00015753)</h3>
          </div>
          <div class="boxContent " style="min-height:200px" >
            
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Name of Review</th>
                  <th>Approval</th>
                  <th>Protocol Number</th>
                  <th>Application Date</th>
                  <th>Approval Date</th>
                  <th style="width: 35px;"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>International Programs</td>
                  <td>Pending</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
                <tr>
                  <td>Animal Usage</td>
                  <td>Pending</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td>&nbsp;</td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
              </tbody>
            </table>
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.specialreview.jpg" alt="temp screenshot" style="width:716px; height:92px;" />
            </div>-->
          
          </div>
        </div>

        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        
        <div style=" padd12px; text-align:center">
          <a href="prop.review.attachments.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.review.print.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>