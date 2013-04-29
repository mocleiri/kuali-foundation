<?php
# Variables
$page = 'index';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>

<!--<div class="responsiveCue">test</div>-->

<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <div class="row-fluid">
        <div class="docHeader clearfix">
          <div class="span12">
            <ul class="breadcrumb">
              <li><a href="index.php">Home</a> <span class="divider">/</span></li>
              <li class="active">KPME</li>
            </ul>
            <h2> KPME: <span>Time</span></h2>
          </div>
        </div>
        <div class="span12" style="padding-top:20px">
          <div class=" row-fluid">
            <div class="span3">
              <h3>Rules</h3>
              <ul>
                <li><a href="#">Clock Location</a></li>
                <li><a href="#">Daily Overtime</a></li>
                <li><a href="#">Department Lunch Deduction</a></li>
                <li><a href="#">Grace Period</a></li>
                <li><a href="#">Shift Differential</a></li>
                <li><a href="#">System Lunch</a></li>
                <li><a href="#">Time Collection</a></li>
                <li><a href="#">Time Sheet Initiate</a></li>
                <li><a href="#">Weekly Overtime</a></li>
              </ul>
            </div>
            <div class="span3">
              <h3>Leave</h3>
              <ul>
                <li><a href="#">Accural Category</a></li>
                <li><a href="#">Balance Transfer</a></li>
                <li><a href="#">Employee Override</a></li>
                <li><a href="#">Leave Adjustment</a></li>
                <li><a href="#">Leave Donation</a></li>
                <li><a href="#">Leave Payout</a></li>
                <li><a href="#">Leave Plan</a></li>
                <li><a href="#">System Scheduled Time Off</a></li>
              </ul>
            </div>
            <div class="span3">
              <h3>Inquiries</h3>
              <ul>
                <li><a href="#">Clock Log</a></li>
                <li><a href="#">Missed Punch</a></li>
                <li><a href="#">Time Block History</a></li>
                <li><a href="#">Time Block</a></li>
              </ul>
             
            </div><div class="span3">
              
              <h3>Tasks</h3>
              <ul>
                <li><a href="#">Delete Timesheet/Leave Calendar</a></li>
                <li><a href="#">Calculate Leave Accurals</a></li>
                <li><a href="#">Create Calendar Entry</a></li>
                <li><a href="#">Run Batch Job(s)</a></li>
              </ul>
            </div>
          </div>
          <div class="clearfix"></div>
        </div>
      </div>
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>

<!-- /container -->
</body>
</html>
