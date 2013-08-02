<!-- Subnavigation -->

<div id="ToC"><!-- <a href="#" class="visible-phone"><i class="icon icon-home"></i> Navigate</a>-->
  <ul style="display: block; ">
    <li class="submenu <?php if( ($page == 'basics') || (strpos($page, "basics-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Basics </span></a>
      <ul>
        <li <?php if( $page == 'basics-propdetails' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.propdetails.php"><span>Proposal Details</span></a></li>
        <li <?php if( $page == 'basics-grantsgov' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.grantsgov.php"><span>Grants.gov</span></a></li>
        <li <?php if( $page == 'basics-sponsproginfo' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.sponsproginfo.php"><span>Sponsor &amp; Program Information</span></a></li>
        <li <?php if( $page == 'basics-orgloc' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.orgloc.php"><span>Organization/Location</span></a></li>
        <li <?php if( $page == 'basics-deliveryinfo' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.deliveryinfo.php"><span>Delivery Info</span></a></li>
        <li <?php if( $page == 'basics-keywords' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.basics.keywords.php"><span>Keywords</span></a></li>
      </ul>
    </li>
    <li class="submenu <?php if( ($page == 'personnel') || (strpos($page, "personnel-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Key Personnel</span></a>
      <ul>
        <li <?php if( $page == 'personnel' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.keypersonnel.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'personnel-credit' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.keypersonnel.creditallocation.php"><span>Credit Allocation</span></a></li>
      </ul>
    </li>
    <li <?php if( $page == 'access' ) { echo 'class="active"'; } ?>><a href="prop.access.php"><i class="icon icon-chevron-right"></i><span>Access</span></a></li>
    <li <?php if( $page == 'compliance' ) { echo 'class="active"'; } ?>><a href="prop.compliance.php"><i class="icon icon-chevron-right"></i><span>Compliance</span></a></li>
    <li class="submenu <?php if( ($page == 'attachments') || (strpos($page, "attachments-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Attachments </span></a>
      <ul>
        <li <?php if( $page == 'attachments-proposal' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.attachments.proposal.php"><span>Proposal</span></a></li>
        <li <?php if( $page == 'attachments-personnel' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.attachments.personnel.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'attachments-internal' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.attachments.internal.php"><span>Internal</span></a></li>
        <li <?php if( $page == 'attachments-abstracts' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.attachments.abstracts.php"><span>Abstracts</span></a></li>
        <li <?php if( $page == 'attachments-notes' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.attachments.notes.php"><span>Notes</span></a></li>
      </ul>
    </li>
    <li <?php if( $page == 'questionnaire' ) { echo 'class="active"'; } ?>><a href="prop.questionnaire.php"><i class="icon icon-chevron-right"></i><span>Questionnaire</span></a></li>
    <li class="submenu <?php if( ($page == 'budget') || (strpos($page, "budget-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Budget <small style="margin-left: 8px;">(version 3)</small></span></a>
      <ul>
        <li <?php if( $page == 'budget-summary' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.summary.php"><span>Summary</span></a></li>
        <li <?php if( $page == 'budget-parameters' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.paramaters.php"><span>Parameters</span></a></li>
        <li <?php if( $page == 'budget-rates' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.rates.php"><span>Rates</span></a></li>
        <li <?php if( $page == 'budget-personnel' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.personnel.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'budget-nonpersonnel' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.nonpersonnel.php"><span>Non-Personnel</span></a></li>
        <li <?php if( $page == 'budget-distincome' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.distincome.php"><span>Distribution &amp; Income</span></a></li>
        <li <?php if( $page == 'budget-modular' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.modular.php"><span>Modular</span></a></li>
        <li <?php if( $page == 'budget-actions' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.budget.actions.php"><span>Actions</span></a></li>
      </ul>
    </li>

    <li class="submenu <?php if( ($page == 'instspecdata') || (strpos($page, "instspecdata-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Institution Specific Data</span></a>
      <ul>
        <li <?php if( $page == 'instspecdata-1' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.instspecdata.1.php"><span>Page 1</span></a></li>
        <li <?php if( $page == 'instspecdata-2' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.instspecdata.2.php"><span>Page 2</span></a></li>
        <li <?php if( $page == 'instspecdata-3' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.instspecdata.3.php"><span>Page 3</span></a></li>
      </ul>
    </li>
    <li <?php if( $page == 'medusa' ) { echo 'class="active"'; } ?>><a href="prop.medusa.php"><i class="icon icon-chevron-right"></i><span>Medusa</span></a></li>
    
    <li class="submenu <?php if( ($page == 'review') || (strpos($page, "review-") !== false) ) { echo 'open'; } ?>">
      <a href="#"><i class="icon icon-chevron-right"></i><span>Review &amp; Approve</span></a>
      <ul>
        <li <?php if( $page == 'review-summary' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.summary.php"><span>Proposal Summary</span></a></li>
        <?PHP
		/*
        <li <?php if( $page == 'review-persons' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.persons.php"><span>Key Persons</span></a></li>
        <li <?php if( $page == 'review-budget' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.budget.php"><span>Budget Summary</span></a></li>
        <li <?php if( $page == 'review-attachments' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.attachments.php"><span>Attachments</span></a></li>
        <li <?php if( $page == 'review-specialreview' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.specialreview.php"><span>Special Review</span></a></li>
        <li <?php if( $page == 'review-print' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.print.php"><span>Proposal Print</span></a></li>
        */
        ?>
        <li <?php if( $page == 'review-actions' ) { echo 'class="active"'; } ?> style="line-height:14px;"><a href="prop.review.actions.php"><span>Proposal Actions</span></a></li>
      </ul>
    </li>
    
  </ul>
</div>
<!-- // -->