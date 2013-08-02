<?php
# Variables
$page = 'instspecdata-1';
?>

<?php require_once( 'assets/inc/head.php' ) ?>
<?php require_once( 'assets/inc/portal-nav.php' ) ?>
<section>
  <div class="sectionContents col2">
    <?php require_once( 'assets/inc/portal-toolbar.php' ) ?>
    <div class="container leftnav">   

<?php require_once( 'assets/inc/document-header.php' ) ?>
      
      
      <div class="leftnavContent">
      <?php require_once( 'assets/inc/document-nav.php' ) ?>
      
      <div id="content" role="application">
        <div class="row-fluid">
          






<div class="span12 content"> <h3>Institution Data 1</h3>
          <div class=" formfields">
            <form  class="form-horizontal" method="get" action="">
            <fieldset><div class="control-group">

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
            </form>
          </div>
          <div class="clearfix"></div>
        </div>










        </div> </div>
      




</div>
<div class="docControls"> <a href="prop.budget.actions.php" class="btn"><i class="icon-chevron-left"></i> Back</a> <a href="#" class="btn">Save</a> <a href="prop.instspecdata.2.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>




    </div>
  </div>
  <?php require_once( 'assets/inc/document-thirdTier.php' ) ?>
</section>
<div class="responsiveCue">asdf</div>
<?php require_once( 'assets/inc/portal-footer.php' ) ?>
