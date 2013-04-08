<?php
# Variables
$page = 'review-actions';
?>
<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container">
      <?php require_once( 'assets/inc/document-header.php' ) ?>
      <div class="row-fluid column-navleft">
        <div class="span2 vertNav">
          <?php require_once( 'assets/inc/document-nav.php' ) ?>
        </div>
        <div class="span10">
          <div class="box">
            <div class="boxHeader">
              <h3>Proposal Actions</h3>
            </div>
            <div class="boxContent">
              
              <div style="margin-bottom:30px;">
              	<a href="#" class="btn btn-success">Approve</a>
                <a href="#" class="btn btn-success">Reject</a>
                <a href="#" class="btn btn-success">Send Notification</a>
                <a href="#" class="btn btn-success">Recall</a>
              </div>
              
              <div class="boxSubheader">
                <h4>Comments</h4>
              </div>
              <div style="margin-bottom:10px;"> <a href="#" class="btn btn-mini btn-success addcomplianceprotocol"><i class="icon-white icon-plus"></i> Add Comment</a> </div>
              <div id="dl3" style="display:block">
                <table class="table table-striped table-bordered table-condensed">
                  <thead>
                    <tr>
                      <th style="width:80px;">Date</th>
                      <th style="width:125px;">Person</th>
                      <th>Comment</th>
                    </tr>
                  </thead>
                  <tbody>
                    <tr>
                      <td>03/22/2013</td>
                      <td>Bart Niedner</td>
                      <td>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sed lorem in risus ornare semper vitae sit amet mauris. Nam imperdiet sollicitudin odio vel convallis. Nullam non malesuada nisl. Nulla tempor fermentum ornare. Fusce sollicitudin lobortis arcu ut malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lacinia interdum eleifend. Cras nec hendrerit nulla. Nulla facilisi. In lectus arcu, consectetur vitae auctor in, condimentum at libero. Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. Suspendisse at quam tortor, ac pulvinar augue.</td>
                    </tr>
                    <tr>
                      <td>03/21/2013</td>
                      <td>David White</td>
                      <td>Cras cursus pellentesque erat non adipiscing. Proin tincidunt suscipit metus a pharetra. Proin non odio ac erat viverra commodo. Duis malesuada sollicitudin lorem quis commodo. Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.</td>
                    </tr>
                  </tbody>
                </table>
              </div>
              
              
              
            </div>
          </div>
          
          
          
        </div>
      </div>
      <div class="docControls"><a href="prop.review.summary.php" class="btn"><i class="icon-chevron-left"></i> Back</a> 
        <!--<a href="#" class="btn">Save</a>--> 
        <!--<a href="#" class="btn btn-inverse">Complete Proposal</a> </div>-->
    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
<!-- /container -->

</body>
</html>
