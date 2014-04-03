<style type="text/css">
.uif-collection-box {border:1px solid #E5E5E5; background:#fff; height:170px; overflow:scroll;width:95%;overflow-x:hidden;}
.uif-collection-box .result-title{font-weight:normal;margin:0px 0 10px 15px;line-height:0; float:left; clear:both;}
.uif-collection-box input[type="checkbox"]{ margin:3px 3px  10px 0; float:left;}
.uif-collection-box li{padding:5px; float:left; clear:both; width:100%;border-bottom:1px dotted #ccc;}
.col-md-2{text-align:center;  margin-top:25%;}
.pad5{padding:5px;}
.uif-personnel-search-form legend{margin-bottom:10px;}
.uif-indicator-arrow-bottom-center {margin-left:3px;position: relative;background: #fff;width:99%; float:left; padding-bottom:10px;}
.uif-indicator-arrow-bottom-center:after {top: 100%;left: 50%;border: solid transparent;content: " ";height: 0;width: 0;position: absolute;pointer-events: none;border-color: rgba(255, 255, 255, 0);border-top-color: #fff;border-width: 30px;margin-left: -30px;}
.uif-bg-shaded{background:#f5f5f5; margin:0; padding:0; width:98.7%;}
.uif-personnel-search-for legend{padding-bottom:0; margin-bottom:0;}
</style>
<div id="modal-add-personnel" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content"> 
       
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="create-new-label">Add budget personnel</h4>
            </div>
               

<form action="#" method="get" class="form-horizontal pad5">
                <div class="clearfix">
                    <div class="row uif-bottom-shadow uif-indicator-arrow-bottom-center">
                        <div class="col-md-3">
                            <div class="uif-personnel-search-for">
                                <fieldset>
                                    <legend>Search for:</legend>
                                    <label class="radio" for="personnel-search-for-employee">
                                        <input type="radio" name="personnel-search-for" id="personnel-search-for-employee" checked>
                                        Employee</label>
                                    <label class="radio" for="personnel-search-for-nonemployee">
                                        <input type="radio" name="personnel-search-for" id="personnel-search-for-nonemployee">
                                        Non-Employee</label>
                                    <label class="radio" for="personnel-search-for-tbn">
                                        <input type="radio" name="personnel-search-for" id="personnel-search-for-tbn">
                                        TBN</label>
                                </fieldset>
                            </div>
                        </div>
                        <div class="col-md-9">
                            <div class="uif-personnel-search-form active" id="personnel-search-form-employee">
                                <fieldset>
                                    <legend>Search by:</legend>
                                    <div class="col-md-4" >
                                        <div class="form-group" >
                                            <label for="personnel-search-by-type" class="control-label"><span class="sr-only">Search by:</span></label>
                                            <select name="personnel-search-by-type" id="personnel-search-by-type" class="form-control input-sm uif-dropdownControl required" data-role="Control" data-control_for="uk9itsk" aria-required="true">
                                                <option value="first">First name</option>
                                                <option value="last">Last name</option>
                                                <option value="email">Email address</option>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="form-group">
                                            <label for="personnel-search-by-text" class="control-label"><span class="sr-only">Search for</span></label>
                                            <input type="text" name="personnel-search-by-text" id="personnel-search-by-text" class="form-control">
                                            <button class="btn btn-default">Search</button>
                                        </div>
                                    </div>
                                    <p class="clearfix text-muted">Results from your search will appear in the box below.</p>
                                </fieldset>
                            </div>
                        </div>
                    </div>
                    <div class="row uif-bg-shaded" >
                        <div class="col-md-5">
                            <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                <h5>We found <span id="found-total">13 </span><span id="search-type">Employee</span>'s' with the first name "<span id="search-text">john</span>":</h5>
                                <p class="text-muted">Select personnel to add to the budget.</p>
                                <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                    <ul>
                                        <li>
                                            <label for="personnel-result-1">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-1">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name ">John Smith</span> <span class="result-title text-muted ">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-2">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-2">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-3">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-3">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-4">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-4">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-5">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-5">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-6">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-6">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-7">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-7">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-2">
                            <button class="btn btn-default btn-sm"><span class="icon icon-plus"></span><span class="sr-only">Add to list</span></button>
                            <button class="btn btn-default btn-sm"><span class="icon icon-minus"></span><span class="sr-only">Remove from list</span></button>
                        </div>
                        <div class="col-md-5">
                            <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                <h5>You are adding the following additional personnel to this budget:</h5>
                                <p class="text-muted">You will be able to modify them later.</p>
                                <textarea class="sr-only" id="personnel-add-box-1-result"></textarea>
                                <div class="uif-collection-box" id="personnel-add-box-1-visible-b" data-dom-update="personnel-add-box-1">
                                    <ul>
                                        <li>
                                            <label for="personnel-result-1-b">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-1-b">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                        <li>
                                            <label for="personnel-result-2-b">
                                                <input type="checkbox" name="personnel-result" id="personnel-result-2-b">
                                                <span class="pull-right result-type">Employee</span> <span class="result-name">John Smith</span> <span class="result-title text-muted">Research Scientist</span> </label>
                                        </li>
                                    </ul>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </form>



            <div class="modal-footer"> <a role="button" class="btn btn-link" data-dismiss="modal" href="#">Cancel</a> <a role="button" class="btn btn-primary" href="budget-ng-personnelCosts-projPersonnel2.php">Add selected</a> </div>
        
        </div>
    </div>
</div>

<!-- //  MODAL ADD PERSONNEL -->
