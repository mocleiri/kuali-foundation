<?php
$section = 'asdf';
$page = 'asdf';
?>
<!DOCTYPE HTML>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>Kuali :: Fluid Application Header</title>
<!-- GLOBAL STYLES -->
<?php include ('includes/styles.php') ?>
<style type="text/css"></style>
</head>
<body id="Uif-Application">
<div style="width:1200px; margin-left:auto; margin-right:auto; margin-top:100px"><!-- Modal --><!-- Modal --><!--<div class="modal fade" id="switchdoc" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">-->
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Document Search</h4>
            </div>
            <div class="modal-body uif-cssGridGroup">
                <div class="form-horizontal" role="form">
                    <div class="row ">
                        <div class="col-md-9">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Document Type</label>
                                <div class="col-md-8">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="test-username-1" name="test-username-1" value="" placeholder="" required="">
                                        <div class="uif-form-control-helpers input-group-addon"><!-- Lookups, datepickers, etc. --><a class="btn btn-link btn-xs icon-search"><span class="sr-only">Search</span></a><a class="btn btn-link btn-xs icon-book"><span class="sr-only">Calendar</span></a><!-- // --></div>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Initiator</label>
                                <div class="col-md-8">
                                    <div class="uif-form-control input-group">
                                        <input type="text" class="form-control" id="test-username-1" name="test-username-1" value="" placeholder="" required="">
                                        <div class="uif-form-control-helpers input-group-addon"><!-- Lookups, datepickers, etc. --><a class="btn btn-link btn-xs icon-search"><span class="sr-only">Search</span></a><a class="btn btn-link btn-xs icon-book"><span class="sr-only">Calendar</span></a><!-- // --></div>
                                    </div>
                                </div>
                            </div>
                            
                            
                            
                            
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Document ID</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            
                            
                            
                            
                            
                            
                            <div class="col-md-3 uif-cssGridLabelCol">
                                <label id="u1206a7s_label" for="u1206a7s_fieldset" class="uif-label displayWith-u1206a7s uif-labelBlock" data-label_for="u1206a7s"> Date Created </label>
                            </div>
                            
                            
                            
                            
                            
                            
                            
                            <div class="col-md-6">
                                <div id="u1206a7s" class="uif-fieldGroup" data-parent="u1rttg5q" data-label="Project Dates" data-group="ufs91v9">
                                    <fieldset aria-labelledby="u1206a7s_label" id="u1206a7s_fieldset">
                                        <legend style="display: none">Project Dates</legend>
                                        <div id="ufs91v9" class="uif-cssGridGroup" data-parent="u1206a7s">
                                            <div class="row ">
                                                <div class="col-md-6">
                                                    <div id="u190upu7" class="uif-inputField" data-parent="ufs91v9" data-role="InputField">
                                                        <div class="input-group">
                                                            <input id="u190upu7_control" type="text" name="field4" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker valid" data-role="Control" data-control_for="u190upu7" placeholder="mm/dd/yyyy ">
                                                            <span class="input-group-msg">
                                                            <p id="uo9cnii" class="uif-message"> to </p>
                                                            </span></div>
                                                    </div>
                                                </div>
                                                <div class="col-md-6">
                                                    <div id="u190upv2" class="uif-inputField" data-parent="ufs91v9" data-role="InputField">
                                                        <input id="u190upv2_control" type="text" name="field5" value="" size="10" class="form-control input-sm uif-dateControl hasDatepicker" data-role="Control" data-control_for="u190upv2" placeholder="mm/dd/yyyy ">
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </fieldset>
                                </div>
                            </div>
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                            
                           <div class="form-group">
    <div class="col-sm-offset-3 col-sm-9">
      <div class="checkbox">
        <label>
          <input type="checkbox"> 
          Save search criteria for future searches
        </label>
      </div>
    </div>
  </div>
                            
                            
                            
                            
                            
                            
                        </div>
                        <div class="col-md-3">
                            <div class="btn-group pull-right">
                                <button type="button" class="btn btn-link dropdown-toggle" data-toggle="dropdown"> Load Previous Search <span class="caret"></span> </button>
                               
                               
              
                               
                               
                               
                                <ul class="dropdown-menu" role="menu">
                                  <li role="presentation" class="dropdown-header">Recent History</li>
                                    <li><a href="#">Initiator: jckahler </a></li>
                                    <li><a href="#">Initiator: awddocmaintainer </a></li>
                                    <li><a href="#">Created: 03/25/2014 </a></li>
                                    <li class="divider"></li>
                                      <li role="presentation" class="dropdown-header">Saved Searches</li>
                                    <li><a href="#">My Documents</a></li>
                                      <li><a href="#">Jims's Documents</a></li>
                                      
                                        <li><a href="#">Carol's Documents</a></li>
                                        
                                             <li class="divider"></li>
                                              <li><a href="#"> <span aria-hidden="true" class="icon-trash"></span>  Clear All</a></li>
                                             
                                </ul>
                            </div>
                        </div>
                    </div>
                </div>
                
            </div>
        <div class="modal-footer"><a href="" class="btn btn-primary" data-dismiss="">Search</a><a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> </div></div>
    </div>
</div>
<!--</div>--><!-- end Modal --><!-- end Modal -->
</div>
<?php include ('includes/footer-scripts.php') ?>
<!-- Modal --><!-- end Modal -->
</body>
</html>