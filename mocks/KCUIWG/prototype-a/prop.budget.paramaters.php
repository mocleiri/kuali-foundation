<?php
# Variables
$page = 'budget-parameters';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">
        <ul class="breadcrumb">
          <li class="active"><a href="prop.budget.php">Budget Home</a> <span class="divider">/</span></li>
          <li >Budget 1</li>
        </ul> <ul class="nav nav-tabs page">
          <li><a href="prop.budget.summary.php">Summary</a></li>
          <li  class=" active"><a href="prop.budget.paramaters.php">Parameters</a></li>
          <li><a href="prop.budget.rates.php">Rates</a></li>
          <li><a href="prop.budget.personnel.php">Personnel</a></li>
          <li><a href="prop.budget.nonpersonnel.php">Non-personnel</a></li>
          <li><a href="prop.budget.distincome.php">Distribution &amp; Income</a></li>
          <li><a href="prop.budget.modular.php">Modular Budget</a></li>
          <li><a href="prop.budget.actions.php">Budget Actions</a></li>
        </ul>
        <div class="box"> <div class="boxHeader expandControl">
            <h3> Budget Overview</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:" >
            <dl class="table-display" style="margin-top:;">
              <dt>Project Start Date</dt>
              <dd>01/31/2013</dd>
              <dt>Project End Date</dt>
              <dd>01/31/2016</dd>
              <dt>Total Direct Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Budget Status</dt>
              <dd>incomplete</dd>
              <dt>Final?</dt>
              <dd>no</dd>
              <dt>Off Campus?</dt>
              <dd>yes</dd>
              <dt>Modular Budget?</dt>
              <dd>yes</dd>
              <dt>Residual Funds</dt>
              <dd>0.00</dd>
              <dt>Total Cost Limit</dt>
              <dd>0.00</dd>
              <dt>Unrecovered F &amp; A Rate Type</dt>
              <dd>MTDC</dd>
              <dt>F&amp;A Rate Type</dt>
              <dd>MTDC</dd>
              <dt>Submit Cost Sharing?</dt>
              <dd>yes</dd>
              
            </dl>
            <div class="clearfix"></div>
          </div>
        </div>
        <div class="box"> <div class="boxHeader expandControl">
            <h3> Budget Periods &amp; Totals</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:" >
            <div class="boxSubheader">
              <h4> Periods</h4>
              <div class="boxControls"><a href="#" class="disclose-dl1">show</a></div>
            </div>
            <div class="boxSubheader">
              <h4> Totals</h4>
              <div class="boxControls"><a href="#" class="disclose-dl1">show</a></div>
            </div>
          </div>
        </div>
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
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