<?php
$section = 'personnel';
$page = 'personnel-assign';
?>
<!DOCTYPE HTML>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuali Coeus - Concept</title>
    <?php include ('includes/styles.php') ?>
</head>

<body id="Uif-Application" style="padding-bottom: 48px;">
    
    <?php include ('includes/uif-applicationHeader.php') ?>

    <form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
        <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;">
            <header class="container-fluid uif-viewHeader-contentWrapper">
                <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal" style="padding-top: 30px">
                    
                    <?php include ('includes/uif-viewHeader.php') ?>

                </div>
            </header>
        
            <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container-fluid">            
                <div id="Uif-BreadcrumbUpdate" style="display:none;"></div>
                <div class="col-md-3">

                    <?php include ('includes/uif-navigation-budget.php') ?>

                </div>

                <div class="col-md-9">
                    <main id="LabsProposal-Page" class="uif-page">
                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Personnel Costs: Assign Personnel</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Assign personnel to one or all periods and configure efforts and charges.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <form action="#" method="#" class="form-horizontal">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="well clearfix">
                                            <section>
                                                <div class="col-md-3">
                                                    <h4>Select working period</h4>
                                                </div>
                                                <div class="col-md-9">
                                                    <div class="form-group">
                                                        <label for="assign-working-period">Budget period:</label>
                                                        <select name="assign-working-period" id="assign-working-period" class="form-control">
                                                            <option value="p1">1: 01/01/2014 - 12/31/2014</option>
                                                            <option value="p1">2: 01/01/2015 - 12/31/2015</option>
                                                            <option value="p1">3: 01/01/2016 - 12/31/2016</option>
                                                        </select>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="well clearfix">
                                            <section>
                                                <div class="col-md-3">
                                                    <h4>Assign Personnel</h4>
                                                    <span class="uif-current-period" id="current-period">Period 1</span><br />
                                                    <span class="uif-current-period-details" id="current-period-details">01/01/2014 - 12/31/2014</span>
                                                </div>
                                                <div class="col-md-9">
                                                    <div class="form-group">
                                                        <label for="assign-personnel" class="control-label">Personnel:</label>
                                                        <select name="assign-personnel" id="assign-personnel" class="form-control">
                                                            <option>- Select-</option>
                                                        </select>
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="assign-object-code-group" class="control-label">Object code name:</label>
                                                        <select name="assign-object-code-group" id="assign-object-code-group" class="form-control">
                                                            <option>Administrative Staff - Off</option>
                                                        </select>
                                                        <!-- <button class="btn btn-default icon icon-search pull-right"><span class="sr-only">Search</span></button> -->
                                                    </div>
                                                    <div class="form-group">
                                                        <label for="assign-group" class="control-label">Group:</label>
                                                        <select name="assign-group" id="assign-group" class="form-control">
                                                            <option>Group that I've configured</option>
                                                        </select>
                                                        <a href="#">Create new group</a>
                                                    </div>
                                                    <button class="btn btn-primary">Assign to this period</button>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                            <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                                <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                    <h4 class="uif-headerText">
                                                        <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="true" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                            <span class="uif-headerText-span">
                                                                <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: inline;"></span>
                                                                <span style="display: none;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                                Personnel available for budgeting
                                                            </span>
                                                        </a>
                                                    </h4>
                                                </header>
                                                <div id="u128z5dt_line0_disclosureContent" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="overflow: hidden; display: block;">
                                                    <div id="u8abdmj_line0" class="uif-fieldGroup" data-parent="u128z5dt_line0" data-group="u1fjua60_line0">
                                                        <form action="#" method="#" class="form-inline">
                                                            <table class="table table-condensed">
                                                                <thead>
                                                                    <tr>
                                                                        <th>Name</th>
                                                                        <th>Start date</th>
                                                                        <th>End date</th>
                                                                        <th>% Effort</th>
                                                                        <th>% Charged</th>
                                                                        <th>Period type</th>
                                                                        <th>Requested salary</th>
                                                                        <th>Calculated fringe</th>
                                                                    </tr>
                                                                </thead>
                                                                <tbody>
                                                                    <tr>
                                                                        <td>
                                                                            <span class="uif-text-medium">Ramen Noodle</span><br />
                                                                            <span class="uif-text-text-faded">Principal Investigator</span><br />
                                                                            <span class="uif-text-job-code">AA0000</span><br />
                                                                            <a href="#">Additional details</a>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group-sm">
                                                                                <label for="line_1_start" class="control-label">
                                                                                    <span class="sr-only">Start date</span>
                                                                                </label>
                                                                                <input type="text" size="5" name="line_1_start" id="line_1_start" placeholder="mm/dd/yyyy" class="form-control">
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group-sm">
                                                                                <label for="line_1_end" class="control-label">
                                                                                    <span class="sr-only">End date</span>
                                                                                </label>
                                                                                <input type="text" size="5" name="line_1_end" id="line_1_end" placeholder="mm/dd/yyyy" class="form-control">
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group-sm">
                                                                                <label for="line_1_effort" class="control-label">
                                                                                    <span class="sr-only">Percent effort</span>
                                                                                </label>
                                                                                <input type="text" size="3" name="line_1_effort" id="line_1_effort" class="form-control">
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group-sm">
                                                                                <label for="line_1_charged" class="control-label">
                                                                                    <span class="sr-only">Percent charged</span>
                                                                                </label>
                                                                                <input type="text" size="3" name="line_1_charged" id="line_1_charged" class="form-control">
                                                                            </div>
                                                                        </td>
                                                                        <td>
                                                                            <div class="form-group-sm">
                                                                                <label for="line_1_period_type" class="control-label">
                                                                                    <span class="sr-only">Percent effort</span>
                                                                                </label>
                                                                                <select name="line_1_period_type" id="line_1_period_type" class="form-control">
                                                                                    <option>Calendar</option>
                                                                                </select>
                                                                            </div>
                                                                        </td>
                                                                        <td><label for="line_1_req_salary"><span class="sr-only">Requested salary</span><span id="line_1_req_salary">$0</span></label></td>
                                                                        <td><label for="line_1_calc_fringe"><span class="sr-only">Calculated fringe</span><span id="line_1_calc_fringe">$0</span></label></td>
                                                                </tbody>
                                                                <tfoot>
                                                                    <tr>
                                                                        <td colspan="8">
                                                                            <a href="#" class="btn btn-default btn-sm">Apply these settings to all periods</a>
                                                                        </td>
                                                                    </tr>
                                                                </tfoot>
                                                            </table>
                                                        </form>
                                                    </div>
                                                </div>
                                            </section>
                                        </div>
                                    </div>
                                </div>
                            </form>
                        </div>

                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Object Codes and Groups</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Configure object code and group rate pairs to be assigned to specific budget pay scales.</p>
                                </div>
                            </div>
                        </header>
                        <div class="uif-cssGridSection uif-boxLayoutVerticalItem clearfix">
                            <div class="row">
                                <div class="col-md-12">
                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="true" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: inline;"></span>
                                                            <span style="display: none;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Object code and group rate configurations
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                            <div id="u128z5dt_line0_disclosureContent" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="overflow: hidden; display: block;">
                                                <div id="u8abdmj_line0" class="uif-fieldGroup" data-parent="u128z5dt_line0" data-group="u1fjua60_line0">
                                                    <form action="#" method="#" class="form-inline">
                                                        <table class="table table-condensed">
                                                            <thead>
                                                                <tr>
                                                                    <td></td>
                                                                    <th>Object code</th>
                                                                    <th>Group</th>
                                                                    <th><span class="sr-only">Actions</span></th>
                                                                </tr>
                                                            </thead>
                                                            <tbody>
                                                                <tr>
                                                                    <td><a class="icon icon-angle-right"><span class="sr-only">Show/Hide details</span></td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_object_code" class="control-label"><span class="sr-only">Object code</span></label>
                                                                            <input type="input" size="3" name="line_1_object_code" id="line_1_object_code" class="form-control">
                                                                        </div>
                                                                    </td>
                                                                    <td>
                                                                        <div class="form-group-sm">
                                                                            <label for="line_1_group_name" class="control-label"><span class="sr-only">Group name</span></label>
                                                                            <select name="line_1_group_name" id="line_1_group_name" class="form-control">
                                                                                <option value="Other">Some group that I've created</option>
                                                                            </select>
                                                                        </div>
                                                                    </td>
                                                                    <td><a class="icon icon-save"><span class="sr-only">Save</span></a></td>
                                                                </tr>
                                                            </tbody>
                                                        </table>
                                                    </form>
                                                </div>
                                            </div>
                                        </section>
                                    </div>                                    
                                </div>
                            </div>
                        </div>

                        <!-- VIEW FOOTER -->
                        <div class="uif-footer clearfix" data-sticky_footer="true" data-parent="LabsProposal">
                            <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="personnel-roster.php">Go back</a>
                            <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="#">Save</a>
                            <a class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem" href="non-personnel.php">Save and continue...</a>
                        </div>

                    </main>
                </div>
            </div>

            <div id="Uif-Dialogs"></div>
        
            <div id="modal-add-personnel" class="modal fade bs-example-modal-lg" tabindex="-1" role="dialog" aria-hidden="true" aria-labbeledby="create-new-title">
                <div class="modal-dialog">
                    <div class="modal-content">
                        <form action="#" method="#">
                            <div class="modal-header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h4 class="modal-title" id="create-new-label">Add budget personnel</h4>
                            </div>
                            <div class="modal-body">
                                <form action="#" method="#" class="form-horizontal">
                                    <div class="clearfix">
                                        <div class="row uif-bottom-shadow uif-indicator-arrow-bottom-center">
                                            <div class="col-md-3">
                                                <div class="uif-personnel-search-for">
                                                    <fieldset>
                                                        <legend>Search for:</legend>
                                                        <label for="employee"><input type="radio" name="personnel-search-for" id="personnel-search-for-employee"> Employee</label>
                                                        <label for="nonemployee"><input type="radio" name="personnel-search-for" id="personnel-search-for-nonemployee"> Non-Employee</label>
                                                        <label for="tbn"><input type="radio" name="personnel-search-for" id="personnel-search-for-tbn"> TBN</label>
                                                    </fieldset>
                                                </div>
                                            </div>
                                            <div class="col-md-9">
                                                <div class="uif-personnel-search-form active" id="personnel-search-form-employee">
                                                    <fieldset>
                                                        <legend>Search by:</legend>
                                                        <div class="col-md-3">
                                                            <div class="form-group">
                                                                <label for="personnel-search-by-type" class="control-label"><span class="sr-only">Search by:</span></label>
                                                                <select name="personnel-search-by-type" id="personnel-search-by-type" class="form-control">
                                                                    <option value="first">First name</option>
                                                                    <option value="last">Last name</option>
                                                                    <option value="email">Email address</option>
                                                                </select>
                                                            </div>
                                                        </div>
                                                        <div class="col-md-9">
                                                            <div class="form-group">
                                                                <label for="personnel-search-by-text" class="control-label"><span class="sr-only">Search for</span></label>
                                                                <input type="text" name="personnel-search-by-text" id="personnel-search-by-text" class="form-control">
                                                                <button class="btn btn-default">Search</button>
                                                                <p class="clearfix text-faded">Results from your search will appear in the box below.</p>
                                                            </div>
                                                        </div>
                                                    </fieldset>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row uif-bg-shaded">
                                            <div class="col-md-5">
                                                <div class="uif-padding-top uif-padding-top-2x uif-padding-left uif-padding-right uif-padding-bottom">
                                                    <h5>We found <span id="found-total">13 <span id="search-type">Employee</span>'s' with the first name "<span id="search-text">john</span>":</h5>
                                                    <p class="text-faded">Select personnel to add to the budget.</p>
                                                    <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                                    <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                                        <ul>
                                                            <li>
                                                                <label for="personnel-result-1">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-1"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-2">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-2"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-3">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-3"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-4">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-4"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-5">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-5"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-6">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-6"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-7">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-7"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
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
                                                    <p class="text-faded">You will be able to modify them later.</p>
                                                    <textarea class="sr-only" id="personnel-add-box-1"></textarea>
                                                    <div class="uif-collection-box" id="personnel-add-box-1-visible" data-dom-update="personnel-add-box-1">
                                                        <ul>
                                                            <li>
                                                                <label for="personnel-result-1">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-1"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                            <li>
                                                                <label for="personnel-result-2">
                                                                    <input type="checkbox" name="personnel-result" id="personnel-result-2"> 
                                                                    <span class="result-name">John Smith</span>
                                                                    <span class="result-title text-faded">Research Scientist</span>
                                                                    <span class="pull-right result-type">Employee</span>
                                                                </label>
                                                            </li>
                                                        </ul>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="modal-footer">
                                <a role="button" class="btn btn-link" data-dismiss="modal" href="#">Cancel</a>
                                <a role="button" class="btn btn-primary" href="_personnel-roster.php">Add selected</a>
                            </div>
                        </form>
                    </div>
                </div>
            </div>

        </div>

        <span id="formInfo">
            <input type="hidden" name="viewId" value="LabsProposal">
            <input type="hidden" name="formKey" value="2e468a13-a495-44cc-acd7-aac6b2ed97a0">
            <input type="hidden" name="requestedFormKey" value="2e468a13-a495-44cc-acd7-aac6b2ed97a0">
            <input type="hidden" name="sessionId" value="CAFCAB4387CB97D8567359A8C37D7712">
            <input type="hidden" name="flowKey" value="">
            <input type="hidden" name="view.applyDirtyCheck" value="true">
            <input type="hidden" name="dirtyForm" value="false">
            <input type="hidden" name="renderedInLightBox" value="false">
            <input type="hidden" name="view.singlePageView" value="true">
            <input type="hidden" name="view.disableBrowserCache" value="true">
        </span>
        <span id="formComplete"></span>
    
        <div class="jquerybubblepopup jquerybubblepopup-kr-error-cs" style="margin: 0px 0px 0px 395.5px; opacity: 0; top: 227px; left: 542px; position: absolute; display: none;" id="jquerybubblepopup-1393862100-0" data-for="u11k8c13_control">
            <table>
                <tbody>
                    <tr>
                        <td class="jquerybubblepopup-top-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-left.png);"></td>
                        <td class="jquerybubblepopup-top-middle" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-middle.png);"></td>
                        <td class="jquerybubblepopup-top-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/top-right.png);"></td>
                    </tr>
                    <tr>
                        <td class="jquerybubblepopup-middle-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-left.png);"></td>
                        <td class="jquerybubblepopup-innerHtml"><div class="uif-clientMessageItems uif-clientErrorDiv">
                                <ul>
                                    <li class="uif-errorMessageItem-field"><img class="uif-validationImage" src="../krad/images/validation/error.png" alt="Error"> Required</li>
                                </ul>
                            </div></td>
                        <td class="jquerybubblepopup-middle-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/middle-right.png);"></td>
                    </tr>
                    <tr>
                        <td class="jquerybubblepopup-bottom-left" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-left.png);"></td>
                        <td class="jquerybubblepopup-bottom-middle" style="background-image: url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-middle.png); text-align: left;"><img src="../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/tail-bottom.png" alt="" class="jquerybubblepopup-tail"></td>
                        <td class="jquerybubblepopup-bottom-right" style="background-image:url(../../plugins/tooltip/jquerybubblepopup-theme/kr-error-cs/bottom-right.png);"></td>
                    </tr>
                </tbody>
            </table>
        </div>
    </form>

    <?php include ('includes/footer-scripts.php') ?>

    <div id="jstree-marker" style="display: none;">»</div>
    <div id="jstree-marker-line" style="display: none;"></div>
    <div id="vakata-contextmenu"></div>
    <div id="ui-datepicker-div" class="ui-datepicker ui-widget ui-widget-content ui-helper-clearfix ui-corner-all">
        <div class="ui-datepicker-header ui-widget-header ui-helper-clearfix ui-corner-all">
            <a href="#" title="Prev" class="ui-datepicker-prev ui-corner-all" data-handler="prev" data-event="click">
                <span class="ui-icon ui-icon-circle-triangle-w">Prev</span>
            </a>
            <a href="#" title="Next" class="ui-datepicker-next ui-corner-all" data-handler="next" data-event="click">
                <span class="ui-icon ui-icon-circle-triangle-e">Next</span>
            </a>
            <div class="ui-datepicker-title">
                <select class="ui-datepicker-month" data-handler="selectMonth" data-event="change">
                    <option value="0">Jan</option>
                    <option value="1">Feb</option>
                    <option value="2" selected="selected">Mar</option>
                    <option value="3">Apr</option>
                    <option value="4">May</option>
                    <option value="5">Jun</option>
                    <option value="6">Jul</option>
                    <option value="7">Aug</option>
                    <option value="8">Sep</option>
                    <option value="9">Oct</option>
                    <option value="10">Nov</option>
                    <option value="11">Dec</option>
                </select>
                <select class="ui-datepicker-year" data-handler="selectYear" data-event="change">
                    <option value="2004">2004</option>
                    <option value="2005">2005</option>
                    <option value="2006">2006</option>
                    <option value="2007">2007</option>
                    <option value="2008">2008</option>
                    <option value="2009">2009</option>
                    <option value="2010">2010</option>
                    <option value="2011">2011</option>
                    <option value="2012">2012</option>
                    <option value="2013">2013</option>
                    <option value="2014" selected="selected">2014</option>
                    <option value="2015">2015</option>
                    <option value="2016">2016</option>
                    <option value="2017">2017</option>
                    <option value="2018">2018</option>
                    <option value="2019">2019</option>
                    <option value="2020">2020</option>
                    <option value="2021">2021</option>
                    <option value="2022">2022</option>
                    <option value="2023">2023</option>
                    <option value="2024">2024</option>
                </select>
            </div>
        </div>
        <table class="ui-datepicker-calendar">
            <thead>
                <tr>
                    <th class="ui-datepicker-week-end"><span title="Sunday">Su</span></th>
                    <th><span title="Monday">Mo</span></th>
                    <th><span title="Tuesday">Tu</span></th>
                    <th><span title="Wednesday">We</span></th>
                    <th><span title="Thursday">Th</span></th>
                    <th><span title="Friday">Fr</span></th>
                    <th class="ui-datepicker-week-end"><span title="Saturday">Sa</span></th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td class=" ui-datepicker-week-end ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">1</a></td>
                </tr>
                <tr>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">2</a></td>
                    <td class=" ui-datepicker-days-cell-over  ui-datepicker-current-day ui-datepicker-today" data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default ui-state-highlight ui-state-active ui-state-hover" href="#">3</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">4</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">5</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">6</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">7</a></td>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">8</a></td>
                </tr>
                <tr>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">9</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">10</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">11</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">12</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">13</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">14</a></td>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">15</a></td>
                </tr>
                <tr>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">16</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">17</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">18</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">19</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">20</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">21</a></td>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">22</a></td>
                </tr>
                <tr>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">23</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">24</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">25</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">26</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">27</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">28</a></td>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">29</a></td>
                </tr>
                <tr>
                    <td class=" ui-datepicker-week-end " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">30</a></td>
                    <td class=" " data-handler="selectDay" data-event="click" data-month="2" data-year="2014"><a class="ui-state-default" href="#">31</a></td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                    <td class=" ui-datepicker-week-end ui-datepicker-other-month ui-datepicker-unselectable ui-state-disabled">&nbsp;</td>
                </tr>
            </tbody>
        </table>

        <div class="ui-datepicker-buttonpane ui-widget-content">
            <button type="button" class="ui-datepicker-current ui-state-default ui-priority-secondary ui-corner-all" data-handler="today" data-event="click">Today</button>
            <button type="button" class="ui-datepicker-close ui-state-default ui-priority-primary ui-corner-all" data-handler="hide" data-event="click">Done</button>
        </div>
    </div>
    <div id="jstree-marker" style="display: none;">»</div>
    <div id="jstree-marker-line" style="display: none;"></div>
    <div id="vakata-contextmenu"></div>
    <div id="jGrowl" class="top-right jGrowl">
        <div class="jGrowl-notification"></div>
    </div>
    <div id="jstree-marker" style="display: none;">»</div>
    <div id="jstree-marker-line" style="display: none;"></div>
    <div id="vakata-contextmenu"></div>
</body>
</html>
