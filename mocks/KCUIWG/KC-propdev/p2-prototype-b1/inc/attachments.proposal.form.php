 <script>

       $(document).ready(function(){
           $('#attachment-proposal-form<?php echo $id?> #type').val("<?php echo $entry['type']?>");
           $('#attachment-proposal-form<?php echo $id?> #approval_status').val("<?php echo $entry['approval_status']?>");
       });

     </script>
                    <form method="post" class="form-horizontal" id="attachment-proposal-form<?php echo $id?>">
                    <input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
                    <input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
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
                              <select name="type" id="type" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="Narrative" selected="selected">Narrative</option>
                                <option value="Equipment">Equipment</option>
                                <option value="Bibliography">Bibliography</option>
                                <option value="Project Summary">Project Summary</option>
                                <option value="Budget Justification">Budget Justification</option>
                                <option value="Other">Other</option>
                                <option value="Additional Keypersons">Additional Keypersons</option>
                                <option value="Additional Equipment">Additional Equipment</option>
                                <option value="Personal Data">Personal Data</option>
                                <option value="Facilities">Facilities</option>
                                <option value="Subaward Budget">Subaward Budget</option>
                                <option value="Table of Contents">Table of Contents</option>
                                <option value="Supplementary Documentation">Supplementary Documentation</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="approval_status" class="control-label col-md-3">Status:</label>
                            <div class="col-md-9">
                              <select name="approval_status" id="approval_status" class="form-control input-sm col-md-8">
                                <option value="">select</option>
                                <option value="Complete" selected="selected">Complete</option>
                                <option value="Incomplete">Incomplete</option>
                              </select>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="contact" class="control-label col-md-3">Contact:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="contact" class="form-control input-sm col-md-8 " placeholder="" value="Bill Evans">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="email" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="" id="email" class="form-control input-sm col-md-8 " placeholder="" value="bill@evans.com">
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
                            <label for="file" class="control-label col-md-3">File:</label>
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
                            <label for="description" class="control-label col-md-3">Description:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="5" id="description" name="description">Following invidious one hurried less formidable that this mindful and crud inescapable sobbed irrespective together exaggerated ambidextrous walking this absentminded hello iguanodon and well much understood jeez.</textarea>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="comments" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="5" id="comments" name="comments">Because regarding effusively haltered pulled so hence morally manta far flamingo much angelfish less close opaque wherever gloated pouted below so amongst wow mundane horse favorably dear.</textarea>
                            </div>
                          </div>
                        </div>
                        <div class="btn-row-widget-action pull-right">
                          <button class="btn btn-primary btn-xs update-compliance-entry" entryId="<?php echo $id?>" id="update-attachment-proposal-entry<?php echo $id?>"> <?php echo $actionLabel?></button>
                          <button class="btn btn-link btn-xs cancel-update-attachment-proposal-entry" entryId="<?php echo $id?>"> Cancel</button>
                        </div>
                      </div>
                    </form>
