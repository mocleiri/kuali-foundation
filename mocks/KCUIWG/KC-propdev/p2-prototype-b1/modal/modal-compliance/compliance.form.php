<?php


    $actionLabel = "Add";
    $action = "addComplianceEntry";

?>



                <form method="post" class="form-horizontal" id="compliance-form">
                    <input type="hidden" name="action" value="<?php echo $action?>">
                      <div class="form-group clearfix">
                        <label for="type" class="control-label col-md-3">Type:</label>
                        <div class="col-md-9">
                          <select name="type" id="type" class="form-control input-sm col-md-8">
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
                          <select name="approval_status" id="approval_status" class="form-control input-sm col-md-8">
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
                          <input type="text" class="form-control input-sm has-helper" name="protocol_no" id="protocol_no" />
                          <span class="input-group-btn"> <a href="#" class="icon-search launch-modal" data-modal-page="modal/#" data-modal-height="500"></a> </span> </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="application_date" class="control-label col-md-3">Application Date:</label>
                        <div class="col-md-9">
                          <input type="text" name="application_date" id="application_date" class="form-control input-sm col-md-8" value="" />
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_date" class="control-label col-md-3">Approval Date:</label>
                        <div class="col-md-9">
                          <input type="text" name="approval_date" id="approval_date" class="form-control input-sm col-md-8" value="" />
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="expiration_date" class="control-label col-md-3">Expiration Date:</label>
                        <div class="col-md-9">
                          <input type="text" name="expiration_date" id="expiration_date" class="form-control input-sm col-md-8" value="" />
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="exemptions" class="control-label col-md-3">Exemption:</label>
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
                        <button class="btn btn-primary btn-xs" id="update-compliance-entry"> <?php echo $actionLabel?></button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
