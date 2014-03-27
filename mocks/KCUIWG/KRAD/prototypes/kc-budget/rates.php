<?php
$section = '';
$page = 'rates';
?>
<!DOCTYPE HTML>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Kuali Coeus - Concept</title>
    <?php include ('includes/styles.php') ?>
</head>

<body id="Uif-Application" style="padding-bottom: 48px;">
    
    <?php include ('includes/header-sticky.php') ?>

    <form id="kualiForm" action="../kr-krad/uicomponents" method="post" accept-charset="UTF-8">
        <div id="LabsProposal" class="clearfix uif-formView" data-role="View" style="margin-top: 75px;">
            <header class="container-fluid uif-viewHeader-contentWrapper">
                <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal" style="padding-top: 30px">
                    
                    <?php include ('includes/header-docinfo.php') ?>

                </div>
            </header>
        
            <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper container-fluid">            
                <div id="Uif-BreadcrumbUpdate" style="display:none;"></div>
                <div class="col-md-3">

                    <?php include ('includes/navigation.php') ?>

                </div>

                <div class="col-md-9">
                    <main id="LabsProposal-Page" class="uif-page">
                        <header class="clearfix uif-header-contentWrapper">
                            <div class="uif-pageHeader clearfix">
                                <h2 class="uif-headerText">
                                    <span class="uif-headerText-span">Rates</span>
                                </h2>
                            </div>
                            <div class="uif-verticalBoxGroup uif-header-lowerGroup">
                                <div class="uif-boxLayoutVerticalItem clearfix">
                                    <p>Verify the default rates set by your institution. You can override them if necessary by clicking the "edit" icon to the right of each row.</p>
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
                                                            Research F&amp;A
                                                        </span>
                                                    </a>
                                                </h4>
                                                <div id="u199yk0n_line0" class="uif-verticalBoxGroup uif-header-rightGroup" data-parent="u128z5dt_line0">
                                                    <!-- <button id="LabsProposal-StackedSection_del_line0" class="btn btn-default btn-sm uif-action uif-boxLayoutVerticalItem clearfix icon-close" data-onclick="writeCurrentPageToSession(this);actionInvokeHandler(this);" data-dirtyonaction="true" data-ajaxreturntype="update-component" data-refreshid="LabsProposal-StackedSection" data-role="Action" data-loadingmessage="Deleting Line..." data-submit_data="{&quot;methodToCall&quot;:&quot;deleteLine&quot;,&quot;actionParameters[selectedCollectionPath]&quot;:&quot;list4&quot;,&quot;actionParameters[selectedCollectionId]&quot;:&quot;LabsProposal-StackedSection&quot;,&quot;actionParameters[selectedLineIndex]&quot;:&quot;0&quot;}" data-jumptoid="LabsProposal-StackedSection"></button> -->
                                                    <a class="guide-me pull-right icon-leaf" href="#">Guide me</a>
                                                </div>
                                            </header>
                                            <div id="u128z5dt_line0_disclosureContent" data-role="disclosureContent" data-open="true" class="uif-disclosureContent" style="overflow: hidden; display: block;">
                                                <div id="u8abdmj_line0" class="uif-fieldGroup" data-parent="u128z5dt_line0" data-group="u1fjua60_line0">
                                                    <table class="table table-condensed">
                                                        <thead>
                                                            <tr>
                                                                <th>Description</th>
                                                                <th>Campus Contact</th>
                                                                <th>Fiscal Year</th>
                                                                <th>Start Date</th>
                                                                <th>Institution Rate</th>
                                                                <th>Application Rate</th>
                                                                <th><span class="sr-only">Actions</span></th>
                                                            </tr>
                                                        </thead>
                                                        <tbody>
                                                            <tr>
                                                                <td>MTDC</td>
                                                                <td>Yes</td>
                                                                <td>2014</td>
                                                                <td>07/02/2014</td>
                                                                <td>48.00</td>
                                                                <td>48.00</td>
                                                                <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                            </tr>
                                                            <tr>
                                                                <td>Faculty Salaries</td>
                                                                <td>Yes</td>
                                                                <td>2014</td>
                                                                <td>07/02/2014</td>
                                                                <td>65.00</td>
                                                                <td>65.00</td>
                                                                <td><a class="icon icon-edit btn-edit"><span class="sr-only">Edit</span></a></td>
                                                            </tr>
                                                        </tbody>
                                                        <tfoot>
                                                            <tr>
                                                                <td colspan="7">
                                                                    <a href="#" class="btn btn-default btn-sm">Reset to Institution defaults</a> 
                                                                    <a href="#" class="btn btn-default btn-sm">Sync rates</a> 
                                                                </td>
                                                            </tr>
                                                        </tfoot>
                                                    </table>
                                                </div>
                                            </div>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Fringe Benefits
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Inflation
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Vacation
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Lab Allocation - Salaries
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Lab Allocation - Other
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>

                                    <div class="uif-stackedCollectionSection uif-boxLayoutVerticalItem clearfix">
                                        <section id="u128z5dt_line0" class="uif-collectionItem uif-boxCollectionItem" data-parent="LabsProposal-StackedSection">
                                            <header id="u1n4uf6a_line0" class="uif-header" data-header_for="u128z5dt_line0">
                                                <h4 class="uif-headerText">
                                                    <a data-role="disclosureLink" data-linkfor="u128z5dt_line0_disclosureContent" href="#" id="u128z5dt_line0_toggle" data-open="false" data-widgetid="uu9jpcc_line0" data-speed="500" data-ajax="false">
                                                        <span class="uif-headerText-span">
                                                            <span id="u128z5dt_line0_toggle_exp" class="icon-caret-down" style="display: none;"></span>
                                                            <span style="display: inline;" id="u128z5dt_line0_toggle_col" class="icon-caret-right"></span>
                                                            Other
                                                        </span>
                                                    </a>
                                                </h4>
                                            </header>
                                        </section>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <!-- VIEW FOOTER -->
                        <div class="uif-footer clearfix" data-sticky_footer="true" data-parent="LabsProposal">
                            <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="_periods-and-totals.php">Go back</a>
                            <a class="btn btn-default btn btn-default uif-boxLayoutHorizontalItem" href="#">Save</a>
                            <a class="btn btn-primary btn btn-primary uif-boxLayoutHorizontalItem" href="personnel-roster.php">Save and continue...</a>
                        </div>

                    </main>
                </div>
            </div>

            <div id="Uif-Dialogs"></div>

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