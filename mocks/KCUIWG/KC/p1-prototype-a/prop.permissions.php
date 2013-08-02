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
        <div class="box">
          <div class="boxHeader ">
            <h3>Access</h3>
          </div>
          <div class="boxContent " >
          <!-- <div style=" margin-bottom:12px; float:">
            <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="viewsummary" data-keyboard="true" url="modal-accesssummary/frame.html">View Summary</a>
          </div> -->
            <div class="tabbable tabs-left clearfix">
              <ul class="nav nav-tabs" style="display:block" >
                <li class="active">
                  <a href="#art" data-toggle="tab">Haskell, Edward </a>
                </li>
              </ul>

              <div class="tab-content">
                <div class="tab-pane active" id="art">
                  <div class="boxSubheader">
                    <h5 style="font-size:12px; padding-bottom:12px">Haskell, Edward </h5>
                    <!-- <div class="boxControls">
                      <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-reorderpersonnel/frame.html">Reorder</a> | <a href="#" class="ajax-modal" data-backdrop="true" data-controls-modal="deletepersonnel" data-keyboard="true" url="modal-deletepersonnel/frame.html">Delete</a>
                    </div> -->
                  </div>
                  <dl class="table-display" style="margin-top:-13px;">
                    <dt>User Name</dt>
                    <dd>haskedw </dd>
                    <dt>Unit Number</dt>
                    <dd>BL-MUS </dd>
                    <dt>Unit Name</dt>
                    <dd>JACOBS SCHOOL OF MUSIC</dd>
                    <dt>Role</dt>
                    <dd>
                      <a href="#" id="role-ed" data-type="checklist" data-pk="1" data-url="/post" data-original-title="Select Role(s)">Principal Investigator <i class="icon-pencil"></i></a>
                    </dd>
                  </dl>
                </div>
              </div>
            </div>
            <div class="clearfix"></div>
          </div>
        </div>
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
        <div style=" padd12px; text-align:center">
          <a href="prop.keypersonnel.php" class="btn"><i class="icon-chevron-left"></i> Back</a>
          <a href="#" class="btn">Save</a>
          <a href="prop.specialreview.php" class="btn btn-inverse">Save and Continue <i class="icon-white icon-chevron-right"></i></a>
        </div>
      </div>
    </div>
  </form>
</div>

<div class="modal hide fade" id="viewsummary"></div>
<p>

<?php include( 'assets/inc/scripts.global.php' ) ?>

 <script>
    $(function () {
        $('#role-ed').editable({
            value: [5],
            source: [{
                value: 1,
                text: 'Viewer'
            }, {
                value: 2,
                text: 'Budget Creator'
            }, {
                value: 3,
                text: 'Narritive Writer'
            }, {
                value: 4,
                text: 'Aggregator'
            }, {
                value: 5,
                text: 'Principal Investigator / Contact Person'
            }]
        });
		
		
		
		
		 $('#role-judith, #role-violet').editable({
            value: [1],
            source: [{
                value: 1,
                text: 'Viewer'
            }, {
                value: 2,
                text: 'Budget Creator'
            }, {
                value: 3,
                text: 'Narritive Writer'
            }, {
                value: 4,
                text: 'Aggregator'
            }, {
                value: 5,
                text: 'Contact Person'
            }]
        });
		
		
    $(".ajax-modal").live('click', function () {
        var url = $(this).attr('url');
        var modal_id = $(this).attr('data-controls-modal');
        $("#" + modal_id).load(url).modal('show');
    });
		
    });
</script>

</body>
</html>