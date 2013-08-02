<!-- Subnavigation -->
<div id="ToC" <?php if( $page == 'start' ) { echo 'class="disabled"'; } ?>>
    <ul style="display: block;">
      <li <?php if( $page == 'basics' || $page == 'start' ) { echo 'class="active"'; } ?>><a href="prop.proposal.php"><i class="icon icon-chevron-right"></i><span>Basics</span></a></li>
      <li <?php if( $page == 'personnel' ) { echo 'class="active"'; } ?>><a href="prop.keypersonnel.php"><i class="icon icon-chevron-right"></i><span>Key Personnel</span></a></li>
      <li <?php if( $page == 'access' ) { echo 'class="active"'; } ?>><a href="prop.permissions.php"><i class="icon icon-chevron-right"></i><span>Access</span></a></li>
      <li <?php if( $page == 'compliance' ) { echo 'class="active"'; } ?>><a href="prop.specialreview.php"><i class="icon icon-chevron-right"></i><span>Compliance</span></a></li>
      <li <?php if( $page == 'attachments' ) { echo 'class="active"'; } ?>><a href="prop.abstracts.php"><i class="icon icon-chevron-right"></i><span>Attachments</span></a></li>
      <li <?php if( $page == 'questionnaire' ) { echo 'class="active"'; } ?>><a href="prop.questions.php"><i class="icon icon-chevron-right"></i><span>Questionnaire</span></a></li>
      <li class="submenu <?php if( ($page == 'budget') || (strpos($page, "budget-") !== false) ) { echo 'open'; } ?>"><a href="#"><i class="icon icon-chevron-right"></i><span>Budget <small style="margin-left: 8px;">(version 3)</small></span></a>
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
      <li <?php if( $page == 'customdata' ) { echo 'class="active"'; } ?>><a href="prop.customdata.php"><i class="icon icon-chevron-right"></i><span>Institution Specific Data</span></a></li>
      <li <?php if( $page == 'medusa' ) { echo 'class="active"'; } ?>><a href="prop.medusa.php"><i class="icon icon-chevron-right"></i><span>Medusa</span></a></li>
      <li <?php if( $page == 'review' ) { echo 'class="active"'; } ?>><a href="prop.review.php"><i class="icon icon-chevron-right"></i><span>Review &amp; Approve</span></a></li>
    </ul>
</div>
<!-- // -->