<?php
# Variables
$page = 'instspecdata-3';
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
            <h3>Institution Specific Data 3</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" > <div class="control-group">
              <label class="control-label" for="Description">Field 1</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 2</span>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 3</span>
              <div class="controls">
                <input type="text" id="Description4" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 4</span>
              <div class="controls">
                <input type="text" id="Description5" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"><span class="control-label">Field 5</span>
              <div class="controls">
                <input type="text" id="Description6" placeholder="" class="input-xlarge">
              </div>
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
          <a href="prop.instspecdata.2.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.medusa.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>