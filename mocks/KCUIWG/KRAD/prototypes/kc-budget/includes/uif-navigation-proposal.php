<nav id="Uif-Navigation">
    <div id="LabsProposal-Menu" class="uif-menuNavigationGroup">
        <div class="sidebar-collapse"> <span class="icon-angle-left"></span> </div>
        <!-- NAVIGATION -->
        <ul class="nav nav-list">
            <li <?php if ($page == 'basics') { echo 'class="active_item"'; } ?>> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-file-alt"></span> <span id="u242f54" class="uif-innerText"> Basics </span> <span class="arrow icon-angle-right"></span> </a>
                <ul class="submenu uif-listLayout">
                    <li <?php if ($page == 'basics-proposalDetails') { echo 'class="active_item"'; } ?>> <a id="ua6f4k" class="uif-actionLink" href="prop-basics-details.php"> Proposal Details </a> </li>
                    <li <?php if ($page == 'basics-s2s') { echo 'class="active_item"'; } ?>> <a id="ua6f5f" class="uif-actionLink" tabindex="0" data-role="Action" data-submit_data="{&quot;methodToCall&quot;:&quot;refresh&quot;}"> S2S Opportunity Search </a> </li>
                    <li <?php if ($page == 'basics-deliveryInfo') { echo 'class="active_item"'; } ?>> <a id="ua6f6a" class="uif-actionLink" tabindex="0" data-role="Action"> Delivery Info </a> </li>
                    <li <?php if ($page == 'basics-sponsorProgram') { echo 'class="active_item"'; } ?>> <a id="ua6f75" class="uif-actionLink" tabindex="0" data-role="Action"> Sponsor &amp; Program Information </a> </li>
                    <li <?php if ($page == 'basics-organizationLocation') { echo 'class="active_item"'; } ?>> <a id="ua6f80" class="uif-actionLink" tabindex="0" data-role="Action"> Organization &amp; Location </a> </li>
                </ul>
            </li>
            <li <?php if ($section != '' &&  $section == 'keyPersonnel') { echo 'class="active_item"'; } ?>> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-user3"></span> <span id="u1sp7yfb" class="uif-innerText"> Key Personnel </span> <span class="arrow icon-angle-right"></span> </a>
                <ul class="submenu uif-listLayout">
                    <li <?php if ($page == 'keyPersonnel-personnel') { echo 'class="active_item"'; } ?>> <a id="u3s0ej9" class="uif-actionLink" tabindex="0" data-role="Action" href="prop-personnel-start.php"> Personnel </a> </li>
                    <li <?php if ($page == 'keyPersonnel-creditAllocation') { echo 'class="active_item"'; } ?>> <a id="u3s0ek4" class="uif-actionLink" tabindex="0" data-role="Action" href="#"> Credit Allocation </a> </li>
                </ul>
            </li>
            <li <?php if ($page == 'compliance') { echo 'class="active_item"'; } ?>> <a id="u79gehe" class="uif-navigationActionLink"><span class="icon-ok"></span><span class="uif-innerText">Compliance</span></a> </li>
            <li <?php if ($page == 'attachments') { echo 'class="active_item"'; } ?>> <a id="u79gei9" class="uif-navigationActionLink"><span class="icon-paper-clip"></span><span class="uif-innerText">Attachments</span></a> </li>
            <li <?php if ($page == 'questionnaire') { echo 'class="active_item"'; } ?>> <a id="u79gej4" class="uif-navigationActionLink"><span class="icon-question2"></span><span class="uif-innerText">Questionnaire</span></a> </li>
            <li <?php if ($page == 'budgets') { echo 'class="active_item"'; } ?>> <a id="u79gej4" class="uif-navigationActionLink" href="prop-budget.php"><span class="icon-money"></span><span class="uif-innerText">Budgets</span></a> </li>
            <li <?php if ($page == 'supplimentalInfo') { echo 'class="active_item"'; } ?>> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-bell2"></span> <span id="umhfcyq" class="uif-innerText"> Supplemental Information </span> <span class="arrow icon-angle-right"></span> </a>
                <ul class="submenu uif-listLayout">
                    <li <?php if ($page == 'supplimentalInfo-1') { echo 'class="active_item"'; } ?>> <a id="ul96bkq" class="uif-actionLink" tabindex="0" data-role="Action"> Page 1 </a> </li>
                    <li <?php if ($page == 'supplimentalInfo-2') { echo 'class="active_item"'; } ?>> <a id="ul96bll" class="uif-actionLink" tabindex="0" data-role="Action"> Page 2 </a> </li>
                </ul>
            </li>
            <li <?php if ($page == 'summary') { echo 'class="active_item"'; } ?>> <a id="u79gelp" class="uif-navigationActionLink"><span class="icon-signout"></span><span class="uif-innerText">Summary/Submit</span></a> </li>
        </ul>
    </div>
</nav>
