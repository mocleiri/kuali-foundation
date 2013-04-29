<?php
# Variables
$page = 'basics-grantsgov';
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
          <div class="boxHeader ">
            <h3>Grants.gov</h3>
          </div>
          <div class="boxContent "  >
            <h4>Opportunity Search</h4>
            <div class="control-group">
              <label class="control-label" for="Description">Opportunity ID</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div>
            <div class="control-group">
              <label class="control-label" for="Description">CFDA Number</label>
              <div class="controls">
                <input type="text" id="Description" placeholder="" class="input-xlarge">
              </div>
            </div>
            <!-- <div class="control-group" style="text-align: right; width:405px;">
              <button class="btn btn-mini" >Search </button>
            </div> -->
            <!-- <h4>Opportunity</h4>
            <div class="clearfix">
              <dl class="table-display" style="margin-top:-13px;">
                <dt> Opportunity ID</dt>
                <dd></dd>
                <dt>Opportunity Title</dt>
                <dd></dd>
                <dt>Submission Type</dt>
                <dd></dd>
                <dt>S2S Revision Type</dt>
                <dd></dd>
                <dt> CFDA Number</dt>
                <dd></dd>
                <dt>Competition Id</dt>
                <dd></dd>
                <dt>Opening Date</dt>
                <dd></dd>
                <dt>Closing Date</dt>
                <dd></dd>
                <dt>Instruction Page</dt>
                <dd></dd>
                <dt>Schema URL</dt>
                <dd></dd>
              </dl>
              <div class="clearfix"></div>
            </div>
            <h4>Submission Details</h4>
            Submission details will be available after the proposal is submitted.
            <h4>Forms</h4>
            No forms are currently available for the Grants.gov opportunity selected. -->
          </div>
        </div>
        <div style=" padd12px; text-align:center">
          <a href="prop.basics.propdetails.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <!-- <a href="#" id="validate_data" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div> -->
        <a href="prop.basics.sponsproginfo.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a> </div>
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