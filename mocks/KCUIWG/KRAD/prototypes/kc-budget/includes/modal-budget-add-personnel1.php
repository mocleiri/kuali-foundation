
<div id="modal-add-personnel1" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Personnel Lookup</h4>
            </div>
            <div class="modal-body uif-cssGridGroup">
                <div class="form-horizontal" role="form"> <br />
                    <div class="row ">
                        <div class="col-md-12"> 
                            
                            <!--  Employee Search-->
                            
                            <div class="form-group">
                                <label for="inputEmail3" class="col-md-3 control-label">Personnel Type</label>
                                <div class="col-md-6">
                                    <select name="privileges" id="personType" class="form-control" onclick="craateUserJsObject.ShowPrivileges();">
                                        <option id="" value="">select</option>
                                        <option id="emp" value="emp">Employee</option>
                                        <option id="nonemp" value="nonemp">Non-Employee</option>
                                        <option id="tbn" value="tbn">To Be Named</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">KcPerson Id</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Last Name</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">First Name</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">User Name</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Email Address</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Office Phone</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Active</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Home Unit</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group employee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Campus Code</label>
                                <div class="col-md-6">
                                    <select class="form-control">
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                        <option value="">select</option>
                                    </select>
                                </div>
                            </div>
                            
                            <!--  Non Employee Search-->
                            
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Address Book Id</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Sponsor</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Organization</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Owned By Unit</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Sponsor Address Flag</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group nonemployee" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Title</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            
                            <!--  To Be Named-->
                            
                            
                        <!--
                            
                            <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">TBA Research</label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Research Associate  </label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Data Entry Assistant </label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            
                            
                            <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Computer Programmer   </label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            
                            
                            
                       <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Data Manager  </label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            
                              <div class="form-group tbn" style="display:none;">
                                <label for="inputEmail3" class="col-md-3 control-label">Project Director   </label>
                                <div class="col-md-6">
                                    <input class="form-control" type="text" placeholder="">
                                </div>
                            </div>
                            -->
                           
                            
                            
                            
                            
                                
                <p style="display:none;" id="" class="uif-message tbn">Select the quantity of each 'to be named' person category to be returned to your budget.</p><br />
                
                
<table id="" class="table table-condensed table-bordered tbn" role="presentation"style="display:none;" >
							<tbody>
								<tr>
									<th class="uif-gridLayoutCell">Person Category </th>
									<th scope="row" style="width:5%">Quantity</th>
								</tr>
								<tr class="">
								    <td  title="TBA Research"> TBA Research
								        &nbsp; </td>
								    <td><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
								</tr>
								<tr>
								    <td  title="Research Associate"> Research Associate
								        &nbsp; </td>
								    <td class=""><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
							    </tr>
								<tr>
								    <td  title="Data Entry Assistant"> Data Entry Assistant
								        &nbsp; </td>
								    <td class=""><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
							    </tr>
								<tr>
								    <td  title="Computer Programmer"> Computer Programmer
								        &nbsp; </td>
								    <td class=""><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
							    </tr>
								<tr>
								    <td  title="Data Manager"> Data Manager
								        &nbsp; </td>
								    <td class=""><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
							    </tr>
								<tr>
								    <td  title="Project Director"> Project Director
								        &nbsp; </td>
								    <td class=""><input type="text" size="" class="" name="" id="" placeholder="" />
								        <label for=""></label></td>
							    </tr>
							</tbody>
						</table>                
                        
                        
                        
                            
                            
                            
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer">
            <a href="" class="btn btn-default" data-dismiss="modal">Cancel</a> 
            <a href="" style="display:none;" data-dismiss="modal" class="btn btn-primary employee" data-toggle="modal" data-target="#modal-add-personnel1-results">Search</a>
            <a href="" style="display:none;" data-dismiss="modal" class="btn btn-primary nonemployee" data-toggle="modal" data-target="#modal-add-personnel1-results">Search</a>
           
            
            
            <a href="budget-ng-personnelCosts-projPersonnel2.php" class="btn btn-primary tbn" data-dismiss="" style="display:none;">Add TBN Personnel to Budget</a>
            
            
             </div>
        </div>
    </div>
</div>

<!-- //  MODAL ADD PERSONNEL --> 
