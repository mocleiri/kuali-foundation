<?php
# Variables
$page = 'index';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>

<div class="responsiveCue">test</div>

<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <div class="row-fluid">
        <div class="docHeader clearfix">
          <div class="span12">
            <ul class="breadcrumb">
              <li><a href="index.php">Home</a> <span class="divider">/</span></li>
             
              <li class="active">Financial Procession</li>
            </ul>
            <h2> Financial Processing</h2>
          </div>
        </div>
        <div class="span12" style="padding-top:20px">
          <div class=" row-fluid">
            <div class="span4">
              <ul>
               <li><a href="#">Advance Deposit</a></li>
<li><a href="#">Auxiliary Voucher</a></li>
<li><a href="#">Budget Adjustment</a></li>
<li><a href="#">Cash Receipt</a></li>
<li><a href="#">Credit Card Receipt</a></li>
<li><a href="#">Disbursement Voucher</a></li>
<li><a href="#">Distribution of Income and Expense</a></li>
<li><a href="#">General Error Correction</a></li>
<li><a href="#">Indirect Cost Adjustment</a></li>
<li><a href="financialProcessing.ib.docover.php">Internal Billing</a></li>
<li><a href="#">Pre-Encumbrance</a></li>
<li><a href="#">Transfer of Funds</a></li>
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
