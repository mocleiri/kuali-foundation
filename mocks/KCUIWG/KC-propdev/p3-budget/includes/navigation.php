<nav id="Uif-Navigation">
    <div id="LabsProposal-Menu" class="uif-menuNavigationGroup" >
        <div class="sidebar-collapse">
            <span class="icon-angle-left"></span>
        </div>

        <!-- NAVIGATION -->
        <ul class="nav nav-list">
            <!-- <li <?php if ($page == 'budget-versions') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="versions.php"><span class="icon-file-alt"></span><span class="uif-innerText">Budget Versions</span></a></li> -->
            <li <?php if ($page == 'periods-and-totals') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="periods-and-totals.php"><span class="icon-sort-by-attributes-alt"></span><span class="uif-innerText">Budget Periods &amp; Totals</span></a></li>
            <li <?php if ($page == 'rates') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="rates.php"><span class="icon-tasks"></span><span class="uif-innerText">Rates</span></a></li>
            <li <?php if ($section == 'personnel') { echo 'class="active in"'; } ?>>
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">
                    <span class="icon-user3"></span>
                    <span class="uif-innerText" >Personnel Costs</span>
                    <span class="arrow icon-angle-right"></span>
                </a>
                <ul class="submenu uif-listLayout">
                    <li <?php if ($page == 'personnel-costs') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="personnel-roster.php">Project Personnel</a></li>
                    <li <?php if ($page == 'personnel-assign') { echo 'class="active"'; } ?>><a class="uif-actionLink" tabindex="0" href="personnel-assign.php">Assign Personnel to Periods</a></li>
                </ul>
            </li>
            <li <?php if ($page == 'non-personnel') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="non-personnel.php"><span class="icon-desktop"></span><span class="uif-innerText">Non-Personnel Costs</span></a></li>
            <li <?php if ($page == 'subawards') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="subawards.php"><span class="icon-file"></span><span class="uif-innerText">Subawards</span></a></li>
            <li <?php if ($page == 'cost-sharing') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="cost-sharing.php"><span class="icon-random"></span><span class="uif-innerText">Cost Sharing &amp; Unrecovered F&amp;A</span></a></li>
            <li <?php if ($page == 'project-income') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="project-income.php"><span class="icon-money"></span><span class="uif-innerText">Project Income</span></a></li>
            <li <?php if ($page == 'modular') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="modular.php"><span class="icon-ok"></span><span class="uif-innerText">Modular</span></a></li>
            <li <?php if ($page == 'notes') { echo 'class="active"'; } ?>><a class="uif-navigationActionLink" tabindex="0" href="notes.php"><span class="icon-th-list"></span><span class="uif-note">Budget Notes</span></a></li>
        </ul>
    </div>
</nav>