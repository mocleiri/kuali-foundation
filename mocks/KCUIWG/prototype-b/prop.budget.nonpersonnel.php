<?php
# Variables
$page = 'budget-nonpersonnel';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <div class="box"> <div class="boxHeader">
            <h3> Budget Overview <span>(Period 1) </span>         </h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >
            <div class="boxSubheader">
              <h4>Applicant Organization</h4>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Period 1 Start Date</dt>
              <dd>01/01/2013</dd>
              <dt>Period 1 End Date</dt>
              <dd>01/01/2014</dd>
              <dt>Direct Cost</dt>
              <dd>0.00</dd>
              <dt>F&amp;A Cost</dt>
              <dd>0.00</dd>
              <dt>Unrecovered F&amp;A</dt>
              <dd>0.00</dd>
              <dt>Total Sponsor Cost</dt>
              <dd>0.00</dd>
              <dt>Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Total Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Direct Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Total Direct Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Cost Sharing</dt>
              <dd>0.00</dd>
            </dl>
            <div class="clearfix"></div>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3>Equipment </h3>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3> Travel </h3>
          </div>
        </div> <div class="box"> <div class="boxHeader">
            <h3>Participant Support </h3>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3> Other Direct </h3>
          </div>
        </div>
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
        <div style=" padd12px; text-align:center">
          <a href="prop.budget.personnel.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.budget.distincome.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>


  <script type="text/javascript">
    $(document).ready(function () {
  
  
  
  $(".disclose-dl1").toggle(

        function () {
            $("#dl1").slideDown(600);
            $(".disclose-dl1").html("hide");
        },

        function () {
            $("#dl1").slideUp(600);
            $(".disclose-dl1").html("show");
        });
  
	
});
  </script>

</body>
</html>