<?php
# Variables
$page = 'review-actions';
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
            <h3>Proposal Actions</h3>
          </div>
          <div class="boxContent " style="min-height:500px" >
            
            <div class="boxSubheader">
              <h4>Comments</h4>
            </div>
            <div style="margin-bottom:10px;">
            	<a href="#" class="btn btn-mini btn-success addcomplianceprotocol"><i class="icon-white icon-plus"></i> Add Comment</a>
            </div>
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
            
            
            <div class="boxSubheader" style="margin-top:30px;">
              <h4>Add Attachment</h4>
            </div>
            <fieldset>
              <div class="control-group">
                <label class="control-label">File </label>
                <!-- File Upload -->
                <div class="controls">
                  <input class="input-file" id="fileInput" type="file">
                </div>
              </div>
              <div class="control-group">
                <!-- Select Basic -->
                <label class="control-label">Attachment Type</label>
                <div class="controls">
                  <select name="select" class="input-xlarge">
                    <option>Enter</option>
                    <option>Your</option>
                    <option>Options</option>
                    <option>Here!</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <!-- Select Basic -->
                <label class="control-label">Status</label>
                <div class="controls">
                  <select name="select" class="input-xlarge">
                    <option>Enter</option>
                    <option>Your</option>
                    <option>Options</option>
                    <option>Here!</option>
                  </select>
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Contact Name</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Uploaded By:</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Email Address</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Posted Timestamp</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Phone Number</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Comments</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01">Description</label>
                <div class="controls">
                  <input type="text" class="input-xlarge">
                </div>
              </div>
              <div class="control-group">
                <!-- Text input-->
                <label class="control-label" for="input01"></label>
                <div class="controls">
                  <a href="#" class="btn btn-mini btn-success"><i class="icon-white icon-plus"></i>Upload and add attachment</a>
                </div>
              </div>
            </fieldset>
            
            
            
            
            
            <!-- TEMP SCREEN SHOT -->
            <!--<div style="margin-top:50px;">
            	<img src="./assets/img/temp.review.actions.jpg" alt="temp screenshot" style="width:871px; height:394px;" />
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
          <a href="prop.review.print.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="#" class="btn btn-inverse">Complete Proposal</a>
        </div>
        
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>