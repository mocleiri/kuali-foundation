<?php
# Variables
$page = 'budget-nonpersonnel';
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
          <div class="box">
            <div class="boxHeader">
              <h3> Budget: Non-personnel </h3>
              <h4> Budget Overview <span>(Period 1) </span> </h4>
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
          <div class="box">
            <div class="boxHeader">
              <h4>Equipment </h4>
            </div>
          </div>
          <div class="box">
            <div class="boxHeader">
              <h4> Travel </h4>
            </div>
          </div>
          <div class="box">
            <div class="boxHeader">
              <h3>Participant Support </h3>
            </div>
          </div>
          <div class="box">
            <div class="boxHeader">
              <h4> Other Direct </h4>
            </div>
          </div>
          <!--<div class="box"> <div class="boxHeader">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
          
        </div>
      </div><div class="docControls"> <a href="prop.budget.personnel.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.budget.distincome.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
