<div class="uif-navigation open" id="sidebar">
	<button id="nav-toggle" class="open" href="#" aria-hidden="true"><span class="icon-expand"></span></button>

	<ul>
		<li <?php if ($section == "basics") { echo 'class="has-sub expanded"'; } else { echo 'class="has-sub"'; } ?>> <a href="prop.basics.details.php"><span aria-hidden="true" class="icon-file-alt"></span> <span class="nav-label">Basics</span></a>
			<ul class="sub">
				<li <?php if ($page == "basics-details") { echo 'class="active"'; } ?>><a href="prop.basics.details.php"><span class="nav-label">Proposal Details</span></a></li>
				<li <?php if ($page == "basics-search") { echo 'class="active"'; } ?>><a href="prop.basics.oppsearch-search.php"><span class="nav-label">Opportunity Search</span></a></li> 
				<li <?php if ($page == "basics-sponsors") { echo 'class="active"'; } ?>><a href="prop.basics.sponsor.php"><span class="nav-label">Sponsor &amp; Program Information</span></a></li>
				<li <?php if ($page == "basics-orgloc") { echo 'class="active"'; } ?>><a href="prop.basics.orgloc.php"><span class="nav-label">Organization &amp; Location</span></a></li>
				<li <?php if ($page == "basics-deliveryinfo") { echo 'class="active"'; } ?>><a href="prop.basics.deliveryinfo.php"><span class="nav-label">Delivery Info</span></a></li>
				<li <?php if ($page == "basics-keywords") { echo 'class="active"'; } ?>><a href="prop.basics.keywords.php"><span class="nav-label">Keywords</span></a></li>
			</ul>
		</li>
		<li <?php if ($section == "keypersonnel") { echo 'class="has-sub expanded"'; } else { echo 'class="has-sub"'; } ?>><a href="prop.keypersonnel.start.php"><span aria-hidden="true" class="icon-user"></span> <span class="nav-label">Key Pesonnel</span></a>
			<ul class="sub">
				<li <?php if ($page == "keypersonnel-start") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.start.php"><span class="nav-label">Personnel</span></a></li>
				<li <?php if ($page == "keypersonnel-intelcredit") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.creditintel.php"><span class="nav-label">Intellectual Credit</span></a></li>
				<li <?php if ($page == "keypersonnel-facredit") { echo 'class="active"'; } ?>><a href="prop.keypersonnel.creditfa.php"><span class="nav-label">F&amp:A Credit</span></a></li>
			</ul>
		</li>
		<li <?php if ($page == "compliance") { echo 'class="active"'; } ?>><a href="prop.compliance.php"><span aria-hidden="true" class="icon-ok"></span> <span class="nav-label">Compliance</span></a></li>
		<li <?php if ($section == "attachments") { echo 'class="has-sub expanded"'; } else { echo 'class="has-sub"'; } ?>><a href="prop.attachments.proposal.php"><span aria-hidden="true" class="icon-paper-clip"></span> <span class="nav-label">Attachments</span></a>
			<ul class="sub">
				<li <?php if ($page == "attach-proposal") { echo 'class="active"'; } ?>><a href="prop.attachments.proposal.php"><span class="nav-label">Proposal</span></a></li>
				<li <?php if ($page == "attach-personnel") { echo 'class="active"'; } ?>><a href="prop.attachments.personnel.php"><span class="nav-label">Personnel</span></a></li>
				<li <?php if ($page == "attach-internal") { echo 'class="active"'; } ?>><a href="prop.attachments.internal.php"><span class="nav-label">Internal</span></a></li>
				<li <?php if ($page == "attach-abstracts") { echo 'class="active"'; } ?>><a href="prop.attachments.abstracts.php"><span class="nav-label">Abstracts</span></a></li>
				<li <?php if ($page == "attach-notes") { echo 'class="active"'; } ?>><a href="prop.attachments.notes.php"><span class="nav-label">Notes</span></a></li>
			</ul>
		</li>
		<li <?php if ($page == "questions") { echo 'class="active"'; } ?>><a href="prop.questionnaire.php"><span aria-hidden="true" class="icon-question"></span> <span class="nav-label">Questionnaire</span></a></li>
		<li <?php if ($section == "budget") { echo 'class="has-sub expanded"'; } else { echo 'class="has-sub"'; } ?>><a href="prop.budget.summary.php"><span aria-hidden="true" class="icon-money"></span> <span class="nav-label">Budget</span></a>
			<ul class="sub">
				<li <?php if ($page == "budget-summary") { echo 'class="active"'; } ?>><a href="prop.budget.summary.php"><span class="nav-label">Summary</span></a></li>
				<li <?php if ($page == "budget-params") { echo 'class="active"'; } ?>><a href="prop.budget.params.php"><span class="nav-label">Parameters</span></a></li>
				<li <?php if ($page == "budget-rates") { echo 'class="active"'; } ?>><a href="prop.budget.rates.php"><span class="nav-label">Rates</span></a></li>
				<li <?php if ($page == "budget-personnel") { echo 'class="active"'; } ?>><a href="prop.budget.personnel.php"><span class="nav-label">Personnel</span></a></li>
				<li <?php if ($page == "budget-nonpersonnel") { echo 'class="active"'; } ?>><a href="prop.budget.nonpersonnel.php"><span class="nav-label">Non-Personnel</span></a></li>
				<li <?php if ($page == "budget-distincome") { echo 'class="active"'; } ?>><a href="prop.budget.distincome.php"><span class="nav-label">Distribution &amp; Income</span></a></li>
				<li <?php if ($page == "budget-modular") { echo 'class="active"'; } ?>><a href="prop.budget.modular.php"><span class="nav-label">Modular</span></a></li>
				<li <?php if ($page == "budget-actions") { echo 'class="active"'; } ?>><a href="prop.budget.actions.php"><span class="nav-label">Actions</span></a></li>
			</ul>
		</li>
		<li <?php if ($section == "instdata") { echo 'class="has-sub expanded"'; } else { echo 'class="has-sub"'; } ?>><a href="prop.inst.1.php"><span aria-hidden="true" class="icon-bell"></span> <span class="nav-label">Institution Data</span></a>
			<ul class="sub">
				<li <?php if ($page == "institution-page1") { echo 'class="active"'; } ?>><a href="prop.inst.1.php"><span class="nav-label">Page 1</span></a></li>
				<li <?php if ($page == "institution-page2") { echo 'class="active"'; } ?>><a href="prop.inst.2.php"><span class="nav-label">Page 2</span></a></li>
				<li <?php if ($page == "institution-page3") { echo 'class="active"'; } ?>><a href="prop.inst.3.php"><span class="nav-label">Page 3</span></a></li>
			</ul>
		</li>
		<li <?php if ($page == "prop-summary") { echo 'class="active"'; } ?>><a href="prop.summary.php"><span aria-hidden="true" class="icon-signout"></span> <span class="nav-label">Summary/Submit</span></a></li>
	</ul>
</div>