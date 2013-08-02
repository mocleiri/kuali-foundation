<div id="ToC">
  <a href="#" class="visible-phone"><i class="icon icon-home"></i> Navigate</a>
  <ul style="display: block;">
    <li class="submenu <?php if( ($page == 'basics') || (strpos($page, "basics-") !== false) ) { echo 'open'; } ?>"><a href="prop.basics.details.php"><i class="cus-doc_lines_stright"></i><span>Basics </span></a>
      <ul>
        <li <?php if( $page == 'basics-details' ) { echo 'class="active"'; } ?> ><a href="prop.basics.details.php"><span>Proposal Details</span></a></li>
        <li <?php if( $page == 'basics-grantsgov' ) { echo 'class="active"'; } ?> ><a href="prop.basics.grantsgov.php"><span>Grants.gov</span></a></li>
        <li <?php if( $page == 'basics-sponsor' ) { echo 'class="active"'; } ?> ><a href="prop.basics.sponsor.php"><span>Sponsor &amp; Program Information</span></a></li>
        <li <?php if( $page == 'basics-orgloc' ) { echo 'class="active"'; } ?> ><a href="prop.basics.orgloc.php"><span>Organization &amp; Location</span></a></li>
        <li <?php if( $page == 'basics-deliveryinfo' ) { echo 'class="active"'; } ?> ><a href="prop.basics.deliveryinfo.php"><span>Delivery Info</span></a></li>
        <li <?php if( $page == 'basics-keywords' ) { echo 'class="active"'; } ?> ><a href="prop.basics.keywords.php"><span>Keywords</span></a></li>
      </ul>
    </li>
    <li class="submenu <?php if( ($page == 'personnel') || (strpos($page, "personnel-") !== false) ) { echo 'open'; } ?>"><a href="prop.keypersonnel.start.php"><i class="cus-user"></i><span>Key Personnel</span></a>
      <ul>
        <li <?php if( $page == 'personnel-pers' ) { echo 'class="active"'; } ?> ><a href="prop.keypersonnel.start.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'personnel-creditintel' ) { echo 'class="active"'; } ?> ><a href="prop.keypersonnel.creditintel.php"><span>Intellectual Credit</span></a></li>
         <li <?php if( $page == 'personnel-creditfa' ) { echo 'class="active"'; } ?> ><a href="prop.keypersonnel.creditfa.php"><span>F &amp; A Credit</span></a></li>
      </ul>
    </li>
    
    <!--  <li><a href="prop.access.php"><i class="icon icon-access"></i><span>Access</span></a></li>--> 
    
    <li <?php if( $page == 'compliance' ) { echo 'class="active"'; } ?>><a href="prop.compliance.php"><i class="cus-checkmark"></i><span>Compliance</span></a></li>
    <li class="submenu <?php if( ($page == 'attachments') || (strpos($page, "attachments-") !== false) ) { echo 'open'; } ?>"><a href="prop.attachments.proposal.php"><i class="cus-clip"></i><span>Attachments </span></a>
      <ul>
        <li <?php if( $page == 'attachments-proposal' ) { echo 'class="active"'; } ?> ><a href="prop.attachments.proposal.php"><span>Proposal</span></a></li>
        <li <?php if( $page == 'attachments-personnel' ) { echo 'class="active"'; } ?> ><a href="prop.attachments.personnel.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'attachments-internal' ) { echo 'class="active"'; } ?> ><a href="prop.attachments.internal.php"><span>Internal</span></a></li>
        <li <?php if( $page == 'attachments-abstracts' ) { echo 'class="active"'; } ?> ><a href="prop.attachments.abstracts.php"><span>Abstracts</span></a></li>
        <li <?php if( $page == 'attachments-notes' ) { echo 'class="active"'; } ?> ><a href="prop.attachments.notes.php"><span>Notes</span></a></li>
      </ul>
    </li>
    <li <?php if( $page == 'questionnaire' ) { echo 'class="active"'; } ?>><a href="prop.questionnaire.php"><i class="cus-question"></i><span>Questionnaire</span></a></li>
    
   
   
   
    <li class="submenu <?php if( ($page == 'budget') || (strpos($page, "budget-") !== false) ) { echo 'open'; } ?>">
      <a href="prop.budget.summary.php"><i class="cus-money"></i><span>Budget</span></a>
      <ul>
        <li <?php if( $page == 'budget-summary' ) { echo 'class="active"'; } ?> ><a href="prop.budget.summary.php"><span>Summary</span></a></li>
        <li <?php if( $page == 'budget-parameters' ) { echo 'class="active"'; } ?> ><a href="prop.budget.paramaters.php"><span>Parameters</span></a></li>
        <li <?php if( $page == 'budget-rates' ) { echo 'class="active"'; } ?> ><a href="prop.budget.rates.php"><span>Rates</span></a></li>
        <li <?php if( $page == 'budget-personnel' ) { echo 'class="active"'; } ?> ><a href="prop.budget.personnel.php"><span>Personnel</span></a></li>
        <li <?php if( $page == 'budget-nonpersonnel' ) { echo 'class="active"'; } ?> ><a href="prop.budget.nonpersonnel.php"><span>Non-Personnel</span></a></li>
        <li <?php if( $page == 'budget-distincome' ) { echo 'class="active"'; } ?> ><a href="prop.budget.distincome.php"><span>Distribution &amp; Income</span></a></li>
        <li <?php if( $page == 'budget-modular' ) { echo 'class="active"'; } ?> ><a href="prop.budget.modular.php"><span>Modular</span></a></li>
        <li <?php if( $page == 'budget-actions' ) { echo 'class="active"'; } ?> ><a href="prop.budget.actions.php"><span>Actions</span></a></li>
      </ul>
    </li>
   
   
    <li class="submenu <?php if( ($page == 'instspecdata') || (strpos($page, "instspecdata-") !== false) ) { echo 'open'; } ?>">
      <a href="prop.instspecdata.1.php"><i class="cus-bell"></i><span>Institution Data</span></a>
      <ul>
        <li <?php if( $page == 'instspecdata-1' ) { echo 'class="active"'; } ?> ><a href="prop.instspecdata.1.php"><span>Page 1</span></a></li>
        <li <?php if( $page == 'instspecdata-2' ) { echo 'class="active"'; } ?> ><a href="prop.instspecdata.2.php"><span>Page 2</span></a></li>
        <li <?php if( $page == 'instspecdata-3' ) { echo 'class="active"'; } ?> ><a href="prop.instspecdata.3.php"><span>Page 3</span></a></li>
      </ul>
    </li>
   
 
    <!--<li><a href="prop.medusa.php"><i class="icon icon-medusa"></i><span>Medusa</span></a></li>--> 
    
    <li <?php if( $page == 'review-summary' ) { echo 'class="active"'; } ?>><a href="prop.review.summary.php"><i class="cus-doc_export"></i><span>Summary/Submit</span></a></li>
    <!--<li <?php if( $page == 'review-actions' ) { echo 'class="active"'; } ?>><a href="prop.review.actions.php"><i class="icon icon-review"></i><span>Approver Actions</span></a></li>-->
    
  </ul>
</div>
