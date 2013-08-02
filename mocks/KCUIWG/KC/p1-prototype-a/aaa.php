<?php
# Variables
$page = 'basics';
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
        
         
        <div class="box  ">
          <div class="boxHeader expandControl closed ">
            <h3> Organization/Location </h3>
          </div>
          <div class="boxContent expandTarget clearfix "  >
            <div class="boxSubheader">
              <h4 >Applicant Organization</h4>
              <div class="boxControls "><a href="../asdf.php" class="btn btn-mini">change</a></div>
            </div>
            <dl class="table-display clearfix" style="margin-top:-13px; clear:both">
              <dt>Organization</dt>
              <dd>UNIVERSITY OF MICHIGAN</dd>
              <dt>Address 1</dt>
              <dd>2044 Wolverine Tower</dd>
              <dt>Address 2</dt>
              <dd>3003 State Street</dd>
              <dt> City</dt>
              <dd>ANN ARBOR</dd>
              <dt>State</dt>
              <dd>MI</dd>
              <dt>Zip</dt>
              <dd>48109-1273</dd>
              <dt>Congressional District</dt>
              <dd>MI-302 <a href="#">(delete)</a></dd>
              <dt>Congressional District</dt>
              <dd>MI-307 <a href="#">(delete)</a></dd>
              <dt>Congressional District</dt>
              <dd><a href="#">+ add</a></dd>
            </dl>
            
           
            
            
          </div>
        </div>
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