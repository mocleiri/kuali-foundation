<?php
# Variables
$page = 'prop-summary';

# Includes
require_once( 'themes/kc/inc/head.php' );
require_once( 'themes/kc/inc/nav.php' );
require_once( 'themes/kc/inc/toolbar.php' );
?>

<section id="main">
  <?php require_once( 'themes/kc/inc/bs-unifiedViewHeader.php' ); ?>
  <div id="Uif-ViewContentWrapper" class="uif-viewContentWrapper">
    <div class="container-fluid" style="">
      <?php require_once( 'themes/kc/inc/doc-subnav.php' ); ?>
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper">

        <h3>Budget Summary and Submission</h3>

        <ul id="prop_summary_tabs" class="nav nav-tabs">
            <li class="active"><a href="#summary" data-toggle="tab">Summary</a></li>
            <li><a href="#submit" data-toggle="tab">Submit</a></li>
        </ul>

        <div class="tab-content">
            <div class="tab-pane active" id="summary">
                <div class="box">
                    <div class="boxHeader">
                        
                            <h3>Workflow routing</h3>
                        
                    </div>
                    <div class="boxContent">
                        <div class="well">
                            <h4>Proposal progress</h4>
                            <div class="progress-details">
                                <div class="empty complete" style="width: 20%">In Progress</div>
                                <div class="empty complete" style="width: 20%">Routing &amp; Review</div>
                                <div class="empty active" style="width: 20%">Final Institutional Review</div>
                                <div class="empty" style="width: 20%">Approved</div>
                                <div class="empty" style="width: 20%">Submitted to Sponsor</div>
                            </div>
                            <div class="progress">
                                <div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
                                <div class="progress-bar progress-bar-success" style="width: 20%;" title="Completed" role="progressbar" aria-valuenow="20" aria-valuemin="0" aria-valuemax="20"></div>
                                <div class="progress-bar progress-bar-warning" style="width: 20%;" title="In Progress" role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
                                <div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
                                <div class="progress-bar progress-bar-danger" style="width: 20%; background-color: #ccc; color: #999; text-shadow: none;" title="Not Started" aria-valuenow="0" aria-valuemin="0" aria-valuemax="20"></div>
                            </div>
                        </div>

                        <div class="panel-group" id="accordion2">
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span aria-hidden="true" class="icon-caret-right"></span> Proposal Details</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse1" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span aria-hidden="true" class="icon-caret-right"></span> Key Personnel</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse2" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse3"><span aria-hidden="true" class="icon-caret-right"></span> Compliance</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse3" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse4"><span aria-hidden="true" class="icon-caret-right"></span> Attachments</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse4" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse5"><span aria-hidden="true" class="icon-caret-right"></span> Questionnaire</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse5" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse6"><span aria-hidden="true" class="icon-caret-right"></span> Budget</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse6" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                            <div class="panel panel-default">
                                <div class="panel-heading">
                                    <div class="row">
                                        <div class="col-md-6">
                                            <h4 class="panel-title"><a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse7"><span aria-hidden="true" class="icon-caret-right"></span> Institution Data</a></h4>
                                        </div>
                                    </div>
                                </div>
                                <div id="collapse7" class="panel-collapse collapse">
                                    <div class="panel-body">
                                        Stuff here...
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div class="tab-pane" id="submit">
                <div class="box">
                    <div class="boxHeader">
                        
                            <h3>Workflow actions</h3>
                        
                    </div>
                    <div class="boxContent">
                        <div class="well">
                            <h4>Proposal actions</h4>

                            <div class="proposal-actions clearfix">
                                <div class="btn-group">
                                    <a href="#" class="btn btn-success dropdown-toggle" data-toggle="dropdown">Approve <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Approve entire proposal</a></li>
                                        <li><a href="#">Approve and pass to next reviewer</a></li>
                                    </ul>
                                </div>
                                <div class="btn-group">
                                    <a href="#" class="btn btn-danger dropdown-toggle" data-toggle="dropdown">Reject <span class="caret"></span></a>
                                    <ul class="dropdown-menu">
                                        <li><a href="#">Reject entire proposal</a></li>
                                        <li><a href="#">Line-item rejection</a></li>
                                    </ul>
                                </div>
                                <a href="#" class="btn btn-default">Recall</a>
                                <a href="#" class="btn btn-default">Send Notification</a>
                                <a href="#" class="btn btn-primary pull-right">Submit to Sponsor</a>
                            </div>
                        </div>

                        <div class="comments">
                            <div class="comment-actions">
                                <button class="btn btn-default">+ Add Comment</button>
                            </div>

                            <div class="comment-row comment-headers clearfix">
                                <div class="col-md-2">
                                    Date
                                </div>
                                <div class="col-md-2">
                                    Person
                                </div>
                                <div class="col-md-8">
                                    Comment
                                </div>
                            </div>

                            <div class="comment-row clearfix">
                                <div class="col-md-2">
                                    03/22/2013
                                </div>
                                <div class="col-md-2">
                                    Bart Niedner
                                </div>
                                <div class="col-md-8">
                                    Lorem ipsum dolor sit amet, consectetur adipiscing elit. Donec sed lorem in risus ornare semper vitae sit amet mauris. Nam imperdiet sollicitudin odio vel convallis. Nullam non malesuada nisl. Nulla tempor fermentum ornare. Fusce sollicitudin lobortis arcu ut malesuada. Lorem ipsum dolor sit amet, consectetur adipiscing elit. Proin lacinia interdum eleifend. Cras nec hendrerit nulla. Nulla facilisi. In lectus arcu, consectetur vitae auctor in, condimentum at libero. Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum. Suspendisse at quam tortor, ac pulvinar augue.
                                </div>
                            </div>

                            <div class="comment-row clearfix">
                                <div class="col-md-2">
                                    03/21/2013
                                </div>
                                <div class="col-md-2">
                                    Colnel Mustard
                                </div>
                                <div class="col-md-8">
                                    Quisque hendrerit, velit in posuere ullamcorper, ligula nisi tincidunt eros, vel porta nunc ligula at eros. Duis facilisis nunc a lacus lobortis vestibulum.
                                </div>
                            </div>
                        </div>
                    </div>
                </did>
            </div>
        </div>
    </div>

        <div class="uif-stickyFooter uif-stickyButtonFooter">
            <div class="btn-row-page-action">
                <button href="prop.inst.3.php" class="btn btn-default">Back</button>
            </div>
        </div>
      </div>
    </div>
  </div>
</section>

<?php require_once( 'themes/kc/inc/footer.php' ); ?>