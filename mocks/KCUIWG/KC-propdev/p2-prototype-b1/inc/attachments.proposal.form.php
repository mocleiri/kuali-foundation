 <script>

       $(document).ready(function(){
           $('#attachments-proposal-form<?php echo $id?> #type').val("<?php echo $entry['type']?>");
           $('#attachments-proposal-form<?php echo $id?> #approval_status').val("<?php echo $entry['approval_status']?>");
       });

     </script>
                    <form method="post" class="form-horizontal" id="attachments-proposal-form<?php echo $id?>" enctype="multipart/form-data">
                    <input type="hidden" id="id" name="id" value="<?php echo $id?>"/>
                    <input type="hidden" id="action" name="action" value="<?php echo $action?>"/>
                    <input type="hidden" id="section" name="section" value="proposal"/>
                      <div class="row">
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Added by:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> McGregor, Geoff <span class="text-muted">(<?php echo $entry['uploadTime'];?>)</span></p>
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
                              <input type="text" name="contact" id="contact" class="form-control input-sm col-md-8 " placeholder="" value="<?php echo $entry['contact']?>">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="email" class="control-label col-md-3">Email:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="email" id="email" class="form-control input-sm col-md-8 " placeholder="" value="<?php echo $entry['email']?>">
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="" class="control-label col-md-3">Phone:</label>
                            <div class="col-md-9 input-group">
                              <input type="text" name="phone" id="phone" class="form-control input-sm col-md-8 " placeholder="" value="<?php echo $entry['phone']?>">
                            </div>
                          </div>
                        </div>
                        <div class="col-md-6">
                          <div class="form-group clearfix">
                            <label for="file" class="control-label col-md-3">File:</label>
                            <div class="col-md-9 input-group">
                              <p class="form-control-static"> <span class="pull-left"> <span aria-hidden="true" class="icon-file-2"></span> <a href="#"> <?php echo $entry['uploadFile']?></a></span>
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
                              <textarea class="form-control" rows="5" id="description" name="description"><?php echo $entry['description']?></textarea>
                            </div>
                          </div>
                          <div class="form-group clearfix">
                            <label for="comments" class="control-label col-md-3">Comments:</label>
                            <div class="col-md-9 input-group">
                              <textarea class="form-control" rows="5" id="comments" name="comments"><?php echo $entry['comments']?></textarea>
                            </div>
                          </div>
                        </div>
                        <div class="btn-row-widget-action pull-right">
                          <button class="btn btn-primary btn-xs update-attachments-proposal-entry" entryId="<?php echo $id?>" id="update-attachments-proposal-entry<?php echo $id?>"> <?php echo $actionLabel?></button>
                          <button class="btn btn-link btn-xs cancel-update-attachments-proposal-entry" entryId="<?php echo $id?>" id="cancel-update-attachments-proposal-entry<?php echo $id?>"> Cancel</button>
                        </div>
                      </div>
                    </form>
