<?php
# Variables
$page = 'personnel';
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
          <div class="boxHeader">
            <h3> Personnel</h3>
          </div>
          
          <!-- <div class="boxHeader expandControl">
            <h3>Key Personnel</h3>
          </div>-->
          <div class="boxContent expandTarget" style="" >
            <div class="tabbable tabs-left clearfix">
              
              <!--   <div class="boxSubheader">
              <h4>About this page</h4>
              
            </div>-->
              <!-- Only required for left/right tabs -->
              
              <div class="alert alert-info">
                <button type="button" class="close" data-dismiss="alert">&times;</button>
                Use this page to identify the faculty member or senior researcher who is the Principal Investigator (PI) of the proposal, any additional Co-Investigators (Co-I), and project Key Persons (other Key Personnel).</div>
              <!--    <div class="boxSubheader">
                <h4>Personnel</h4>
              </div>-->
              
              <ul class="nav nav-tabs" style="display:block" >
                <li>
                     <a href="#" class="ajax-modal notab" data-backdrop="true" data-controls-modal="addemployee" data-keyboard="true" url="modal-addpersonnel/frame.html"><small><em>add person...</em></small></a>
                  
                  <!-- Modal -->
                  
                </li>
              </ul>
            </div>
          </div>
        </div>
        <div style=" padd12px; text-align:center">
          <a href="prop.proposal.html" class="btn"><i class="icon-chevron-left"></i> back</a>
          <a href="#" class="btn">save</a>
          <a href="prop.specialreview.html" class="btn btn-inverse">save and continue<i class="icon-white icon-chevron-right"></i></a>
        </div>
        
        <!--<div class="box"> <div class="boxHeader expandControl">
            <h3> asdfasdfasdf </h3>
          </div>
          <div class="boxContent expandTarget"> asdfasdfasdf </div>
          <div class="action_bar"> <a href="#" class="btn btn-small">Export data</a> </div>
        </div>-->
      </div>
    </div>
  </form>
</div>


<!-- Modal -->

<div class="modal hide fade" id="addemployee"></div>
<div class="modal hide fade" id="addnonemployee"></div>
<div class="modal hide fade" id="deletepersonnel"></div>


<?php include( 'assets/inc/scripts.global.php' ) ?>
<script type="text/javascript">
    $(document).ready(function () {
        
        $('#username, #email, #phone, #prititle, #dirtitle, #fax, #pager, #mobile, #officelocation,#officelocation2,#address1,#address2, #address3, #city, #county, #zip, #edlevel, #gradyear, #major, #degree, #school').editable();
        $(function () {
            $('#role').editable({
                value: 1,
                source: [{
                    value: 1,
                    text: 'PI/Contact'
                }, {
                    value: 2,
                    text: 'Co-Principal Investigator'
                }]
            });
        });
        
       
    });

    $(".ajax-modal").live('click', function () {
        var url = $(this).attr('url');
        var modal_id = $(this).attr('data-controls-modal');
        $("#" + modal_id).load(url).modal('show');
    });
  </script>
</body>
</html>