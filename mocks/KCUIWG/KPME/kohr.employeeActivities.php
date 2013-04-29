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
             
              <li class="active">KOHR</li>
            </ul>
            <h2> KOHR: <span>Employee Activities</span></h2>
          </div>
        </div>
        <div class="span12" style="padding-top:20px">
          <div class=" row-fluid">
            <div class="span4">
              <ul>
                <li><a href="#">Administrative Titles</a></li>
                <li><a href="#">Demote Employee</a></li>
                <li><a href="#">Hire Employee</a></li>
                <li><a href="#">Leave of Absence</a></li>
                <li><a href="#">Maintain Funding</a></li>
                <li><a href="#">Maintain Job Data</a></li>
              </ul>
            </div>
            <div class="span4">
              <ul>
                <li><a href="#">Maintain Pay Rate</a></li>
                <li><a href="#">Maintain Time Assignments</a></li>
                <li><a href="#">Mass Terminate Employees</a></li>
                <li><a href="#">Mass Maintain Pay Rate</a></li>
                <li><a href="#">Mass Renew Contract</a></li>
                <li><a href="#">Promote Employee</a></li>
              </ul>
            </div>
            <div class="span4">
              <ul>
                <li><a href="#">Return to Duties</a></li>
                <li><a href="#">Renew Contract</a></li>
                <li><a href="#">Short Work Break</a></li>
                <li><a href="#">Suspend Employee</a></li>
                <li><a href="#">Terminate Employee</a></li>
                <li><a href="#">Transfer Employee</a></li>
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
