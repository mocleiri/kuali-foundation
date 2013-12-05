<script>

      $(document).ready(function(){
          $('#compliance-form<?php echo $id?> #type').val("<?php echo $entry['type']?>");
          $('#compliance-form<?php echo $id?> #approval_status').val("<?php echo $entry['approval_status']?>");
            $('#compliance-form<?php echo $id?> #exemptions').val([<?php echo $entry['exemptions']?>]);

      });

    </script>

                <form method="post" class="form-horizontal" id="compliance-form<?php echo $id?>">
                    <input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
                    <input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
                      <div class="form-group clearfix">
                        <label for="type" class="control-label col-sm-3 col-xs-3">Type:</label>
                        <div class="col-sm-9 col-xs-9">
                          <select name="type" id="type" class="form-control input-sm col-md-8">
                            <option> </option>
                            <option value="Human Subjects">Human Subjects</option>
                            <option value="Animal Usage">Animal Usage</option>
                            <option value="Recombinant DNA">Recombinant DNA</option>
                            <option value="Radioactive Isotopes">Radioactive Isotopes</option>
                            <option value="Biohazard Materials">Biohazard Materials</option>
                            <option value="International Programs">International Programs</option>
                            <option value="Space Change">Space Change</option>
                            <option value="TLO Review - No conflict (A)">TLO Review - No conflict (A)</option>
                            <option value="TLO review - Reviewed, no conflict (B1)">TLO review - Reviewed, no conflict (B1)</option>
                            <option value="TLO Review - Potential Conflict (B2)">TLO Review - Potential Conflict (B2)</option>
                            <option value="TLO PR-Previously Reviewed">TLO PR-Previously Reviewed</option>
                            <option value="Foundation Relations">Foundation Relations</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-sm-3 col-xs-3">Approval status:</label>
                        <div class="col-sm-9 col-xs-9">
                          <select name="approval_status" id="approval_status" class="form-control input-sm col-md-8">
                            <option> </option>
                            <option value="Approved">Approved</option>
                            <option value="Exempt">Exempt</option>
                            <option value="Link to IACUC">Link to IACUC</option>
                            <option value="Link to IRB">Link to IRB</option>
                            <option value="Not yet applied">Not yet applied</option>
                            <option value="Pending">Pending</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="protocol_no" class="control-label col-sm-3 col-xs-3">Protocol number:</label>
                        <div class="col-sm-9 col-xs-9 input-group">
                          <input type="text" class="form-control input-sm has-helper" name="protocol_no" id="protocol_no" value="<?php echo $entry['protocol_no'];?>"/>
                          <span class="input-group-btn"> <a href="#" class="icon-search launch-modal" data-modal-page="modal/#" data-modal-height="500"></a> </span> </div>
                      </div>


                      <div class="form-group clearfix">
                        <label for="application_date" class="control-label col-sm-3 col-xs-3">Application Date:</label>
                        <div class="col-sm-9 col-xs-9 col-md-9">
                          <input type="text" name="application_date" id="application_date" class="form-control input-sm uif-dateControl" value="<?php echo $entry['application_date'];?>" placeholder="mm/dd/yyyy"/>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_date" class="control-label col-sm-3 col-xs-3">Approval Date:</label>
                        <div class="col-sm-9 col-xs-9">
                          <input type="text" name="approval_date" id="approval_date" class="form-control input-sm col-md-8 uif-dateControl" value="<?php echo $entry['approval_date'];?>" placeholder="mm/dd/yyyy" />
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="expiration_date" class="control-label col-sm-3 col-xs-3">Expiration Date:</label>
                        <div class="col-sm-9 col-xs-9">
                          <input type="text" name="expiration_date" id="expiration_date" class="form-control input-sm col-md-8 col-md-8 uif-dateControl" value="<?php echo $entry['expiration_date'];?>" placeholder="mm/dd/yyyy" />
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="exemptions" class="control-label col-sm-3 col-xs-3">Exemption:</label>
                        <div class="col-sm-9 col-xs-9">
                          <select name="exemptions[]" id="exemptions" class="form-control input-sm col-md-8" multiple>
                            <option >E1</option>
                            <option >E2</option>
                            <option >E3</option>
                            <option >E4</option>
                            <option >E5</option>
                            <option >E6</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="comments" class="control-label col-sm-3 col-xs-3">Comments:</label>
                        <div class="col-sm-9 col-xs-9">
                          <textarea name="comments" id="comments" class="form-control col-md-8 col-md-8"><?php echo $entry['comments'];?></textarea>
                        </div>
                      </div>
                      
                      
                    <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs update-compliance-entry" complianceEntryId="<?php echo $id?>" id="update-compliance-entry<?php echo $id?>"> <?php echo $actionLabel?></button>
                        <button class="btn btn-link btn-xs cancel-update-compliance-entry" complianceEntryId="<?php echo $id?>"> Cancel</button>
                      </div>
                      
                      
                    </form>
