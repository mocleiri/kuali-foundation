<?php
# Variables
$page = 'customdata';
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
            <h3>Institution Specific Data</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" > <div class="control-group">
              <label class="control-label" for="Description">Graduate Student Count</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div> <div class="control-group">
              <label class="control-label" for="Description">Tenured</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div>
          </div>
        </div>
        <div class="box"> <div class="boxHeader">
            <h3> Project Details </h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >
            <div class="control-group">
              <label class="control-label" for="Description2">Export Controls</label>
              <div class="controls">
                <input type="text" id="Description2" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description2">Inventions</label>
              <div class="controls">
                <input type="text" id="Description2" placeholder="" class="input-xlarge">
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
          <a href="prop.budget.actions.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
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