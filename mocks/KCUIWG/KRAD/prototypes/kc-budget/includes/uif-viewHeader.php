<header class="container uif-viewHeader-contentWrapper">
    <div id="ueqbqhn" class="uif-viewHeader" data-header_for="LabsProposal">
        <h1 class="uif-headerText">
            <p id="u1p8pc9q" class="uif-viewHeader-areaTitle"> Proposal Budget Development </p>
            <span class="uif-headerText-span"> Budget: Version 5</span> </h1>
        <div id="LabsProposal-DocInfo" class="uif-verticalBoxGroup uif-header-rightGroup uif-documentInfo" data-parent="LabsProposal">
            <div id="u1f206ki" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Initiator">
                <label id="ujre4xu" for="u7lh763_span" class="uif-label" data-label_for="u1f206ki"> Final Version: </label>
                <p id="u7lh763" class="uif-message"> No </p>
            </div>
            <div id="u1f206ld" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Status">
                <label id="uk9uzz5" for="u4ch8dm_span" class="uif-label" data-label_for="u1f206ld"> Created: </label>
                <p id="u4ch8dm" class="uif-message"> 1/25/14 </p>
            </div>
            <div id="u1f206jn" class="uif-messageField uif-boxLayoutVerticalItem clearfix" data-label="Doc Nbr">
                <label id="uj8x9wj" for="uauh5yk_span" class="uif-label" data-label_for="u1f206jn"> <a id="uotglr8" class="uif-actionLink"  data-toggle="modal" data-target="#switchdoc">Return to Proposal</a> </label>
                <p id="uauh5yk" class="uif-message"> #23533</p>
            </div>
            <div id="LabsProposal-MoreDocInfo" class="dropdown uif-boxLayoutVerticalItem clearfix"> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> more info... </a>
                <section id="uhlixhs" class="dropdown-menu uif-gridGroup">
                    <h4 class="uif-headerText"> Document Info </h4>
                    <table id="u98wduy" class="edit-table table table-condensed uif-table-fixed" role="presentation">
                        <tbody>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                            <tr>
                                <th scope="row">Label</th>
                                <td class="uif-gridLayoutCell">Item</td>
                            </tr>
                        </tbody>
                    </table>
                </section>
            </div>
        </div>
    </div>
    <div id="LabsProposal-DocActionBar" class="uif-actionBar uif-header-lowerGroup">
        <ul>
            <!--      <li> <a id="uotglns" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;print&quot;}"><span class="icon-print"></span>Print</a> </li>
            <li> <a id="uotglon" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;copy&quot;}"><span class="icon-copy"></span>Copy</a> </li>
            <li> <a id="uotglr8" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;access&quot;}"><span class="icon-lock"></span>Access</a> </li>-->
            <li> <a id="uotglmx" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;toggleAuditMode&quot;}"> Data Validation <span style="color:#090">(on)</span> </a> </li>
            <li> <a id="uotglr8" class="uif-actionLink"  data-toggle="modal" data-target="#budgetSettings"><span class="icon-cog"></span>Budget Settings</a> </li>
            <li> <a id="uotglr8" class="uif-actionLink" data-toggle="modal" data-target="#summary"><span class="icon-eye"></span>Summary</a> </li>
            <li><a  class="uif-actionLink" data-toggle="modal" data-target="#modal-budget-versions"><span class="icon-money"></span>Budget Versions</a> </li>
                    
            <?php
            if ($_COOKIE['pg'] || $_COOKIE['pg'] == 1) {
                echo '<li><a class="uif-actionLink" data-toggle="modal" data-target="#modal-generate-versions"><span class="icon-stack"></span>Autocalculate Periods </a></li>';
            } else {
                echo '<li><a class="uif-actionLink" data-toggle="modal" data-target="#modal-generate-versions"><span class="icon-stack"></span>Autocalculate Periods </a></li>';
            }
            ?>
     
                 <li id="page-help"> <a  class="dropdown-toggle" data-toggle="dropdown"><span class="icon-question-sign"><span class="sr-only">help</span></span><span class="caret"></span></a>
                        <ul class="dropdown-menu" role="menu">
                           
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Periods &amp; Totals</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Rates</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Personnel Cost</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Non-Personnel</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Institutional Commitments</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Project Income</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Modular</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Budget Notes</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Summary</a></li>
<li><a href="https://testdrive.kc.kuali.org/kc-ptd/static/help/default.htm?turl=Documents/requiredfieldsforsavingdocument.htm">Glossary of Terms</a></li>
                           
                           
                           
                           
                        </ul>
                        </li>
                 
            
        </ul>
    </div>
</header>
