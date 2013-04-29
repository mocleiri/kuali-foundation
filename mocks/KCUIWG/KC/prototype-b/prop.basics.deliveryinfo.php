<?php
# Variables
$page = 'basics-deliveryinfo';
?>

<?php require_once( 'assets/inc/head.php' ) ?>

<?php require_once( 'assets/inc/header.php' ) ?>

<?php include( 'assets/inc/subnavigation.php' ) ?>

<!-- Begin main application content -->
<div id="content" tabindex="-1">
  <form class="form-horizontal">
    <div class="row-fluid">
      <div class="span12 content">

        <!-- Examples of error messages --><!-- // -->
        <div class="box">
          <div class="boxHeader  ">
            <h3>Delivery Info</h3>
          </div>
          <div class="boxContent " >
            <div class="boxSubheader">
              <h4>Recipient</h4>
              <div class="boxControls"><a href="#" class="btn btn-mini btn-inverse">Edit</a></div>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt>Organization</dt>
              <dd></dd>
              <dt>Address 1</dt>
              <dd></dd>
              <dt>Address 2</dt>
              <dd></dd>
              <dt>City</dt>
              <dd></dd>
              <dt>State</dt>
              <dd></dd>
              <dt>Zip</dt>
              <dd></dd>
            </dl>
            <div class="clearfix"></div>
            <div class="boxSubheader">
              <h4>Delivery Details </h4>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType4">Mail By</label>
              <div class="controls">
                <select name="select2" class="input-xlarge" title="Notice of Opportunity" >
                  <option value="">select</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="ProposalType4">Type</label>
              <div class="controls">
                <select name="select2" class="input-xlarge" title="Notice of Opportunity" >
                  <option value="">select</option>
                </select>
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description3">Mail Account ID</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description3">Number of Copies</label>
              <div class="controls">
                <input type="text" id="Description3" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group"> 
              <!-- Textarea -->
              <label class="control-label">Mail Description</label>
              <div class="controls">
                <div class="textarea">
                  <textarea name="textarea2" class="input-xlarge"> </textarea>
                </div>
              </div>
            </div>
          </div>
        </div>  <div style=" padd12px; text-align:center">
        <a href="prop.basics.orgloc.php" class="btn">Back</a>
        <a href="#" class="btn">Save</a>
        <!-- <a href="#" id="validate_data" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div> -->
        <a href="prop.basics.keywords.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>


<!--
Action list sticky box
Chris Rodriguez, clrux@bu.edu
-->
<script>
// Loads file into iframe
// We'll use an iframe for the purposes of this prototype. Comment this line and uncomment the line above to switch methods.

$('.action-list').on('click', function() {
  if ($('body').find('#action_list').is(':visible')) {
  } else {
    $('body').append('<div id="action_list" class="sticky-panel"><a href="#" class="close" data-dismiss="alert">&times;</a><div class="actions-padded" id="actions_container"><iframe src="prop.actions-cr1.php"></iframe></div>');
    // Loads the above file
    // If we're on a web server just use the filename (plus path if necessary)
    //$('#action_list #actions_container').load(actions_list_file);
    return false;
  }
});
</script>
<!-- // -->


</body>
</html>