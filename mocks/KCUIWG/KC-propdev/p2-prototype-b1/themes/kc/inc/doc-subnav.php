
      <div id="sidebar" class="uif-navigation" style="margin-left:-15px; ">
      
         <div id="sidebar-collapse"> <i class="icon-angle-left"></i> </div>
         
         


	 <ul class="nav nav-list">
		<li <?php if ($section == "basics") { echo 'class="active"'; } else { echo 'class=""'; } ?>> <a href="prop.basics.details.php" class="dropdown-toggle"> <i class="icon-file-alt"></i><span>Basics</span><b class="arrow icon-angle-right"></b> </a>
			<ul class="submenu">
				<li <?php if ($page == "basics-details") { echo 'class="active"'; } ?>><a href="prop.basics.details.php">Proposal Details</a></li>
				<li <?php if ($page == "basics-search") { echo 'class="active"'; } ?>><a href="prop.basics.oppsearch-search.php">Opportunity Search</a></li> 
                <li <?php if ($page == "basics-deliveryinfo") { echo 'class="active"'; } ?>><a href="prop.basics.deliveryinfo.php">Delivery Info</a></li>
				<li <?php if ($page == "basics-sponsors") { echo 'class="active"'; } ?>><a href="prop.basics.sponsor.php">Sponsor &amp; Program Information</a></li>
				<li <?php if ($page == "basics-orgloc") { echo 'class="active"'; } ?>><a href="prop.basics.orgloc.php">Organization &amp; Location</a></li>
				
	
			</ul>
		</li>
		<li <?php if ($section == "keypersonnel") { echo 'class="active"'; } else { echo 'class=""'; } ?>> <a href="prop.keypersonnel.start.php" class="dropdown-toggle"><i class="icon-user"></i> <span>Key Personnel</span><b class="arrow icon-angle-right"></b> </a>
			<ul class="submenu">
				<li <?php if ($page == "keypersonnel-start") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.start.php">Personnel</a></li>
				<li <?php if ($page == "keypersonnel-intelcredit") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.creditintel.php">Intellectual Credit</a></li>
				<li <?php if ($page == "keypersonnel-facredit") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.creditfa.php">F&amp:A Credit</a></li>
			</ul>
		</li>
		<li <?php if ($page == "compliance") { echo 'class="active"'; } ?>><a href="prop.compliance.php"><i class="icon-ok"></i> <span>Compliance</span> </a></li>
		<li <?php if ($section == "attachments") { echo 'class="active"'; } else { echo 'class=""'; } ?>> <a href="#" class="dropdown-toggle"><i class="icon-paper-clip"></i><span>Attachments</span> <b class="arrow icon-angle-right"></b></a>
			<ul class="submenu">
				<li <?php if ($page == "attach-proposal") { echo 'class="active"'; } ?>><a href="prop.attachments.proposal.php">Proposal</a></li>
				<li <?php if ($page == "attach-personnel") { echo 'class="active"'; } ?>><a href="prop.attachments.personnel.php">Personnel</a></li>
				<li <?php if ($page == "attach-internal") { echo 'class="active"'; } ?>><a href="prop.attachments.internal.php">Internal</a></li>
				<li <?php if ($page == "attach-abstracts") { echo 'class="active"'; } ?>><a href="prop.attachments.abstracts.php">Abstracts</a></li>
				<li <?php if ($page == "attach-notes") { echo 'class="active"'; } ?>><a href="prop.attachments.notes.php">Notes</a></li>
			</ul>
		</li>
		<li <?php if ($page == "questions") { echo 'class="active"'; } ?>><a href="prop.questionnaire.php"><i class="icon-question"></i><span> Questionnaire</span></a></li>
		<li <?php if ($section == "budget") { echo 'class="active"'; } else { echo 'class=""'; } ?>> <a href="#" class="dropdown-toggle"><i class="icon-money"></i> <span>Budget</span> <b class="arrow icon-angle-right"></b></a>
			<ul class="submenu">
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
		<li <?php if ($section == "instdata") { echo 'class="active"'; } else { echo 'class=""'; } ?>> <a href="#" class="dropdown-toggle"><i class="icon-bell"></i><span>Institution Data</span> <b class="arrow icon-angle-right"></b></a>
			<ul class="submenu">
				<li <?php if ($page == "institution-page1") { echo 'class="active"'; } ?>><a href="prop.inst.1.php">Page 1</a></li>
				<li <?php if ($page == "institution-page2") { echo 'class="active"'; } ?>><a href="prop.inst.2.php">Page 2</a></li>
				<li <?php if ($page == "institution-page3") { echo 'class="active"'; } ?>><a href="prop.inst.3.php">Page 3</a></li>
			</ul>
		</li>
		<li <?php if ($page == "prop-summary") { echo 'class="active"'; } ?>><a href="prop.summary.php"><i class="icon-signout"></i><span>Summary/Submit</span></a></li>
	</ul>
</div>

