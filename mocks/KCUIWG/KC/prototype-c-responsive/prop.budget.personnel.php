<?php
# Variables
$page = 'budget-personnel';
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
          






<div class="span12 content"> <!--<div class="btn-group" style="margin-bottom:20px">
          <button class="btn btn-small">Budget Period 1</button>
          <button class="btn btn-small dropdown-toggle" data-toggle="dropdown"> <span class="caret"></span> </button>
          <ul class="dropdown-menu">
            <li> <a href="#">Budget Period 1</a></li>
            <li> <a href="#">Budget Period 2</a></li>
            <li> <a href="#">Budget Period 3</a></li>
            <li> <a href="#">Budget Period 4</a></li>
          </ul>
        </div>-->
        <div class="box"> <div class="boxHeader">
            <h3>Budget: Key Personnel</h3>
          </div>
          <div class="boxContent expandTarget" style="" >
            <div class="boxSubheader">
              <h4> Details</h4>
              <div class="boxControls"><a href="#" class="disclose-dl1">show</a></div>
            </div>
               <table class="table table-condensed table-hover">
              <thead>
                <tr>
                  <th >Person</th>
                  <th >Job Code</th>
                  <th >Appointment Type</th>
                  <th >Base Salary</th>
                  <th >Salary Effective Date</th>
                  <th ></th>
                </tr>
              </thead>
              <tbody>
                <tr>
                  <td>Webster, Ben</td>
                  <td><a href="#" id="jobcode" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Job Code">AA000</a></td>
                  <td><a href="#" id="apptype" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Appointment Type">12M Duration</a></td>
                  <td><a href="#" id="basesal" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Base Salary">87,000.00</a></td>
                  <td><a href="#" id="saleff" data-type="text" data-pk="1" data-url="/post" data-original-title="Enter Salary Effective Date">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
                <tr>
                  <td>Tatum, Art</td>
                  <td><a href="#">AA000</a></td>
                  <td><a href="#">12M  Duration</a></td>
                  <td><a href="#">69,000.00</a></td>
                  <td><a href="#">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
                <tr>
                  <td>Lee, Peggy</td>
                  <td><a href="#">AA000</a></td>
                  <td><a href="#">12M  Duration</a></td>
                  <td><a href="#">69,000.00</a></td>
                  <td><a href="#">01/01/2013</a></td>
                  <td><a href="#myModal" role="button" data-toggle="modal" class="btn btn-mini btn-danger"><i class="icon-white icon-minus"></i>Delete</a></td>
                </tr>
              </tbody>
            </table>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h4> Budget Overview <span> (Period 1)</span></h4>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h4>Personnel Detail <span> (Period 1)</span></h4>
          </div>
        </div>
        
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
        
      </div>










        </div> </div>
      




</div>
<div class="docControls"> <a href="prop.budget.rates.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.budget.nonpersonnel.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>




    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
