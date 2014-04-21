<!-- Modal -->

<div class="modal fade" id="actionlist-blanket" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" style="width: 800px">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel"> Blanket Actions</h4>
            </div>
            <div class="modal-body">
                <p>You can execute blanket actions across multiple documents in your action list. Please select a desired action.</p>
                <div class="row form-horizontal">
                    <div class="col-md-12"> 
                        
                        <!--  Employee Search-->
                        
                        <div class="form-group">
                            <label for="inputEmail3" class="col-md-3 control-label">Document Type</label>
                            <div class="col-md-6">
                                <select name="privileges" id="personType" class="form-control" onclick="craateUserJsObject.ShowPrivileges();">
                                    <option id="" value="">select</option>
                             
                                    <option>Award Budget </option>
                                    <option>Award </option>
                                    <option>Budget </option>
                                    <option>Coi Disclosure </option>
                                    <option>Committee </option>
                                    <option>Common Committee </option>
                                    <option>Iacuc Protocol </option>
                                    <option>Iacuc Protocol Online Review </option>
                                    <option>Institutional Proposal </option>
                                    <option>Kc Maintenance </option>
                                    <option>Negotiation </option>
                                    <option>Person Mass Change </option>
                                    <option>Proposal Development </option>
                                    <option>Protocol </option>
                                    <option>Protocol Online Review</option>
                                    <option>SubAward</option>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="inputEmail3" class="col-md-3 control-label">Apply This Action</label>
                            <div class="col-md-6">
                                <select name="privileges" id="personType" class="form-control" onclick="craateUserJsObject.ShowPrivileges();">
                                    <option id="" value="">select</option>
                                    <option id="emp" value="emp">Acknowledge</option>
                                    <option id="nonemp" value="nonemp">Approve</option>
                                    <option id="tbn" value="tbn">Dissaprove</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer"> <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> <a href="#" class="btn btn-primary">Apply Action</a> </div>
        </div>
    </div>
</div>
<!-- end Modal --> 
