<?php
# Variables
$page = 'instspecdata-1';
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
            <h3>Institution Specific Data 1</h3>
          </div>
          <div class="boxContent expandTarget" style=" display:block" >  <fieldset><div class="control-group">

          <!-- Text input-->
          <label class="control-label" for="input01">Text input</label>
          <div class="controls">
            <input type="text" placeholder="placeholder" class="input-xlarge">
          </div>
        </div>

    <div class="control-group">

          <!-- Select Basic -->
          <label class="control-label">Select - Basic</label>
          <div class="controls">
            <select class="input-xlarge">
              <option>Enter</option>
              <option>Your</option>
              <option>Options</option>
              <option>Here!</option>
            </select>
          </div>

        </div>

    <div class="control-group">

          <!-- Select Multiple -->
          <label class="control-label">Select - Multiple</label>
          <div class="controls">
            <select class="input-xlarge" multiple="multiple">
              <option>Enter</option>
              <option>Your</option>
              <option>Options</option>
              <option>Here!</option>
            </select>
          </div>
        </div>

    <div class="control-group">
          <label class="control-label">Radio buttons</label>
          <div class="controls">

            <!-- Multiple Radios -->
            <label class="radio">
              <input type="radio" value="Option one" name="group" checked="checked">
              Option one
            </label>
            <label class="radio">
              <input type="radio" value="Option two" name="group">
              Option two
            </label>
          </div>

        </div>

    </fieldset>
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
          <a href="prop.instspecdata.2.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>