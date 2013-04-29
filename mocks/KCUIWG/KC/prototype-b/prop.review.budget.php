<?php
# Variables
$page = 'review-budget';
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
            <h3>Budget Summary</h3>
          </div>
          <div class="boxContent " style="min-height:500px" >
            
            <div class="boxSubheader">
              <h4>Budget Periods</h4>
              <!--<div class="boxControls"><a href="#" class="disclose-dl1">edit</a></div>-->
            </div>
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th style="width:50%">&nbsp;</th>
                  <th nowrap style="text-align:center" >Period 1</th>
                  <th nowrap style="text-align:center" >Period 2</th>
                  <th nowrap style="text-align:center" >Period 3</th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <th>Start Date</th>
                  <td style="text-align:left">02/01/2013</td>
                  <td style="text-align:left">08/01/2013</td>
                  <td style="text-align:left">08/01/2014</td>
                </tr>
                <tr>
                  <th>End Date</th>
                  <td style="text-align:left">07/31/2013</td>
                  <td style="text-align:left">07/31/2014</td>
                  <td style="text-align:left">07/31/2015</td>
                </tr>
                <tr>
                  <th>Direct Cost</th>
                  <td style="text-align:right">$67,306.36</td>
                  <td style="text-align:right">$106,132.15</td>
                  <td style="text-align:right">$42,859.88</td>
                </tr>
                <tr>
                  <th>Indirect Cost</th>
                  <td style="text-align:right">$34,891.56</td>
                  <td style="text-align:right">$59,434.01</td>
                  <td style="text-align:right">$24,001.53</td>
                </tr>
                <tr>
                  <th>Under Recovery</th>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                </tr>
                <tr>
                  <th>Cost Sharing</th>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                  <td style="text-align:right">$0.00</td>
                </tr>
                <tr>
                  <th>Total Cost</th>
                  <td style="text-align:right">$102,197.92</td>
                  <td style="text-align:right">$165,566.16</td>
                  <td style="text-align:right">$66,861.41</td>
                </tr>
              </tbody>
            </table>
            
            <div class="boxSubheader">
              <h4>Budget Reports</h4>
              <!--<div class="boxControls"><a href="#" class="disclose-dl1">edit</a></div>-->
            </div>
            <table class="table table-striped table-bordered table-condensed">
              <thead>
                <tr>
                  <th>Report Title</th>
                  <th>Include Comments</th>
                  <th style="width: 35px;"></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Budget Summary by Period</td>
                  <td><label class="checkbox"><input type="checkbox" value="true" name="group"></td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
                <tr>
                  <td>Cost Sharing Summary by Period</td>
                  <td><label class="checkbox"><input type="checkbox" value="true" name="group"></td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
                <tr>
                  <td>Cumulative Budget</td>
                  <td><label class="checkbox"><input type="checkbox" value="true" name="group"></td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
                <tr>
                  <td>Industrial Budget by Period</td>
                  <td><label class="checkbox"><input type="checkbox" value="true" name="group"></td>
                  <td><a href="#" class="btn btn-mini btn-inverse" role="button" data-toggle="modal">View</a></td>
                </tr>
              </tbody>
            </table>
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.budget.jpg" alt="temp screenshot" style="width:712px; height:221px;" />
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
          <a href="prop.review.persons.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.review.attachments.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
        
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>