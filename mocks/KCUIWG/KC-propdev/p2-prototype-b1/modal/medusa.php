<!DOCTYPE html>
<!--[if lt IE 7]><html class="no-js ie7" lang="en"><![endif]-->
<!--[if IE 7]><html class="ie9 ie8 ie7" lang="en"><![endif]-->
<!--[if IE 8]><html class="ie9 ie8" lang="en"><![endif]-->
<!--[if IE 9]><html class="ie9" lang="en"><![endif]-->
<!--[if gt IE 8]><!--><html lang="en-us"><!--<![endif]-->
<head>
<title>Kuali Coeus Prototype 2b</title>

<!-- Meta cluster -->
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<meta name="description" content="" />
<meta name="author" content="" />

<!--  Styles -->
<link href="../themes/bootstrap/css/bootstrap.css" rel="stylesheet" />
<link href="../themes/kc/js/fancybox/jquery.fancybox.css" rel="stylesheet" media="screen" />
<link href="../themes/kc/css/smoothness/jquery-ui-1.9.2.custom.min.css" rel="stylesheet" media="screen" />
<link href="../themes/kc/css/jquery.multiselect.css" rel="stylesheet" media="screen" />
<link href="../themes/kc/css/jquery.multiselect.filter.css" rel="stylesheet" media="screen" />
<link href="../themes/kc/css/custom.css" rel="stylesheet" />
<link href="../themes/kc/icons/style.css" rel="stylesheet" type="text/css" />
<link rel="stylesheet" type="text/css" href="../themes/kc/js/datatables/DT_bootstrap.css">
<link href="../themes/kc/js/hopscotch/css/hopscotch-0.1.1.css" rel="stylesheet" type="text/css" media="screen" />

<!-- Scripts, ideally we'd load these in the footer and not use in-line scripting -->
<script src="http://code.jquery.com/jquery-1.10.1.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<!--[if lt IE 9]><script src="bootstrap/js/html5shiv.js"></script><![endif]-->

<script type="text/javascript" charset="utf-8" language="javascript" src="../themes/kc/js/datatables/jquery.dataTables.js"></script>
<script type="text/javascript" charset="utf-8" language="javascript" src="../themes/kc/js/datatables/DT_bootstrap.js"></script>

