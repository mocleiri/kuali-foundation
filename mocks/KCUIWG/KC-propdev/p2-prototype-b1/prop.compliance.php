<?php
# Variables
$page = 'compliance';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
        <h3>Compliance</h3>
        <div class="panel-group" id="accordion">

                <?php //include "modal/modal-compliance/compliance.entry.preview.php";?>

          <?php
               if(isset($_SESSION['compliance']) && is_array($_SESSION['compliance'])){
                    foreach($_SESSION['compliance'] as $key=>$entry){
                      //  print_r($entry);
                      include "inc/compliance.entry.php";

                    }
                }
          ?>

          <div class="panel panel-default">
            <div class="panel-heading">
              <div class="row">
                <div class="col-md-3">
                  <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span aria-hidden="true" class="icon-caret-right"></span> International Programs</a> </h4>
                </div>
                <div class="col-md-2"> Approved </div>
                <div class="col-md-3">#7236471AC</div>
                <div class="col-md-3"> Exp: 05/01/2014 </div>
                <div class="col-md-1"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
              </div>
            </div>
            <div id="collapse2" class="panel-collapse collapse">
              <div class="panel-body">
                <form method="post" class="form-horizontal">
                  <div class="form-group clearfix">
                    <label for="type" class="control-label col-md-3">Type:</label>
                    <div class="col-md-9">
                      <select name="type" id="type" class="form-control input-sm col-md-8">
                        <option value="1">Human Subjects</option>
                        <option value="2">Animal Usage</option>
                        <option value="3">Recombinant DNA</option>
                        <option value="4">Radioactive Isotopes</option>
                        <option value="5">Biohazard Materials</option>
                        <option value="6" selected="selected">International Programs</option>
                        <option value="7">Space Change</option>
                        <option value="8">TLO Review - No conflict (A)</option>
                        <option value="9">TLO review - Reviewed, no conflict (B1)</option>
                        <option value="10">TLO Review - Potential Conflict (B2)</option>
                        <option value="11">TLO PR-Previously Reviewed</option>
                        <option value="12">Foundation Relations</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_status" class="control-label col-md-3">Approval status:</label>
                    <div class="col-md-9">
                      <select name="type" id="approval_status" class="form-control input-sm col-md-8">
                        <option value="1" selected="selected">Approved</option>
                        <option value="2">Exempt</option>
                        <option value="3">Link to IACUC</option>
                        <option value="4">Link to IRB</option>
                        <option value="5">Not yet applied</option>
                        <option value="6">Pending</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="protocol_no" class="control-label col-md-3">Protocol number:</label>
                    <div class="col-md-9 input-group">
                      <input type="text" class="form-control input-sm has-helper store-as-session" name="" id="" value="7236471AC" />
                      <span class="input-group-btn"> <a href="#" class="icon-search launch-modal" data-modal-page="modal/#" data-modal-height="500"></a> </span> </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Application Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="application_date" id="application_date" class="form-control input-sm col-md-8 uif-dateControl hasDatepicker" placeholder="mm/dd/yyyy" value="12/12/2013">
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Approval Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="exemption_no" id="exemption_no" class="form-control input-sm col-md-8" value="12/12/2013">
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Expiration Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="expiration_date" id="expiration_date" class="form-control input-sm col-md-8 uif-dateControl hasDatepicker" placeholder="mm/dd/yyyy" value="05/01/2014">
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Exemption:</label>
                    <div class="col-md-9">
                      <select name="exemptions" id="exemptions" class="form-control input-sm col-md-8" multiple>
                        <option >E1</option>
                        <option >E2</option>
                        <option >E3</option>
                        <option >E4</option>
                        <option >E5</option>
                        <option >E6</option>
                      </select>
                    </div>
                  </div>
                  <div class="btn-row-widget-action pull-right">
                    <button class="btn btn-primary btn-xs" id=""> Save</button>
                    <button class="btn btn-link btn-xs" id=""> Cancel</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
          <div class="panel panel-default" id="add_entry">
            <div class="panel-heading">
              <div class="row">
                <div class="col-md-6">
                  <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse3"> Add New Protocol</a> 
                    <!-- <span>-</span>
											<span>-</span>
											<span>-</span>
											<span>-</span> --> 
                  </h4>
                </div>
                <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
              </div>
            </div>
            <div id="collapse3" class="panel-collapse collapse in">
              <div class="panel-body">
                <form method="post" class="form-horizontal">
                  <div class="form-group clearfix">
                    <label for="type" class="control-label col-md-3">Type:</label>
                    <div class="col-md-9">
                      <select name="type2" id="type2" class="form-control input-sm col-md-8">
                        <option value="0">select</option>
                        <option value="1">Human Subjects</option>
                        <option value="2">Animal Usage</option>
                        <option value="3">Recombinant DNA</option>
                        <option value="4">Radioactive Isotopes</option>
                        <option value="5">Biohazard Materials</option>
                        <option value="6">International Programs</option>
                        <option value="7">Space Change</option>
                        <option value="8">TLO Review - No conflict (A)</option>
                        <option value="9">TLO review - Reviewed, no conflict (B1)</option>
                        <option value="10">TLO Review - Potential Conflict (B2)</option>
                        <option value="11">TLO PR-Previously Reviewed</option>
                        <option value="12">Foundation Relations</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="approval_status" class="control-label col-md-3">Approval status:</label>
                    <div class="col-md-9">
                      <select name="type3" id="type3" class="form-control input-sm col-md-8">
                        <option value="0">select</option>
                        <option value="1">Approved</option>
                        <option value="2">Exempt</option>
                        <option value="3">Link to IACUC</option>
                        <option value="4">Link to IRB</option>
                        <option value="5">Not yet applied</option>
                        <option value="6">Pending</option>
                      </select>
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="protocol_no" class="control-label col-md-3">Protocol number:</label>
                    <div class="col-md-9 input-group">
                      <input type="text" class="form-control input-sm has-helper store-as-session" name="" id="" />
                      <span class="input-group-btn"> <a href="#" class="icon-search launch-modal" data-modal-page="modal/#" data-modal-height="500"></a> </span> </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Application Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="protocol_no3" id="protocol_no3" class="form-control input-sm col-md-8" value="" />
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Approval Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="protocol_no4" id="protocol_no4" class="form-control input-sm col-md-8" value="" />
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Expiration Date:</label>
                    <div class="col-md-9">
                      <input type="text" name="protocol_no5" id="protocol_no5" class="form-control input-sm col-md-8" value="" />
                    </div>
                  </div>
                  <div class="form-group clearfix">
                    <label for="exemption_no" class="control-label col-md-3">Exemption:</label>
                    <div class="col-md-9">
                      <select name="exemptions" id="exemptions" class="form-control input-sm col-md-8" multiple>
                        <option >E1</option>
                        <option >E2</option>
                        <option >E3</option>
                        <option >E4</option>
                        <option >E5</option>
                        <option >E6</option>
                      </select>
                    </div>
                  </div>
                  <div class="btn-row-widget-action pull-right">
                    <button class="btn btn-primary btn-xs" id=""> Add</button>
                    <button class="btn btn-link btn-xs" id=""> Cancel</button>
                  </div>
                </form>
              </div>
            </div>
          </div>
        </div>
        <div class="btn-row-widget-action">

           <button id="compliance_add" class="btn btn-default btn-xs launch-modal" data-modal-page="modal/modal-compliance/compliance.add.php"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
        </div>
        
        <!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <button  onclick="location.href='prop.keypersonnel.creditfa.php'" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='prop.attachments.proposal.php'" class="btn btn-primary">Save and Continue</button>
          </div>
          <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<script type="text/javascript">
$('#add_entry').hide();
$('#compliance_add').click(function() {
	$('#add_entry').fadeIn();
});
</script>

<script>
$(document).ready(function(){
    $(".remove-compliance-entry").live("click", function(){
            var container = $(this).parents('div').eq(4);

            if(confirm("Are you sure you want to remove this entry")) $(container).remove();
             console.log($(this).attr('personnel-id'));
             var id = $(this).attr('personnel-id');
             $.post('save-session.php', {'id': id, 'action' : 'removeComplianceEntry'}, function(){

             });
             return false;
          //   alert($(this).parents('div').eq(4).attr('id'));
        });

});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>