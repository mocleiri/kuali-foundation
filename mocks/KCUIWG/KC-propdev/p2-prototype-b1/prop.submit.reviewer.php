<?php
# Variables
$page = 'prop-submit';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <div id="navbar" class="navbar" style="background:white">
    <div class="container">
      <button type="button" class="navbar-toggle navbar-btn collapsed" data-toggle="collapse" data-target="#sidebar"> <span class="icon-reorder"></span> </button>
      <header class="viewHeader">
        <div class="row">
          <div class="col-md-6"> 
            <!-- <h1> <span class="areaTitle">Proposal Development</span> <span class="headerText"> Proposal: #23533 </span> <a href="#"><span aria-hidden="false" class="icon-question-sign"> <span class="icon-text">help</span></span></a> <span class="supportTitle">PI: Ken Graves</span> </h1> -->
            <h1> <span class="areaTitle">Proposal Development</span> <span class="headerText"> Proposal: #23533 </span> <span class="supportTitle">PI: Edward Haskell</span> </h1>
          </div>
          <div class="col-md-6 visible-lg visible-md">
            <div class="metadata"><strong>Document Info</strong> <br>
              Doc Nbr: 2743<br>
              Initiator: thrclark<br>
              Status: Routing/Review<br>
              <div class="dropdown"><a href="#" class="dropdown-toggle" data-toggle="dropdown">more...</a>
                <div class="dropdown-menu" >
                  <h4> Document Info</h4>
                  <table class="table table-condensed">
                    <tbody>
                      <tr>
                        <th scope="row">Doc Nbr</th>
                        <td>2743</td>
                      </tr>
                      <tr>
                        <th scope="row">Initiator</th>
                        <td><a href="#">thrclark</a></td>
                      </tr>
                      <tr>
                        <th scope="row">Status</th>
                        <td>Routing/Review</td>
                      </tr>
                      <tr>
                        <th scope="row">PI</th>
                        <td>Edward Haskell</td>
                      </tr>
                      <tr>
                        <th scope="row">Created</th>
                        <td>04:27pm 07/09/2013</td>
                      </tr>
                      <tr>
                        <th scope="row">Updated</th>
                        <td>12:22pm 07/12/2013</td>
                      </tr>
                      <tr>
                        <th scope="row">Proposal Nbr</th>
                        <td>#23533</td>
                      </tr>
                      <tr>
                        <th scope="row">Sponsor Name</th>
                        <td>NIH</td>
                      </tr>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>
          </div>
        </div>
        <div class="doc-functions">
          <ul>
            <li><i class="icon icon-eye-open"></i> <a class="launch-modal" data-modal-page="modal/summary.php" href="#">Summary</a></li>
            <li> <i class="icon icon-share-alt"></i> <a class="launch-modal" data-modal-page="modal/routingtrail.php" href="#">Routing Trail</a></li>
             <li><i class="icon icon-bolt"></i><a class="launch-modal" data-modal-page="modal/#" href="#">Super User Actions</a></li>
            
            <!-- <li><i class="icon icon-question-sign"></i> <a href="#">Help</a></li>-->
          </ul>
        </div>
      </header>
    </div>
  </div>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
 <h3>Submit</h3>
        
        

        
        
          <div class="well">
            <div class="proposal-actions clearfix">
              <div class="pull-left"> <a href="#" class="btn btn-primary">Submit to Sponsor</a> </div>
              <div class="pull-right">   <div class="btn-group"> <a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Approve <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Approve entire proposal</a></li>
                  <li><a href="#">Approve and pass to next reviewer</a></li>
                </ul>
              </div>
              <div class="btn-group"> <a href="#" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">Reject <span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="#">Reject entire proposal</a></li>
                  <li><a href="#">Line-item rejection</a></li>
                </ul>
              </div>
              <a href="#" class="btn btn-default">Recall</a></div>
            </div>
          </div>
          <span><a href="#">Send Notifications</a> | <a href="#">View Route Log</a></span>
          
        <div class="uif-toolbar">   <button class="btn btn-default btn-xs"><span aria-hidden="true" class="icon-plus"></span> Add Comment</button></div>
          
                  
        <table class="table table-condensed">
  <tr>
    <th scope="col">Date</th>
    <th scope="col">Person</th>
    <th scope="col">Comment</th>
  </tr>
  <tr>
    <td>03/22/2013</td>
    <td style="white-space:nowrap">Oscar Peterson</td>
    <td>Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sed lorem in risus ornare semper vitae sit amet mauris. Nam imperdiet sollicitudin odio vel convallis. Nullam non malesuada nisl. Nulla tempor fermentum ornare. Fusce sollicitudin lobortis arcu ut malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lacinia interdum eleifend. Cras nec hendrerit nulla. Nulla facilisi. In lectus arcu, consectetur vitae auctor in, condimentum at libero. Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. Suspendisse at quam tortor, ac pulvinar augue. </td>
  </tr>
  <tr>
    <td>03/21/2013 </td>
    <td>Horace Silver</td>
    <td> Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. </td>
  </tr>
</table>
        
        
        <!-- // --> 
        
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>