<div class="modal-dialog">
  <div class="modal-content">
    <div class="modal-header">
      <h3>Medusa</h3>
    </div>
    <div class="modal-body">
      <div class="container">
        <form class="form-horizontal" method="get" action="">
          <fieldset>
            <legend style="display:none">Medusa</legend>
            <!-- Tabs -->
            <ul class="nav nav-tabs">
              <li class="active"><a href="#proposal_award" data-toggle="tab">Proposal > Award</a></li>
              <li><a href="#award_proposal" data-toggle="tab">Award > Proposal</a></li>
            </ul>
            <!-- // -->

            <!-- Panes -->
            <div class="tab-content">
              <div class="tab-pane in active" id="proposal_award">
                <ul class="medusa-tree">
                  <li><i class="icon icon-folder-open"></i> Institutional Proposal 00000018
                    <ul>
                      <li><i class="icon icon-file"></i> Development Proposal 1</li>
                      <li><i class="icon icon-folder-open"></i> <a href="#" class="show-more-content" data-show-more-content="prop_award_award_details">Award 0000026-00001</a>
                        <div class="off-screen" id="prop_award_award_details">
                          <fieldset>
                            <legend>Summary</legend>
                            <div class="form-group clearfix">
                              <label for="award_id" class="control-label col-sm-4 col-xs-4">Award ID:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="award_id">00000026-000001</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="sponsor_award_id" class="control-label col-sm-4 col-xs-4">Sponsor Award ID:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="sponsor_award_id"></span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="account_id" class="control-label col-sm-4 col-xs-4">Account ID:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="account_id"></span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="award_status" class="control-label col-sm-4 col-xs-4">Award Status:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="award_status">Active</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="title" class="control-label col-sm-4 col-xs-4">Title:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="title">Kaci Master Test Project for Medusa</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="award_type" class="control-label col-sm-4 col-xs-4">Award Type:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="award_type">Grant</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="activity_type" class="control-label col-sm-4 col-xs-4">Activity Type:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="activity_type">Research</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="account_type" class="control-label col-sm-4 col-xs-4">Account Type:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="account_type"></span>
                              </div>
                            </div>
                          </fieldset>

                          <fieldset>
                            <legend>Dates &amp; Amounts</legend>
                            <div class="form-group clearfix">
                              <label for="sponsor" class="control-label col-sm-4 col-xs-4">Sponsor:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="sponsor">00500 NSF</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="project_start_date" class="control-label col-sm-4 col-xs-4">Project Start Date:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="project_start_date">09/01/2013</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="project_end_date" class="control-label col-sm-4 col-xs-4">Project End Date:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="project_end_date">08/31/2016</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="anticipated_cumulative" class="control-label col-sm-4 col-xs-4">Anticipated Cumulative:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="anticipated_cumulative">$444,000.00</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="obligation_start_date" class="control-label col-sm-4 col-xs-4">Obligation Start Date:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="obligation_start_date">09/01/2013</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="obligation_end_date" class="control-label col-sm-4 col-xs-4">Obligation End Date:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="obligation_end_date">08/31/2016</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="obligated_cumulative" class="control-label col-sm-4 col-xs-4">Obligation Cumulative:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="obligated_cumulative">$148,000.00</span>
                              </div>
                            </div>
                          </fieldset>

                          <fieldset>
                            <legend>Award Details Recorded</legend>
                            <div class="form-group clearfix">
                              <label for="approved_subaward" class="control-label col-sm-4 col-xs-4">Approved Subaward:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="approved_subaward">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="approved_equipment" class="control-label col-sm-4 col-xs-4">Approved Equipment:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="approved_equipment">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="approved_foreign_travel" class="control-label col-sm-4 col-xs-4">Approved Foreign Travel:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="approved_foreign_travel">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="fa" class="control-label col-sm-4 col-xs-4">F&amp;A:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="fa">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="payment_schedule" class="control-label col-sm-4 col-xs-4">Payment Schedule:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="payment_schedule">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="sponsor_funding_transferred" class="control-label col-sm-4 col-xs-4">Sponsor Funding Transferred:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="sponsor_funding_transferred">No</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="cost_share" class="control-label col-sm-4 col-xs-4">Cost Sharing:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="cost_share">No</span>
                              </div>
                            </div>
                          </fieldset>

                          <fieldset>
                            <legend>Investigators</legend>
                            <div class="form-group clearfix">
                              <label for="investigator" class="control-label col-sm-4 col-xs-4">Investigator(s):</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="investigator">Edward Haskell (PI)</span>
                              </div>
                            </div>
                            <div class="form-group clearfix">
                              <label for="units" class="control-label col-sm-4 col-xs-4">Units:</label>
                              <div class="col-sm-8 col-xs-8">
                                <span id="units">000001 : University<br />IN-CARD : Cardiology (Lead Unit)<br />000001 : University</span>
                              </div>
                            </div>
                          </fieldset>
                        </div>
                        <ul>
                          <li><i class="icon icon-file"></i> Protocol 1307000021</li>
                          <li><i class="icon icon-file"></i> Subaward 1</li>
                        </ul>
                      </li>
                      <li><i class="icon icon-file"></i> Negotiation 1</li>
                    </ul>
                  </li>
                </ul>
              </div>

              <div class="tab-pane" id="award_proposal">
                <ul class="medusa-tree">
                  <li><i class="icon icon-folder-open"></i> Award 00000026-00001
                    <ul>
                      <li><i class="icon icon-file"></i> Institutional Proposal 00000018
                        <ul>
                          <li><i class="icon icon-file"></i> Protocol 1307000021</li>
                          <li><i class="icon icon-file"></i> Negotiation 1</li>
                        </ul>
                      </li>
                      <li><i class="icon icon-folder-open"></i> Protocol 1307000021</li>
                      <li><i class="icon icon-file"></i> Subaward 1</li>
                    </ul>
                  </li>
                </ul>
              </div>
            </div>
            <!-- // -->
          </fieldset>
        </form>
      </div>
    </div>
    <!-- <div class="modal-footer">
      <a href="#" class="btn btn-primary">Save</a> <a href="#" class="btn btn-link">Cancel</a>
    </div> -->
  </div>
</div>

<footer>

  <!--<div class="container-fluid">

    <div class="footer">
      <div class="foundation col_md_6">
        &copy; 2013
        <a href="#" target="_blank">Kuali Foundation</a>
      </div>
      <div class="credit col_md_6">
        <span>site built with KRAD</span>
      </div>
    </div>

  </div>-->
  
</footer>

<!-- Scripts -->
<script>window.jQuery || document.write('<script src="assets/jquery/jquery-2.0.3.min.js"><\/script>')</script> 

<!-- Bootstrap -->
<script src="../themes/bootstrap/js/bootstrap.min.js"></script>
<script src="../themes/bootstrap/js/modal.js"></script>

<script src="../themes/kc/js/fancybox/jquery.fancybox.js"></script>
<script src="../themes/kc/js/jquery.nicescroll.js"></script>
<script src="../themes/kc/js/jquery.cookie.js"></script>
<script src="../themes/kc/js/leftnav.js"></script>

<!-- jQuery UI 1.9.2 -->
<script src="../themes/kc/js/jquery-ui-1.9.2.custom.min.js"></script>
<script src="../themes/kc/js/jquery.multiselect.min.js"></script>
<script src="../themes/kc/js/jquery.multiselect.filter.min.js"></script>
<script src="../themes/kc/js/jquery.maskedinput.min.js"></script>

<script src="../themes/kc/js/hopscotch/js/hopscotch-0.1.2.js"></script> 
<script src="../themes/kc/js/hopscotch/js/prop.basics.details.js"></script> 

<!-- Our custom functions, goes at the end -->
<script src="../themes/kc/js/functions.js"></script>
</body>
</html>