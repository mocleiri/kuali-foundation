<nav id="Uif-Navigation">
    <div id="uif_budget_navigation_menu" class="uif-menuNavigationGroup" >
        <div class="sidebar-collapse"> <span class="icon-angle-left"></span> </div>
            <!-- NAVIGATION -->
        <ul class="nav nav-list">
			  <li <?php if ($page == 'periods-and-totals') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-periods.php"><span class="icon-sort-by-attributes-alt"></span><span class="uif-innerText">Periods &amp; Totals</span></a></li>
            <li <?php if ($page == 'rates') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-rates.php"><span class="icon-tasks"></span><span class="uif-innerText">Rates</span></a></li>
            <li <?php if ($section == 'personnel') { echo 'class="active in open"'; } ?>> <a href="#" class="dropdown-toggle" data-toggle="dropdown"> <span class="icon-user3"></span> <span class="uif-innerText" >Personnel Costs</span> <span class="arrow icon-angle-right"></span></a>
                <ul class="submenu uif-listLayout">
                    <li <?php if ($page == 'personnel-costs') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="budget-ng-personnelCosts-projPersonnel1.php">Project Personnel</a></li>
                    <li <?php if ($page == 'personnel-assign') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="budget-ng-personnelCosts-persPeriod.php">Assign Personnel to Periods</a></li>
                </ul>
            </li>
            <li <?php if ($page == 'non-personnel') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-non-personnel.php"><span class="icon-beaker"></span><span class="uif-innerText">Non-Personnel Costs</span></a></li>
            <li <?php if ($page == 'subawards') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-subawards.php"><span class="icon-file"></span><span class="uif-innerText">Subawards</span></a></li>
                 <li <?php if ($section == 'institute') { echo 'class="active in open"'; } ?>> <a href="#" class="dropdown-toggle" data-toggle="dropdown-b"><span class="icon-office"></span> <span class="uif-innerText" >Institutional Commitments</span><span class="arrow icon-angle-right"></span></a>
                <ul class="submenu uif-listLayout" >
                    <li <?php if ($page == 'cost-sharing') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="budget-ng-cost-sharing.php">Cost Sharing</a></li>
                    <li <?php if ($page == 'unrecovered') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="budget-ng-unrecovered-fa.php">Unrecovered F&amp;A</a></li>
                </ul>
            </li>
            <li <?php if ($page == 'project-income') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-income.php"><span class="icon-money"></span><span class="uif-innerText">Project Income</span></a></li>
            <li <?php if ($page == 'modular') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-modular.php"><span class="icon-ok"></span><span class="uif-innerText">Modular</span></a></li>
            <li <?php if ($page == 'notes') { echo 'class="active"'; } ?>><a  class="uif-actionLink" tabindex="0" href="budget-ng-notes.php"><span class="icon-th-list"></span><span class="uif-innerText">Budget Notes</span></a></li>
            <li <?php if ($page == 'summary') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="budget-ng-summary.php"><span class="icon-file"></span><span class="uif-innerText">Budget Summary</span></a></li>
       </ul>
    </div>
</nav>