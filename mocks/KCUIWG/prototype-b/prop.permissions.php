<?php
# Variables
$page = 'access';
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
            <h3> Permissions </h3>
          </div>
          <div class="boxContent " >
            <div class="boxSubheader">
              <h4>Assigned Roles</h4>
              <div class="boxControls"><a href="../asdf.php" class="">view permissions</a></div>
            </div>
            <dl class="table-display" style="margin-top:-13px;">
              <dt> Viewer</dt>
              <dd><a href="#">assign</a></dd>
              <dt> Budget Creator</dt>
              <dd><a href="#">assign</a></dd>
              <dt> Narrative Writer</dt>
              <dd><a href="#">assign</a></dd>
              <dt> Aggregator</dt>
              <dd>Oscar Peterson <a href="#">(change)</a></dd>
              <dt> Contact Person</dt>
              <dd><a href="#">assign</a></dd>
           
            </dl>
            <div class="clearfix"></div>
          </div>
        </div>
        
        
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>--> <div style=" padd12px; text-align:center"> <a href="prop.budget.php" class="btn"><i class="icon-chevron-left"></i> back</a> <a href="#" class="btn">save</a> <a href="prop.medusa.php" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a> </div>
      </div>
    </div>
  </form>
</div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

</body>
</html>