<!-- Header -->
<div id="header" class="header" role="banner">
  <header> 
    
    <!-- Global items -->
    <div id="global" class="clearfix">
      <div class="brand">
        <ul class="nav nav-tabs">
          <li class="active"><a href="index.php">Researcher</a></li>
          <li><a href="#">Unit</a></li>
          <li><a href="#">Central Admin</a></li>
          <li><a href="#">Maintenance</a></li>
          <li><a href="#">System Admin</a></li>
        </ul>
      </div>
      <div class="toolset">
        <ul>
          <li><a href="#" class="my-account">Logout</a></li>
          <li><a href="#" class="action-list">Action List</a></li>
        </ul>
      </div> 
    </div>
    <!-- // --> 
    
    <!-- Document items -->
    <div id="document" class="clearfix">
      <div class="title">
        <div class="title-name">
          <a href="#"><i class="icon-white icon-home"></i>Portal</a> &raquo; Doc #1234 Kuali Coeus Prototype A
        </div>
        <div class="title-author">
          by Test User
        </div>
      </div>
    </div>

    <!-- Secondary -->

    <!-- //-->
    <div id="secondary" class="clearfix">
      
      <div class="meta">
        <ul>
          <li><span>Created:</span><?php if( $page !== 'start' ) { echo date('m-d-Y'); } else { echo '---'; } ?></li>
          <li><span>Updated:</span><?php if( $page !== 'start' ) { echo date('m-d-Y'); } else { echo '---'; } ?></li>
          <?php if( $page !== 'start' ) { ?>
          <li>
            <a href="#" class="show-popover" rel="popover" data-placement="bottom" data-original-title="Document meta information" data-content="
            <ul>
              <li><p><span>Proposal Number:</span>3341</p></li>
              <li><p><span>Initiator:</span>Megatron</p></li>
              <li><p><span>PI:</span>Sam Witwicky</p></li>
              <li><p><span>Sponsor:</span>Optimus Prime</p></li>
            </ul>
            ">See more</a>
          </li>
          <?php } ?>
        </ul>
      </div>

      <!-- Tools -->
      <div class="btn-group">
        <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">Actions<span class="caret"></span></a>
        <ul class="dropdown-menu">
          <li><a href="#"><i class="icon-ok"></i>Data validation</a></li>
          <li><a href="#"><i class="icon-list-alt"></i>Proposal hierarchy</a></li>
          <li><a href="#"><i class="icon-print"></i>Print</a></li>
          <li><a href="#"><i class="icon-file"></i>Copy document</a></li>
          <li class="divider"></li>
          <li><a href="#"><i class="icon-share"></i>Routing</a></li>
          <li><a href="#"><i class="icon-download-alt"></i>Ad hoc recipients</a></li>
        </ul>
      </div>

      <div class="proposal-stats">
        <span class="show-popover" rel="popover" data-placement="bottom" data-original-title="Proposal completion" data-content="
          <ul>
            <li>
              <strong>Basics</strong>
              <div class='progress'>
                <div class='bar' style='width: 40%;'></div>
              </div>
            </li>
            <li>
              <strong>Key Personnel</strong>
              <div class='progress'>
                <div class='bar' style='width: 33%;'></div>
              </div>
            </li>
            <li>
              <strong>Access</strong>
              <div class='progress'>
                <div class='bar' style='width: 66%;'></div>
              </div>
            </li>
            <li>
              <strong>Compliance</strong>
              <div class='progress'>
                <div class='bar complete' style='width: 100%;'></div>
              </div>
            </li>
            <li>
              <strong>Attachments</strong>
              <div class='progress'>
                <div class='bar complete' style='width: 100%;'></div>
              </div>
            </li>
            <li>
              <strong>Questionnaire</strong>
              <div class='progress'>
                <div class='bar' style='width: 41%;'></div>
              </div>
            </li>
            <li>
              <strong>Budget</strong>
              <div class='progress'>
                <div class='bar' style='width: 23%;'></div>
              </div>
            </li>
            <li>
              <strong>Institution Specific Data</strong>
              <div class='progress'>
                <div class='bar' style='width: 12%;'></div>
              </div>
            </li>
            <li>
              <strong>Medusa</strong>
              <div class='progress'>
                <div class='bar' style='width: 1%;'></div>
              </div>
            </li>
          </ul>
        ">Completion:</span>
        <div class="progress clearfix">
          <div class="bar" style="width: 60%;"></div>
        </div>
        <span>60%</span>
      </div>
      <!-- // --> 
      
    </div>
    <!-- // --> 
  </header>
</div>