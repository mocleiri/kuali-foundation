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
        <div class="box"> <div class="boxHeader">
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
        <div class="box"> <div class="boxHeader">
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
        <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> 
        <div style=" padd12px; text-align:center">
          <a href="prop.budget.summary.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.budget.rates.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
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