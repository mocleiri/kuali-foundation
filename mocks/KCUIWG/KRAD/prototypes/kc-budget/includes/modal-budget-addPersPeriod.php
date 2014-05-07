<!-- Modal -->

<div class="modal fade" id="persperiod" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                <h4 class="modal-title" id="myModalLabel">Add Personnel to Period</h4>
            </div>
            <div class="modal-body"> 
                <script type="text/javascript">
    function showHide() {
        if (document.getElementById("groups").selectedIndex == 6) {
            document.getElementById("newname").style.display = "";
        } else {
            document.getElementById("newname").style.display = "none";
        }
    }
</script>
                <div class="form-horizontal uif-cssGridGroup" role="form">
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Person </label>
                        <div class="col-sm-6">
                            <select class="form-control">
                                <option value="">select</option>
                                <option>Ward Cleaver</option>
                                <option>John Coltrane</option>
                                <option>Oliver Elgin</option>
                                <option>Darren Devaney</option>
                                <option>Emory Eagle</option>
                                <option>TBN</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Object Code </label>
                        <div class="col-sm-6">
                            <select class="form-control">
                                <option value="">select</option>
                                <option value="400255">Administrative Staff - Off</option>
                                <option value="400250">Administrative Staff - On</option>
                                <option value="421568">CWSP Credit - Not MTDC</option>
                                <option value="400550">Electronic Assembly - On</option>
                                <option value="400654">Exempt Technical - On</option>
                                <option value="400135">Faculty Emeriti</option>
                                <option value="400136">Faculty Retired Non-Tenured - On</option>
                                <option value="400105">Faculty Salaires Non-Tenured - Off</option>
                                <option value="400040">Faculty Salaries Non-Tenured - On</option>
                                <option value="400090">Faculty Salaries Tenured - Off</option>
                                <option value="400025">Faculty Salaries Tenured - On</option>
                                <option value="400315">Fellows - Non-Student- Not MTDC</option>
                                <option value="400700">Graduate Student Staff - On</option>
                                <option value="400452">Hourly Personnel - Off</option>
                                <option value="400450">Hourly Personnel - On</option>
                                <option value="400552">Mechanical Assembly - On</option>
                                <option value="400756">MIT Students - Off</option>
                                <option value="400754">MIT Students - On</option>
                                <option value="400155">Other Academic Staff - Off</option>
                                <option value="400140">Other Academic Staff - On</option>
                                <option value="400390">Post-Doctoral Staff</option>
                                <option value="400365">Program Manager</option>
                                <option value="400370">Project Engineering Staff - On</option>
                                <option value="400601">Project Support Staff - Off</option>
                                <option value="400600">Project Support Staff - On</option>
                                <option value="400556">Quality Control - On</option>
                                <option value="400708">Research Asst - Off</option>
                                <option value="400706">Research Asst - On</option>
                                <option value="400355">Research Staff - Off</option>
                                <option value="400350">Research Staff - On</option>
                                <option value="400115">Summer - Other Academic - Off</option>
                                <option value="400070">Summer Faculty - Off</option>
                                <option value="400005">Summer Faculty - On</option>
                                <option value="400050">Summer Other Academic - On</option>
                                <option value="420262">Temporary Help</option>
                                <option value="400768">Undergrad S&amp;W UROP - Off</option>
                                <option value="400770">Undergrad S&amp;W UROP - On</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Group </label>
                        <div class="col-sm-6">
                            <select class="form-control" id="groups" onchange="showHide()">
                                <option value="">Default</option>
                                <option>My Custom Group 1</option>
                                <option>My Custom Group 2</option>
                                <option>My Custom Group 3</option>
                                <option>My Custom Group 4</option>
                                <option>My Custom Group 5</option>
                                <option>Create New Group...</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group"  id="newname" style="display:none;">
                        <label for="inputEmail3" class="col-sm-3 control-label">New Group Name </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Start Date </label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">End Date </label>
                        <div class="col-sm-9">
                            <div class="input-group">
                                <input id="u11k8c4j_control" type="text" name="field3" value="12/15/2013" size="" class="form-control input-sm uif-textControl has-helper valid" data-role="Control" data-control_for="u11k8c4j">
                                <span class="input-group-btn"> <a id="u11k8c4j_quickfinder_act" class="uif-actionLink icon-calendar" tabindex="0" data-role="Action" data-focusid="NEXT_INPUT:u11k8c4j_quickfinder_act"></a> </span> </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Effort % </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Charged % </label>
                        <div class="col-sm-6">
                            <input type="text" class="form-control" id="" placeholder="">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputEmail3" class="col-sm-3 control-label">Period Type </label>
                        <div class="col-sm-6">
                            <select class="form-control">
                                <option value="2">Academic</option>
                                <option value="3">Calendar</option>
                                <option value="4">Summer</option>
                            </select>
                        </div>
                    </div>
                </div>
            </div>
            <div class="modal-footer"> <a type="button" class="btn btn-default" data-dismiss="modal">Cancel</a> <a  href="budget-ng-personnelCosts-persPeriod2.php" type="button" class="btn btn-primary" data-dismiss="modal">Assign to Period 1</a> </div>
        </div>
    </div>
</div>

<!-- end Modal --> 