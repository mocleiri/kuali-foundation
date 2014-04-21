<nav id="Uif-Navigation" style="position:absolute; display:">
    <div id="" class="uif-menuNavigationGroup">
        <div class="sidebar-collapse"><span class="icon-angle-left"></span></div>
        <!-- NAVIGATION -->
        <ul class="nav nav-list">
            <li <?php if ($page == 'role-overview') { echo 'class="active"'; } ?>><a href="kim-role-overview.php" id="" class="uif-navigationActionLink" ><span class="icon-user"></span><span class="uif-innerText">Role Overview</span></a></li>
            <li <?php if ($page == 'role-permissions') { echo 'class="active"'; } ?>><a href="kim-role-permissions.php" id="" class="uif-navigationActionLink" ><span class="icon-list-alt"></span><span class="uif-innerText">Permissions </span></a></li>
            <li <?php if ($page == 'role-responsibilities') { echo 'class="active"'; } ?>><a href="kim-role-responsibilities.php" id="" class="uif-navigationActionLink" ><span class="icon-puzzle"></span><span class="uif-innerText">Responsibilities</span></a></li>
            
            
            <li <?php if ($page == 'role-assignees') { echo 'class="active"'; } ?>><a href="kim-role-assignees.php" id="" class="uif-navigationActionLink" ><span class="icon-group"></span><span class="uif-innerText">Assignees</span></a></li>
            
            
            
            <li <?php if ($page == 'role-routinginfo') { echo 'class="active"'; } ?>><a href="kim-role-routinginfo.php" id="" class="uif-navigationActionLink" ><span class="icon-file-alt"></span><span class="uif-innerText">Routing Information</span></a></li>
        </ul>
    </div>
</nav>
