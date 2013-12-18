<div id="sidebar" class="uif-navigation" style="margin-left:-15px; ">
	<div id="sidebar-collapse">
		<i class="icon-angle-left"></i>
	</div>
	<ul class="nav nav-list">
		<li id="menu-basics" aria-owns="submenu-basics" aria-controls="submenu-basics" <?php if ($section == "basics") { echo 'class="active" aria-expanded="true"'; } else { echo 'class="" aria-expanded="false"'; } ?>> <a href="prop.basics.details.php" class="dropdown-toggle"> <i class="icon-file-alt"></i><span>Basics</span><b class="arrow icon-angle-right"></b> </a>
			<ul id="submenu-basics" role="group" aria-labeledby="menu-basics" class="submenu">
				<li <?php if ($page == "basics-details") { echo 'class="active"'; } ?>><a href="prop.basics.details.php">Proposal Details</a></li>
				<li <?php if ($page == "basics-search") { echo 'class="active"'; } ?>><a href="prop.basics.oppsearch-search.php">S2S Opportunity Search</a></li> 
				<li <?php if ($page == "basics-deliveryinfo") { echo 'class="active"'; } ?>><a href="prop.basics.deliveryinfo-alt.php">Delivery Info</a></li>
				<li <?php if ($page == "basics-sponsors") { echo 'class="active"'; } ?>><a href="prop.basics.sponsor.php">Sponsor &amp; Program Information</a></li>
				<li <?php if ($page == "basics-orgloc") { echo 'class="active"'; } ?>><a href="prop.basics.orgloc.php">Organization &amp; Location</a></li>
			</ul>
		</li>
		<li id="menu-personnel" aria-owns="submenu-personnel" aria-controls="submenu-personnel" <?php if ($section == "keypersonnel") { echo 'class="active" aria-expanded="true"'; } else { echo 'class="" aria-expanded="false"'; } ?>> <a href="prop.keypersonnel.start.php" class="dropdown-toggle"><i class="icon-user"></i> <span>Key Personnel</span><b class="arrow icon-angle-right"></b> </a>
			<ul id="submenu-personnel" role="group" aria-labeledby="menu-personnel" class="submenu">
				<li <?php if ($page == "keypersonnel-start") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.start.php">Personnel</a></li>
				<li <?php if ($page == "keypersonnel-intelcredit") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.credit.original.php">Credit Allocation</a></li>
			</ul>
		</li>
		<li <?php if ($page == "compliance") { echo 'class="active"'; } ?>><a href="prop.compliance.php"><i class="icon-ok"></i> <span>Compliance</span> </a></li>
		<li <?php if ($page == "attachments") { echo 'class="active"'; } ?>><a href="prop.attachments.php"><i class="icon-paper-clip"></i> <span>Attachments</span> </a></li>
		<li <?php if ($page == "questions") { echo 'class="active"'; } ?>><a href="prop.questionnaire.php"><i class="icon-question"></i><span> Questionnaire</span></a></li>
		<li id="menu-budget" aria-owns="submenu-budget" aria-controls="submenu-budget" <?php if ($section == "budget") { echo 'class="active" aria-expanded="true"'; } else { echo 'class="" aria-expanded="false"'; } ?>> <a href="#" class="dropdown-toggle"><i class="icon-money"></i> <span>Budget</span> <b class="arrow icon-angle-right"></b></a>
			<ul id="submenu-budget" role="group" aria-labeledby="menu-budget" class="submenu">
				<li <?php if ($page == "budget-summary") { echo 'class="active"'; } ?>><a href="prop.budget.summary.php">Summary</a></li>
				<li <?php if ($page == "budget-params") { echo 'class="active"'; } ?>><a href="prop.budget.params.php">Parameters</a></li>
				<li <?php if ($page == "budget-rates") { echo 'class="active"'; } ?>><a href="prop.budget.rates.php">Rates</a></li>
				<li <?php if ($page == "budget-personnel") { echo 'class="active"'; } ?>><a href="prop.budget.personnel.php">Personnel</a></li>
				<li <?php if ($page == "budget-nonpersonnel") { echo 'class="active"'; } ?>><a href="prop.budget.nonpersonnel.php">Non-Personnel</a></li>
				<li <?php if ($page == "budget-distincome") { echo 'class="active"'; } ?>><a href="prop.budget.distincome.php">Distribution &amp; Income</a></li>
				<li <?php if ($page == "budget-modular") { echo 'class="active"'; } ?>><a href="prop.budget.modular.php">Modular</a></li>
				<li <?php if ($page == "budget-actions") { echo 'class="active"'; } ?>><a href="prop.budget.actions.php">Actions</a></li>
			</ul>
		</li>
		<li id="menu-institution" aria-owns="submenu-institution" aria-controls="submenu-institution" <?php if ($section == "instdata") { echo 'class="active" aria-expanded="true"'; } else { echo 'class="" aria-expanded="false"'; } ?>> <a href="#" class="dropdown-toggle"><i class="icon-bell"></i><span>Supplemental Information</span> <b class="arrow icon-angle-right"></b></a>
			<ul id="submenu-institution" role="group" aria-labeledby="menu-institution" class="submenu">
				<li <?php if ($page == "institution-page1") { echo 'class="active"'; } ?>><a href="prop.inst.1.php">Page 1</a></li>
				<li <?php if ($page == "institution-page2") { echo 'class="active"'; } ?>><a href="prop.inst.2.php">Page 2</a></li>
				<li <?php if ($page == "institution-page3") { echo 'class="active"'; } ?>><a href="prop.inst.3.php">Page 3</a></li>
			</ul>
		</li>

		<li <?php if ($page == "prop-submit") { echo 'class="active"'; } ?>><a href="prop.submit.php"><i class="icon-signout"></i><span>Submit</span></a></li>
	</ul>
</div>