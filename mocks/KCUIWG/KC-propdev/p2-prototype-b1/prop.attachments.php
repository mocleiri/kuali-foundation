<?php
# Variables
$page = 'attachments';

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
      <div id="Uif-PageContentWrapper" class="uif-pageContentWrapper"> <!-- Main content goes here -->
        
        <h3>Attachments</h3>
        <ul class="nav nav-tabs" id="myTab">
          <li class="active"><a href="#proposal">Proposal</a></li>
          <li><a href="#personnel">Personnel</a></li>
          <li><a href="#internal">Internal</a></li>
          <li><a href="#abstracts">Abstracts</a></li>
          <li><a href="#notes">Notes</a></li>
        </ul>
        <div class="tab-content">
          <div class="tab-pane active" id="proposal" style="display:noneee">
            <h4>Proposal</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-3">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse1"><span aria-hidden="true" class="icon-caret-right"></span> Narritive</a> </h4>
                    </div>
                    <div class="col-md-3">my_narritive1.pdf </div>
                    <div class="col-md-3"> Complete </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse1" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Added by:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="type" class="control-label col-md-3">Type:</label>
                            <div class="col-md-9">
                              <p class="form-control-static">Narrative</p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="approval_status" class="control-label col-md-3">Status:</label>
                            <div class="col-md-9">
                              <p class="form-control-static">Complete</p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Contact:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static">Bill Evans</p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static">bill@evans.com</p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Phone:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static">734-234-3735</p>
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">File:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> <span class="pull-left"><span aria-hidden="true" class="icon-file-2"></span> <a href="#">my_narritive1.pdf</a></span> </p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Description:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static">Because regarding effusively haltered pulled so hence morally manta far flamingo much angelfish less close opaque wherever gloated pouted below so amongst wow mundane horse favorably dear.</p>
                            </div>
                          </div>
                        </div>
                        <div class="btn-row-widget-action pull-right">
                          <button class="btn btn-primary btn-xs" id=""> Edit</button>
                          <button class="btn btn-link btn-xs" id=""> Cancel</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-3">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse2"><span aria-hidden="true" class="icon-caret-right"></span> Narritive</a> </h4>
                    </div>
                    <div class="col-md-3"> my_narritive2.pdf </div>
                    <div class="col-md-3"> Complete </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse2" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Added by:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="type" class="control-label col-md-3">Type:</label>
                            <div class="col-md-9">
                              <select name="type2" id="type2" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="1" selected="selected">Narrative</option>
                                <option value="3">Equipment</option>
                                <option value="4">Bibliography</option>
                                <option value="5">ProjectSummary</option>
                                <option value="7">BudgetJustification</option>
                                <option value="8">Other</option>
                                <option value="11">Additionalkeypersons</option>
                                <option value="12">Additionalequipment</option>
                                <option value="13">PersonalData</option>
                                <option value="2">Facilities</option>
                                <option value="9">SubawardBudget</option>
                                <option value="10">TableofContents</option>
                                <option value="15">SupplementaryDocumentation</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="approval_status" class="control-label col-md-3">Status:</label>
                            <div class="col-md-9">
                              <select name="type3" id="type3" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="C" selected="selected">Complete</option>
                                <option value="I">Incomplete</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Contact:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="Bill Evans">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="bill@evans.com">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Phone:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="734-234-3735">
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">File:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> my_narritive2.pdf</a></span> 
                              <span class="">
                              <div class="dropdown pull-right"><span class="caret"></span> <a data-toggle="dropdown" href="#">Actions</a>
                                <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                                  <li><a href="#">Replace File</a></li>
                                  <li><a href="#">Set Permissions</a></li>
                                </ul>
                              </div>
                              </span>
                              </p>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Description:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="5">Because regarding effusively haltered pulled so hence morally manta far flamingo much angelfish less close opaque wherever gloated pouted below so amongst wow mundane horse favorably dear.</textarea>
                            </div>
                          </div>
                        </div>
                        <div class="btn-row-widget-action pull-right">
                          <button class="btn btn-primary btn-xs" id=""> Save</button>
                          <button class="btn btn-link btn-xs" id=""> Cancel</button>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default" id="add_proposal">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> Add Proposal Attachment 
                        <!-- <span>-</span>
											<span>-</span>
											<span>-</span>
											<span>-</span> --> 
                      </h4>
                    </div>
                    <div class="col-md-6"> <a class="pull-right"href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse3" class="panel-collapse collapse in">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="type" class="control-label col-md-3">Type:</label>
                            <div class="col-md-9">
                              <select name="type2" id="type2" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="1">Narrative</option>
                                <option value="3">Equipment</option>
                                <option value="4">Bibliography</option>
                                <option value="5">ProjectSummary</option>
                                <option value="7">BudgetJustification</option>
                                <option value="8">Other</option>
                                <option value="11">Additionalkeypersons</option>
                                <option value="12">Additionalequipment</option>
                                <option value="13">PersonalData</option>
                                <option value="2">Facilities</option>
                                <option value="9">SubawardBudget</option>
                                <option value="10">TableofContents</option>
                                <option value="15">SupplementaryDocumentation</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="approval_status" class="control-label col-md-3">Status:</label>
                            <div class="col-md-9">
                              <select name="type3" id="type3" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="C">Complete</option>
                                <option value="I">Incomplete</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Contact:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Phone:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="" class="form-control input-sm col-md-8 " placeholder="" value="">
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">File:</label>
                            <div class="col-md-9 input-group">
                              <input type="file" id="exampleInputFile">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Description:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="3"></textarea>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="3"></textarea>
                            </div>
                          </div>
                          <div class="btn-row-widget-action pull-right">
                            <button class="btn btn-primary btn-xs" id=""> Add</button>
                            <button class="btn btn-link btn-xs" onClick="window.location.reload()" id=""> Cancel</button>
                          </div>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id="proposal_add"><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="personnel">
            <h4>Personnel</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-3">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse8"><span aria-hidden="true" class="icon-caret-right"></span> Biosketch</a> </h4>
                    </div>
                    <div class="col-md-3"> KGbiosketch.pdf </div>
                    <div class="col-md-3"> Ken Graves </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse8" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="type" class="control-label col-md-3">Person:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">Ken Graves</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">Biosketch</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> KGbiosketch.pdf</a></span> </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Edit</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-3">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse4"><span aria-hidden="true" class="icon-caret-right"></span> Biosketch</a> </h4>
                    </div>
                    <div class="col-md-3"> KGbiosketch.pdf </div>
                    <div class="col-md-3"> Ken Graves </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse4" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="type" class="control-label col-md-3">Person:</label>
                        <div class="col-md-9">
                          <select name="type2" id="type2" class="form-control input-sm col-md-8">
                            <option value="">select</option>
                            <option value="1" selected="selected">Ken Graves</option>
                            <option value="3">Bill Evans</option>
                            <option value="4">Sarah Vaughn</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <select name="type3" id="type3" class="form-control input-sm col-md-8">
                            <option value="">select</option>
                            <option value="1" selected="selected">Biosketch</option>
                            <option value="3">BudgetDetails</option>
                            <option value="2">Currentpending</option>
                            <option value="5">Other</option>
                            <option value="4">StatementofCommitment</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> KGbiosketch.pdf</a></span> 
                          <span class="">
                          <div class="dropdown pull-right"><span class="caret"></span> <a data-toggle="dropdown" href="#">Actions</a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                              <li><a href="#">Replace File</a></li>
                              <li><a href="#">Set Permissions</a></li>
                            </ul>
                          </div>
                          </span>
                          </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Save</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id=""><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="internal">
            <h4>Internal</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse9"><span aria-hidden="true" class="icon-caret-right"></span> MIT Cost-Sharing Distribution</a> </h4>
                    </div>
                    <div class="col-md-3"> MIT-costsharingdoc.pdf </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse9" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">MIT Cost-Sharing Distribution</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> MIT-costsharingdoc.pdf</a></span> </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Edit</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse5"><span aria-hidden="true" class="icon-caret-right"></span> MIT Cost-Sharing Distribution</a> </h4>
                    </div>
                    <div class="col-md-3"> MIT-costsharingdoc.pdf </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse5" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Attachment Type:</label>
                        <div class="col-md-9">
                          <select name="type3" id="type3" class="form-control input-sm col-md-8">
                            <option value="">select:</option>
                            <option value="55" selected="selected">MIT Cost-Sharing Distribution</option>
                            <option value="56">MIT F&amp;A Under-recovery Distribution</option>
                            <option value="60">Other Institutional Attachment</option>
                            <option value="299">Institutional Narrative 1 - Change this description as Needed</option>
                            <option value="900">1st Institutional Attachment</option>
                            <option value="901">2nd Institutional Attachment</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">File:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> MIT-costsharingdoc.pdf</a></span> 
                          <span class="">
                          <div class="dropdown pull-right"><span class="caret"></span> <a data-toggle="dropdown" href="#">Actions</a>
                            <ul class="dropdown-menu" role="menu" aria-labelledby="dLabel">
                              <li><a href="#">Replace File</a></li>
                              <li><a href="#">Set Permissions</a></li>
                            </ul>
                          </div>
                          </span>
                          </p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Description:</label>
                        <div class="col-md-9 input-group">
                          <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                        </div>
                      </div>
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Save</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id=""><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="abstracts">
            <h4>Abstracts</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-9">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse6"><span aria-hidden="true" class="icon-caret-right"></span> Project Summary</a> </h4>
                    </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse6" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">Project Summary</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Abstract Details:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                      
                      
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Edit</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                      
                      
                    </form>
                  </div>
                </div>
              </div>
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-9">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse10"><span aria-hidden="true" class="icon-caret-right"></span> Project Summary</a> </h4>
                    </div>
                    <div class="col-md-3"> <a class="pull-right" href="#"><span aria-hidden="true" class="icon-remove"></span></a> </div>
                  </div>
                </div>
                <div id="collapse10" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Abstract Type:</label>
                        <div class="col-md-9">
                          <select name="type3" id="type3" class="form-control input-sm col-md-8">
                            <option value="">select</option>
                            <option value="1" selected="selected">Project Summary</option>
                            <option value="2">Technical Abstract</option>
                            <option value="4">Labs</option>
                            <option value="5">Clinical</option>
                            <option value="6">Animal</option>
                            <option value="7">Computer</option>
                            <option value="8">Office</option>
                            <option value="9">Other Facilities</option>
                            <option value="10">Equipment</option>
                            <option value="11">Other Resources</option>
                            <option value="12">Suggested Reviewers</option>
                            <option value="13">Publications</option>
                            <option value="14">Reviewers Not to Include</option>
                            <option value="15">Deviation Authorization</option>
                            <option value="16">Areas Affected</option>
                            <option value="17">Relevance</option>
                          </select>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Abstract Details:</label>
                        <div class="col-md-9 input-group">
                          <textarea class="form-control" rows="5">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                        </div>
                      </div>
                      
                      
                      <div class="btn-row-widget-action pull-right">
                        <button class="btn btn-primary btn-xs" id=""> Save</button>
                        <button class="btn btn-link btn-xs" id=""> Cancel</button>
                      </div>
                      
                      
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id=""><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
          <div class="tab-pane" id="notes">
            <h4>Notes</h4>
            <div class="panel-group" id="accordion">
              <div class="panel panel-default">
                <div class="panel-heading">
                  <div class="row">
                    <div class="col-md-6">
                      <h4 class="panel-title"> <a class="accordion-toggle pull-left" data-toggle="collapse" data-parent="#accordion" href="#collapse7"><span aria-hidden="true" class="icon-caret-right"></span>My Note Title</a> </h4>
                    </div>
                    <div class="col-md-3"> McGregor, Geoff </div>
                    <div class="col-md-3"> 10/29/2013 09:58 AM </div>
                  </div>
                </div>
                <div id="collapse7" class="panel-collapse collapse">
                  <div class="panel-body">
                    <form method="post" class="form-horizontal">
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Added by:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(10/29/2013 09:58 AM)</span></p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="approval_status" class="control-label col-md-3">Note Topic:</label>
                        <div class="col-md-9">
                          <p class="form-control-static">My Note Title</p>
                        </div>
                      </div>
                      <div class="form-group clearfix">
                        <label for="" class="control-label col-md-3">Note Text:</label>
                        <div class="col-md-9 input-group">
                          <p class="form-control-static">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</p>
                        </div>
                      </div>
                    </form>
                  </div>
                </div>
              </div>
            </div>
            <div class="btn-row-widget-action">
              <button class="btn btn-default btn-xs" id=""><span aria-hidden="true" class="icon-plus"></span> Add Entry</button>
            </div>
          </div>
        </div>
        
        <!-- // -->
        <div class="uif-stickyFooter uif-stickyButtonFooter"> <!-- Button row -->
          <div class="btn-row-page-action">
            <button  onclick="location.href='prop.attachments.internal.php'" class="btn btn-default">Back</button>
            <button class="btn btn-default">Save</button>
            <button  onclick="location.href='prop.attachments.notes.php'" class="btn btn-primary">Save and Continue</button>
          </div>
          <!-- // --> 
        </div>
      </div>
    </div>
  </div>
</section>
<script>
$('#myTab a').click(function (e) {
  e.preventDefault()
  $(this).tab('show')
})

$('#add_proposal').hide();
$('#proposal_add').click(function() {
	$('#add_proposal').fadeIn();
});
</script>
<?php require_once( 'themes/kc/inc/footer.php' ); ?>
